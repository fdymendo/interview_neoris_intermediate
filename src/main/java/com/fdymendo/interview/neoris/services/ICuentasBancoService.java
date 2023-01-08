package com.fdymendo.interview.neoris.services;

import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.dto.CuentaDTO;

public interface ICuentasBancoService {

  ResponseDefault crearOactualizar(CuentaDTO cuenta, boolean actualizar) throws ApplicationException;

  ResponseDefault eliminar(CuentaDTO cuenta) throws ApplicationException;

}
