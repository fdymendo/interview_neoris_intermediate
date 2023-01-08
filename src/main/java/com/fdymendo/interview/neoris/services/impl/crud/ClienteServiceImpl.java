package com.fdymendo.interview.neoris.services.impl.crud;

import org.springframework.stereotype.Service;
import com.fdymendo.interview.neoris.entity.ClienteEntity;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.repository.IClienteRepository;
import com.fdymendo.interview.neoris.services.crud.ACrudServiceTemplate;

@Service
public class ClienteServiceImpl extends ACrudServiceTemplate<IClienteRepository, ClienteEntity> {

  public ClienteServiceImpl(IClienteRepository repository) {
    super(repository);
  }

  @Override
  public ClienteEntity updateItem(ClienteEntity item, int id) throws ApplicationException {
    ClienteEntity tmp = this.getItem(id);
    item.setId(tmp.getId());
    return this.repository.save(item);
  }

}
