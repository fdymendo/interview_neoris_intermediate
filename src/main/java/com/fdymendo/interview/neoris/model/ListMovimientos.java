package com.fdymendo.interview.neoris.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ListMovimientos {

  private String fecha;
  private String cliente;
  private String numeroCuenta;
  private String tipo;
  private Double saldoInicial;
  private Boolean state;
  private Double movimiento;
  private Double saldoDisponible;
}
