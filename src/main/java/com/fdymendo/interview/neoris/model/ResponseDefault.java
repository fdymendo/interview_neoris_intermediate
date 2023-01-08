package com.fdymendo.interview.neoris.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fdymendo.interview.neoris.model.dto.ClienteDTO;
import com.fdymendo.interview.neoris.model.dto.CuentaDTO;
import com.fdymendo.interview.neoris.model.dto.MovimientosDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ResponseDefault {
  private CuentaDTO cuenta;
  private ClienteDTO cliente;
  private MovimientosDTO movimiento;
  private List<ListMovimientos> movimientos;

}
