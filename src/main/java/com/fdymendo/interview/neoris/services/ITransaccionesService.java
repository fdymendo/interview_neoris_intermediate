package com.fdymendo.interview.neoris.services;

import java.text.ParseException;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.dto.MovimientosDTO;

public interface ITransaccionesService {

  ResponseDefault crearOActualizar(MovimientosDTO movimientos) throws ApplicationException;;

  ResponseDefault Eliminar(MovimientosDTO movimientos) throws ApplicationException;

  ResponseDefault TodosLosMovimientos(String fechaInicial, String fechaFinal)
      throws ApplicationException, ParseException;
}
