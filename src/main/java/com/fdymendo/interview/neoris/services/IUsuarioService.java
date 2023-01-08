package com.fdymendo.interview.neoris.services;

import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.dto.ClienteDTO;

public interface IUsuarioService {

  ResponseDefault crearOActualizar(ClienteDTO cliente, boolean actualizar)
      throws ApplicationException;

  ResponseDefault Eliminar(ClienteDTO cliente) throws ApplicationException;

}
