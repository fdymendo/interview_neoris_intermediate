package com.fdymendo.interview.neoris.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.fdymendo.interview.neoris.entity.MovimientosEntity;

public interface IMovimientoRepository extends JpaRepository<MovimientosEntity, Integer> {

  @Query(nativeQuery = true,
      value = "select * from movimientos where fecha_creacion between ? and ?")
  List<MovimientosEntity> findByFechaCreacionBetween(String fechaCreacion, String fechaFinal);

}
