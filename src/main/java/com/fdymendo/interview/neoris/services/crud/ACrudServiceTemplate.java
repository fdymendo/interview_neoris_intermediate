package com.fdymendo.interview.neoris.services.crud;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.utils.ApplicationConstant;

/**
 * An interface that extends from JpaRepository and has save , deleteById, findAll and findById
 * functions.
 */
public abstract class ACrudServiceTemplate<T extends JpaRepository<S, Integer>, S>
    implements ICrudTemplate<S> {

  protected T repository;

  public ACrudServiceTemplate(T repository) {
    this.repository = repository;
  }

  @Override
  public void saveItem(S item) throws ApplicationException {
    this.repository.save(item);
  }

  @Override
  public void deleteItem(int id) {
    this.repository.deleteById(id);

  }

  @Override
  public S getItem(int id) throws ApplicationException {
    Optional<S> tmp = repository.findById(id);
    if (tmp.isEmpty()) {
      throw new ApplicationException(null, ApplicationConstant.ERROR_NOT_INFO,
          HttpStatus.BAD_REQUEST);
    }
    return tmp.get();
  }

  @Override
  public List<S> getAllItems() {
    return repository.findAll();
  }

  public abstract S updateItem(S item, int id) throws ApplicationException;


}
