package com.fdymendo.interview.neoris.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.dto.MovimientosDTO;
import com.fdymendo.interview.neoris.services.ITransaccionesService;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientosController {

  private ITransaccionesService iTransaccionesService;

  public MovimientosController(ITransaccionesService iTransaccionesService) {
    this.iTransaccionesService = iTransaccionesService;
  }

  @PostMapping
  public ResponseDefault crear(@RequestBody MovimientosDTO movimientos)
      throws ApplicationException {
    return this.iTransaccionesService.crearOActualizar(movimientos);
  }

  @DeleteMapping
  public ResponseDefault eliminar(@RequestBody MovimientosDTO movimientos)
      throws ApplicationException {
    return this.iTransaccionesService.Eliminar(movimientos);
  }

}
