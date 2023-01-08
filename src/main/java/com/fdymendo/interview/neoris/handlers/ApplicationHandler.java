package com.fdymendo.interview.neoris.handlers;

import java.util.NoSuchElementException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.ResponseError;
import com.fdymendo.interview.neoris.utils.ApplicationConstant;
import com.fdymendo.interview.neoris.utils.GenericMethods;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler {

  /***
   * this class throws a possible exception found while doing a web request
   * 
   * @param ex as the stacktrace needs the exception thrown and the request made
   * @param request the web request made
   * @return generic response method responseError500
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseError> exceptionFound(Exception ex, WebRequest request) {
    log.error(ex.getMessage() + " {}", ex);
    return GenericMethods.responseError500(ApplicationConstant.ERROR_MESSAGE_500);
  }



  /***
   *
   * @param ex when found an application exception
   * @param request the web request made
   * @return a log with the message thrown by the errors occurred.
   */
  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<ResponseError> exceptionFound(ApplicationException ex, WebRequest request) {
    log.error(ex.getMessage() + " {}", ex);

    return GenericMethods.genericResponse(ex);
  }

  /**
   *
   * @param ex Data access exception thrown when a result was expected to have at least one row
   * @param request the web request made
   * @return The exception details will already be printed as part of the stacktrace
   */
  @ExceptionHandler({EmptyResultDataAccessException.class, NoSuchElementException.class})
  public ResponseEntity<ResponseDefault> exceptionFound(EmptyResultDataAccessException ex,
      WebRequest request) {
    log.warn(ex.getMessage() + " {}", ex);
    return GenericMethods.responseBadRequest();
  }

}
