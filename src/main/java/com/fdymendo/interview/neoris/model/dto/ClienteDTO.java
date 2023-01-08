package com.fdymendo.interview.neoris.model.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.utils.GenericMethods;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ClienteDTO {

  private Integer id;
  private PersonaDTO personaInfo;
  private String contrasenia;
  private Integer estado;
  private Date fechaCreacion;
  private Date fechaActualizacion;

  public void validateCreate() throws ApplicationException {
    if (this.personaInfo == null || this.contrasenia == null || this.estado == null) {
      GenericMethods.generateError();
    }

    this.personaInfo.validarCrear();

  }

  public void validateUpdate() throws ApplicationException {
    if (this.id == null) {
      GenericMethods.generateError();
    }
    this.validateCreate();
  }

  public void clean() {
    this.contrasenia = null;
  }
}
