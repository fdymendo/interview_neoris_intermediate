package com.fdymendo.interview.neoris.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "persona")
public class PersonaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String nombre;
  private String genero;
  private Integer edad;
  private Integer identificacion;
  private String direccion;
  private String telefono;
  @Column(insertable = true, updatable = false)
  private Date fechaCreacion;
  @Column(insertable = true, updatable = true)
  private Date fechaActualizacion;

}
