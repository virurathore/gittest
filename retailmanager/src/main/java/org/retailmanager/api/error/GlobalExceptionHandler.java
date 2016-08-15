package org.retailmanager.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Viru
 *
 *  Handle all kind of exceptions
 *
 */
@ControllerAdvice  
@RestController  
public class GlobalExceptionHandler {  
  
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(value = Exception.class)  
    public String handleException(Exception e){return e.getMessage();}  
  
  
}  