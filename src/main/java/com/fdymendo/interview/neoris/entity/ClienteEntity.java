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
@Table(name = "cliente")
public class ClienteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "persona_id")
  private Integer personaId;
  private String contrasenia;
  private Integer estado;
  @Column(insertable = true, updatable = false)
  private Date fechaCreacion;
  @Column(insertable = true, updatable = true)
  private Date fechaActualizacion;

}
