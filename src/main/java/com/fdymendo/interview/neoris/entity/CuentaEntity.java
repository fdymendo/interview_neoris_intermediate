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
@Table(name = "cuenta")
public class CuentaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private Integer clienteId;
  private String numeroCuenta;
  private String tipoCuenta;
  private Double saldoInicial;
  private Integer estado;
  private String clave;
  @Column(insertable = true, updatable = false)
  private Date fechaCreacion;
  @Column(insertable = true, updatable = true)
  private Date fechaActualizacion;

}
