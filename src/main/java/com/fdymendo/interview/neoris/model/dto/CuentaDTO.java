package com.fdymendo.interview.neoris.model.dto;

import java.util.Date;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.utils.ApplicationConstant;
import com.fdymendo.interview.neoris.utils.GenericMethods;
import com.fdymendo.interview.neoris.utils.TipoCuenta;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CuentaDTO {

  private Integer id;
  private Integer clienteId;
  private String numeroCuenta;
  private TipoCuenta tipoCuenta;
  private Double saldoInicial;
  private Boolean estado;
  private String clave;
  private Date fechaCreacion;
  private Date fechaActualizacion;

  public void validateCreate() throws ApplicationException {
    if (this.clienteId == null || this.numeroCuenta == null || this.saldoInicial == null
        || this.estado == null || this.clave == null) {
      GenericMethods.generateError();

    }
  }

  public void validateActualizar() throws ApplicationException {
    if (this.id == null) {
      throw new ApplicationException(null, ApplicationConstant.ERROR_MESSAGE_500,
          HttpStatus.BAD_REQUEST);
    }
    this.validateCreate();
  }

  public void clean() {
    this.clave = null;
  }

}
