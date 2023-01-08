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
@Table(name = "movimientos")
public class MovimientosEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private Integer cuentaId;
  private String tipoMovimiento;
  private Double valor;
  private Double saldo;
  @Column(insertable = true, updatable = false)
  private Date fechaCreacion;
  @Column(insertable = true, updatable = true)
  private Date fechaActualizacion;

}
