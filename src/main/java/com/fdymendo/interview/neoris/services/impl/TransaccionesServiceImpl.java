package com.fdymendo.interview.neoris.services.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fdymendo.interview.neoris.entity.ClienteEntity;
import com.fdymendo.interview.neoris.entity.CuentaEntity;
import com.fdymendo.interview.neoris.entity.MovimientosEntity;
import com.fdymendo.interview.neoris.entity.PersonaEntity;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ListMovimientos;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.dto.MovimientosDTO;
import com.fdymendo.interview.neoris.services.ITransaccionesService;
import com.fdymendo.interview.neoris.services.impl.crud.ClienteServiceImpl;
import com.fdymendo.interview.neoris.services.impl.crud.CuentaServiceImpl;
import com.fdymendo.interview.neoris.services.impl.crud.MovimientoServiceImpl;
import com.fdymendo.interview.neoris.services.impl.crud.PersonaServiceImpl;
import com.fdymendo.interview.neoris.utils.ApplicationConstant;
import com.fdymendo.interview.neoris.utils.GenericMethods;
import com.fdymendo.interview.neoris.utils.TipoMovimiento;

@Service
public class TransaccionesServiceImpl implements ITransaccionesService {

  private ModelMapper modelMapper;
  private MovimientoServiceImpl iMovimientosService;
  private CuentaServiceImpl cuentaServiceImpl;
  private ClienteServiceImpl clienteServiceImpl;
  private PersonaServiceImpl personaServiceImpl;

  public TransaccionesServiceImpl(ModelMapper modelMapper,
      MovimientoServiceImpl iMovimientosService, CuentaServiceImpl cuentaServiceImpl,
      ClienteServiceImpl clienteServiceImpl, PersonaServiceImpl personaServiceImpl) {
    this.modelMapper = modelMapper;
    this.iMovimientosService = iMovimientosService;
    this.cuentaServiceImpl = cuentaServiceImpl;
    this.clienteServiceImpl = clienteServiceImpl;
    this.personaServiceImpl = personaServiceImpl;
  }

  @Override
  public ResponseDefault crearOactualizar(MovimientosDTO movimientos) throws ApplicationException {

    movimientos.validate();

    if (!this.cuentaServiceImpl.saldoValido(movimientos.getNumeroCuenta(), movimientos.getValor(),
        movimientos.getTipoMovimiento())) {
      throw new ApplicationException(null, ApplicationConstant.ERROR_INSUFFICIENTE_BALANCE,
          HttpStatus.BAD_REQUEST);
    }

    CuentaEntity cuenta =
        this.cuentaServiceImpl.getCuentaByNumeroCuenta(movimientos.getNumeroCuenta());
    movimientos.setSaldo(cuenta.getSaldoInicial());
    cuenta = calcularSaldo(cuenta, movimientos.getValor(), movimientos.getTipoMovimiento());

    MovimientosEntity tmp = this.modelMapper.map(movimientos, MovimientosEntity.class);
    tmp.setFechaCreacion(new Date());
    tmp.setFechaActualizacion(new Date());
    tmp.setCuentaId(cuenta.getId());

    this.iMovimientosService.saveItem(tmp);
    this.cuentaServiceImpl.saveItem(cuenta);

    ResponseDefault response = new ResponseDefault();
    MovimientosDTO movimientoRespuesta = this.modelMapper.map(tmp, MovimientosDTO.class);
    movimientoRespuesta.setNumeroCuenta(cuenta.getNumeroCuenta());
    response.setMovimiento(movimientoRespuesta);

    return response;
  }

  @Override
  public ResponseDefault eliminar(MovimientosDTO movimientos) throws ApplicationException {
    if (movimientos.getId() == null) {
      GenericMethods.generateError();
    }
    this.iMovimientosService.deleteItem(movimientos.getId());

    return new ResponseDefault();
  }

  private CuentaEntity calcularSaldo(CuentaEntity cuenta, double valor,
      TipoMovimiento tipoMovimiento) {
    if (tipoMovimiento.equals(TipoMovimiento.CREDITO)) {
      valor *= -1;
    }
    cuenta.setSaldoInicial(cuenta.getSaldoInicial() + valor);
    return cuenta;
  }

  private Double calcularSaldoSinCuenta(Double saldoInicial, double valor,
      TipoMovimiento tipoMovimiento) {
    if (tipoMovimiento.equals(TipoMovimiento.CREDITO)) {
      valor *= -1;
    }
    return saldoInicial + valor;
  }

  @Override
  public ResponseDefault todosLosMovimientos(String fechaInicial, String fechaFinal)
      throws ApplicationException, ParseException {
    List<MovimientosEntity> listMovimientos =
        this.iMovimientosService.findByFechaCreacionBetween(fechaInicial, fechaFinal);

    ResponseDefault respuesta = new ResponseDefault();
    respuesta.setMovimientos(crearMovimientos(listMovimientos));
    return respuesta;
  }

  private List<ListMovimientos> crearMovimientos(List<MovimientosEntity> listMovimientos)
      throws ApplicationException, ParseException {
    List<ListMovimientos> items = new ArrayList<>();

    for (MovimientosEntity movimiento : listMovimientos) {

      CuentaEntity cuentaTmp = this.cuentaServiceImpl.getItem(movimiento.getCuentaId());
      ClienteEntity clienteTmp = this.clienteServiceImpl.getItem(cuentaTmp.getClienteId());
      PersonaEntity personaEntity = this.personaServiceImpl.getItem(clienteTmp.getPersonaId());

      ListMovimientos item = new ListMovimientos();
      item.setFecha(GenericMethods.generarFormatoFecha(movimiento.getFechaCreacion()));
      item.setCliente(personaEntity.getNombre());
      item.setNumeroCuenta(cuentaTmp.getNumeroCuenta());
      item.setTipo(cuentaTmp.getTipoCuenta());
      item.setSaldoInicial(movimiento.getSaldo());
      item.setMovimiento(movimiento.getValor());

      item.setSaldoDisponible(calcularSaldoSinCuenta(movimiento.getSaldo(), movimiento.getValor(),
          TipoMovimiento.valueOf(movimiento.getTipoMovimiento())));
      items.add(item);
    }
    return items;
  }
}
