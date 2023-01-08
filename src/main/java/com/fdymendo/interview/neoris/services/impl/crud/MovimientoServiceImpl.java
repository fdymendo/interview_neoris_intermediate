package com.fdymendo.interview.neoris.services.impl.crud;

import java.util.List;
import org.springframework.stereotype.Service;
import com.fdymendo.interview.neoris.entity.MovimientosEntity;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.repository.IMovimientoRepository;
import com.fdymendo.interview.neoris.services.crud.ACrudServiceTemplate;

@Service
public class MovimientoServiceImpl
    extends ACrudServiceTemplate<IMovimientoRepository, MovimientosEntity> {

  public MovimientoServiceImpl(IMovimientoRepository repository) {
    super(repository);
  }

  @Override
  public MovimientosEntity updateItem(MovimientosEntity item, int id) throws ApplicationException {
    MovimientosEntity tmp = this.getItem(id);
    item.setId(tmp.getId());
    return this.repository.save(item);
  }

  public List<MovimientosEntity> findByFechaCreacionBetween(String fechaCreacion,
      String fechaFinal) {
    return this.repository.findByFechaCreacionBetween(fechaCreacion, fechaFinal);
  }

}
