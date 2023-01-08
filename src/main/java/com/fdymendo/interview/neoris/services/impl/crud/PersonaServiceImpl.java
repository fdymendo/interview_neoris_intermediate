package com.fdymendo.interview.neoris.services.impl.crud;

import org.springframework.stereotype.Service;
import com.fdymendo.interview.neoris.entity.PersonaEntity;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.repository.IPersonaRepository;
import com.fdymendo.interview.neoris.services.crud.ACrudServiceTemplate;

@Service
public class PersonaServiceImpl extends ACrudServiceTemplate<IPersonaRepository, PersonaEntity> {

  public PersonaServiceImpl(IPersonaRepository repository) {
    super(repository);
  }

  @Override
  public PersonaEntity updateItem(PersonaEntity item, int id) throws ApplicationException {
    PersonaEntity tmp = this.getItem(id);
    item.setId(tmp.getId());
    return this.repository.save(item);
  }

}
