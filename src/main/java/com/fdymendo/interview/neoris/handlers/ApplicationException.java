package com.fdymendo.interview.neoris.handlers;

import org.springframework.http.HttpStatus;
import lombok.Getter;

public class ApplicationException extends Exception {

  private static final long serialVersionUID = 1L;

  @Getter
  private final HttpStatus httpStatus;

  /**
   * The ApplicationException has a custom parameters that need to be achieved in order to be thrown
   * 
   * @param e The exception to be catch
   * @param message the message from the super class inherited by the ApplicationException
   * @param httpStatus Teh message from the server that allows to know the request input in a
   *        website
   */
  public ApplicationException(Exception e, String message, HttpStatus httpStatus) {
    super(message, e);
    this.httpStatus = httpStatus;
  }

}
