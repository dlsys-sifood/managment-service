package com.dlsys.sifood.ms.error;

import com.dlsys.sifood.ms.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(CannotCreateTransactionException.class)
    public final ResponseEntity<Object> handleException(CannotCreateTransactionException ex, WebRequest request){

        GenericResponse response = new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Internal Server Error", GenericResponse.toList(ex.getMessage()));

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request){

        GenericResponse response = new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Internal Server Error", GenericResponse.toList(ex.getMessage()));

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
