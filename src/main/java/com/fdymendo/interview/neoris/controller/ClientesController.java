package com.fdymendo.interview.neoris.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.dto.ClienteDTO;
import com.fdymendo.interview.neoris.services.IUsuarioService;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

  private IUsuarioService iUsuarioService;

  public ClientesController(IUsuarioService iUsuarioService) {
    this.iUsuarioService = iUsuarioService;
  }

  @PostMapping
  public ResponseDefault crear(@RequestBody ClienteDTO cliente) throws ApplicationException {
    return this.iUsuarioService.crearOActualizar(cliente, false);
  }

  @PutMapping
  public ResponseDefault actualizar(@RequestBody ClienteDTO cliente) throws ApplicationException {
    return this.iUsuarioService.crearOActualizar(cliente, true);
  }

  @DeleteMapping
  public ResponseDefault eliminar(@RequestBody ClienteDTO cliente) throws ApplicationException {
    return this.iUsuarioService.Eliminar(cliente);
  }
  
}
