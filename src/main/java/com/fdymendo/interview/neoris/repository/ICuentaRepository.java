package com.fdymendo.interview.neoris.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fdymendo.interview.neoris.entity.CuentaEntity;

public interface ICuentaRepository extends JpaRepository<CuentaEntity, Integer> {
  List<CuentaEntity> findByNumeroCuenta(String numeroCuenta);
}
