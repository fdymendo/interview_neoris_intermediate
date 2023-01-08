package com.fdymendo.interview.neoris.services.impl;

import java.util.Date;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.fdymendo.interview.neoris.entity.ClienteEntity;
import com.fdymendo.interview.neoris.entity.PersonaEntity;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.dto.ClienteDTO;
import com.fdymendo.interview.neoris.model.dto.PersonaDTO;
import com.fdymendo.interview.neoris.services.IUsuarioService;
import com.fdymendo.interview.neoris.services.impl.crud.ClienteServiceImpl;
import com.fdymendo.interview.neoris.services.impl.crud.PersonaServiceImpl;
import com.fdymendo.interview.neoris.utils.GenericMethods;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

  private ClienteServiceImpl iClienteService;
  private ModelMapper modelMapper;
  private PersonaServiceImpl personaServiceImpl;

  public UsuarioServiceImpl(ModelMapper modelMapper, ClienteServiceImpl iClienteService,
      PersonaServiceImpl personaServiceImpl) {
    this.modelMapper = modelMapper;
    this.iClienteService = iClienteService;
    this.personaServiceImpl = personaServiceImpl;
  }

  @Override
  public ResponseDefault crearOactualizar(ClienteDTO cliente, boolean actualizar)
      throws ApplicationException {

    Date initransaction = new Date();

    if (actualizar) {
      cliente.validateUpdate();
    } else {
      cliente.validateCreate();
    }

    PersonaEntity personaTmp = obtenerPersona(cliente.getPersonaInfo(), actualizar);
    personaTmp.setFechaCreacion(initransaction);
    personaTmp.setFechaActualizacion(initransaction);

    cliente.setPersonaInfo(null);

    ClienteEntity clientetmp = this.modelMapper.map(cliente, ClienteEntity.class);
    clientetmp.setFechaCreacion(initransaction);
    clientetmp.setFechaActualizacion(initransaction);

    this.personaServiceImpl.saveItem(personaTmp);
    clientetmp.setPersonaId(personaTmp.getId());
    this.iClienteService.saveItem(clientetmp);

    ResponseDefault respuesta = new ResponseDefault();
    ClienteDTO clienteRespuesta = this.modelMapper.map(clientetmp, ClienteDTO.class);
    clienteRespuesta.setPersonaInfo(this.modelMapper.map(personaTmp, PersonaDTO.class));
    clienteRespuesta.clean();
    respuesta.setCliente(clienteRespuesta);
    return respuesta;
  }

  @Override
  public ResponseDefault eliminar(ClienteDTO cliente) throws ApplicationException {
    if (cliente.getId() == null) {
      GenericMethods.generateError();
    }
    this.iClienteService.deleteItem(cliente.getId());
    return new ResponseDefault();
  }

  private PersonaEntity obtenerPersona(PersonaDTO persona, boolean actualizar)
      throws ApplicationException {

    PersonaEntity tmp = this.modelMapper.map(persona, PersonaEntity.class);

    if (actualizar) {
      this.personaServiceImpl.getItem(persona.getId());
    }

    return tmp;

  }

}
