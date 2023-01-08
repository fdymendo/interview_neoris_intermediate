package com.fdymendo.interview.neoris.model.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.utils.GenericMethods;
import com.fdymendo.interview.neoris.utils.TipoMovimiento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MovimientosDTO {

  private Integer id;
  private String numeroCuenta;
  private TipoMovimiento tipoMovimiento;
  private Double valor;
  private Double saldo;
  private Date fechaCreacion;
  private Date fechaActualizacion;


  public void validate() throws ApplicationException {
    if (this.numeroCuenta == null || this.tipoMovimiento == null || this.valor == null) {
      GenericMethods.generateError();
    }
  }

}
