package com.fdymendo.interview.neoris.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.dto.CuentaDTO;
import com.fdymendo.interview.neoris.services.ICuentasBancoService;

@RestController
@RequestMapping("/api/cuentas")
public class CuentasController {
  
  private ICuentasBancoService iCuentasBancoService;

  public CuentasController(ICuentasBancoService iCuentasBancoService) {
    this.iCuentasBancoService = iCuentasBancoService;
  }

  @PostMapping
  public ResponseDefault crear(@RequestBody CuentaDTO cuenta) throws ApplicationException {
    return this.iCuentasBancoService.crearOactualizar(cuenta, false);
  }

  @PutMapping
  public ResponseDefault actualizar(@RequestBody CuentaDTO cuenta) throws ApplicationException {
    return this.iCuentasBancoService.crearOactualizar(cuenta, true);
  }

  @DeleteMapping
  public ResponseDefault eliminar(@RequestBody CuentaDTO cuenta) throws ApplicationException {
    return this.iCuentasBancoService.eliminar(cuenta);
  }
  
}
