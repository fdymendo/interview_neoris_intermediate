package com.fdymendo.interview.neoris.model.dto;

import java.util.Date;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.utils.GenericMethods;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonaDTO {

  private Integer id;
  private String nombre;
  private String genero;
  private Integer edad;
  private Integer identificacion;
  private String direccion;
  private String telefono;
  private Date fechaCreacion;
  private Date fechaActualizacion;

  public void validarCrear() throws ApplicationException {

    if (this.genero == null || this.edad == null) {
      GenericMethods.generateError();
    }

  }

  public void validarActualizar() throws ApplicationException {
    if (this.id == null) {
      GenericMethods.generateError();
    }
    this.validarCrear();
  }

}
