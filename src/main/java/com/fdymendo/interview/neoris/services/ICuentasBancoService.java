package com.fdymendo.interview.neoris.services;

import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.dto.CuentaDTO;

public interface ICuentasBancoService {

  ResponseDefault crearOActualizar(CuentaDTO cuenta, boolean actualizar) throws ApplicationException;

  ResponseDefault Eliminar(CuentaDTO cuenta) throws ApplicationException;

}
