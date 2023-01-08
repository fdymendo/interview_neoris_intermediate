package com.fdymendo.interview.neoris.services;

import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.dto.ClienteDTO;

public interface IUsuarioService {

  ResponseDefault crearOactualizar(ClienteDTO cliente, boolean actualizar)
      throws ApplicationException;

  ResponseDefault eliminar(ClienteDTO cliente) throws ApplicationException;

}
