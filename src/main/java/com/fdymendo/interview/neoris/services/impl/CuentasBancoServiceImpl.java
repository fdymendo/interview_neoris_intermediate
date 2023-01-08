package com.fdymendo.interview.neoris.services.impl;

import java.util.Date;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.fdymendo.interview.neoris.entity.CuentaEntity;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.dto.CuentaDTO;
import com.fdymendo.interview.neoris.services.ICuentasBancoService;
import com.fdymendo.interview.neoris.services.impl.crud.ClienteServiceImpl;
import com.fdymendo.interview.neoris.services.impl.crud.CuentaServiceImpl;
import com.fdymendo.interview.neoris.utils.GenericMethods;

@Service
public class CuentasBancoServiceImpl implements ICuentasBancoService {

  private ModelMapper modelMapper;
  private CuentaServiceImpl iCuentaService;
  private ClienteServiceImpl clienteServiceImpl;

  public CuentasBancoServiceImpl(ModelMapper modelMapper, CuentaServiceImpl iCuentaService,
      ClienteServiceImpl clienteServiceImpl) {
    this.modelMapper = modelMapper;
    this.iCuentaService = iCuentaService;
    this.clienteServiceImpl = clienteServiceImpl;
  }

  @Override
  public ResponseDefault crearOactualizar(CuentaDTO cuenta, boolean actualizar)
      throws ApplicationException {

    if (actualizar) {
      cuenta.validateActualizar();
    } else {
      cuenta.validateCreate();
    }

    CuentaEntity tmp = this.modelMapper.map(cuenta, CuentaEntity.class);
    this.clienteServiceImpl.getItem(tmp.getClienteId());
    tmp.setFechaCreacion(new Date());
    tmp.setFechaActualizacion(new Date());

    this.iCuentaService.saveItem(tmp);

    ResponseDefault respuesta = new ResponseDefault();
    CuentaDTO cuentaRespuesta = this.modelMapper.map(tmp, CuentaDTO.class);
    cuentaRespuesta.clean();
    respuesta.setCuenta(cuentaRespuesta);
    return respuesta;
  }

  @Override
  public ResponseDefault eliminar(CuentaDTO cuenta) throws ApplicationException {
    if (cuenta.getId() == null) {
      GenericMethods.generateError();
    }
    this.iCuentaService.deleteItem(cuenta.getId());
    return new ResponseDefault();
  }

}
