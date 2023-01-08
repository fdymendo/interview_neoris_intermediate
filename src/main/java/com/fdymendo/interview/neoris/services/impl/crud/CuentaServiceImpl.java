package com.fdymendo.interview.neoris.services.impl.crud;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fdymendo.interview.neoris.entity.CuentaEntity;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.repository.ICuentaRepository;
import com.fdymendo.interview.neoris.services.crud.ACrudServiceTemplate;
import com.fdymendo.interview.neoris.utils.ApplicationConstant;
import com.fdymendo.interview.neoris.utils.TipoMovimiento;

@Service
public class CuentaServiceImpl extends ACrudServiceTemplate<ICuentaRepository, CuentaEntity> {

  public CuentaServiceImpl(ICuentaRepository repository) {
    super(repository);
  }

  @Override
  public CuentaEntity updateItem(CuentaEntity item, int id) throws ApplicationException {
    CuentaEntity tmp = this.getItem(id);
    item.setId(tmp.getId());
    return this.repository.save(item);
  }

  public boolean saldoValido(String numeroCuenta, double valor, TipoMovimiento tipoMovimiento)
      throws ApplicationException {
    if (tipoMovimiento.equals(TipoMovimiento.DEBITO)) {
      return true;
    }

    CuentaEntity cuenta = getCuentaByNumeroCuenta(numeroCuenta);
    return (cuenta.getSaldoInicial() - valor) >= 0;

  }

  public CuentaEntity getCuentaByNumeroCuenta(String numeroCuenta) throws ApplicationException {
    List<CuentaEntity> tmp = this.repository.findByNumeroCuenta(numeroCuenta);
    if (tmp == null || tmp.isEmpty() || tmp.size() > 1) {
      throw new ApplicationException(null, ApplicationConstant.ERROR_ACCOUNT_CORRUPTED,
          HttpStatus.BAD_REQUEST);
    }
    return tmp.get(0);

  }

  @Override
  public void saveItem(CuentaEntity item) throws ApplicationException {


    List<CuentaEntity> listCuentas = this.repository.findByNumeroCuenta(item.getNumeroCuenta());

    if (listCuentas != null && listCuentas.size() > 0) {
      for (CuentaEntity tmp : listCuentas) {
        if (tmp.getId() != item.getId() && tmp.getNumeroCuenta() != item.getNumeroCuenta()) {
          throw new ApplicationException(null, ApplicationConstant.ERROR_MAX_NUMBER_ACCOUNT,
              HttpStatus.BAD_REQUEST);
        }
      }

    }
    super.saveItem(item);

  }


}
