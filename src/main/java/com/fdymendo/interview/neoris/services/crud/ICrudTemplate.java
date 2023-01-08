package com.fdymendo.interview.neoris.services.crud;

import java.util.List;
import com.fdymendo.interview.neoris.handlers.ApplicationException;

public interface ICrudTemplate<I> {

  void saveItem(I item) throws ApplicationException;

  void deleteItem(int id);

  I getItem(int id) throws ApplicationException;

  List<I> getAllItems();

}
