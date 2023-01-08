package com.fdymendo.interview.neoris.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fdymendo.interview.neoris.handlers.ApplicationException;
import com.fdymendo.interview.neoris.model.ResponseDefault;
import com.fdymendo.interview.neoris.model.ResponseError;

public class GenericMethods {

  public static ResponseEntity<ResponseDefault> responseOk(ResponseDefault responseDefault) {
    return ResponseEntity.ok(responseDefault);
  }

  public static ResponseEntity<ResponseDefault> responseNoContent() {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  public static ResponseEntity<ResponseDefault> responseBadRequest() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
  }

  public static ResponseEntity<ResponseError> responseError500(String message) {
    return ResponseEntity.internalServerError().body(new ResponseError(message));
  }

  public static ResponseEntity<ResponseError> genericResponse(
      ApplicationException applicationException) {
    return ResponseEntity.status(applicationException.getHttpStatus())
        .body(new ResponseError(applicationException.getMessage()));
  }

  public static void generateError() throws ApplicationException {
    throw new ApplicationException(null, ApplicationConstant.ERROR_NEED_MORE_INFO,
        HttpStatus.BAD_REQUEST);
  }

  public static String generarFormatoFecha(Date fecha) throws ParseException {
    DateFormat dateFormatOut = new SimpleDateFormat("dd/mm/YYYY");
    return dateFormatOut.format(fecha);
  }

}
