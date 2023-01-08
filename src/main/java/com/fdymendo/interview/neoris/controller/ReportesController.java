package com.fdymendo.interview.neoris.controller;

import java.text.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.services.ITransaccionesService;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {

  private ITransaccionesService iTransaccionesService;

  public ReportesController(ITransaccionesService iTransaccionesService) {
    this.iTransaccionesService = iTransaccionesService;

  }

  @GetMapping
  public ResponseDefault movimientosPorFecha(@RequestParam String fechainicial,
      @RequestParam String fechafinal) throws ApplicationException, ParseException {
    return this.iTransaccionesService.TodosLosMovimientos(fechainicial, fechafinal);
  }

}
