package com.example.usuarioapi.infrastructure.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserExceptionHandler {


    @ExceptionHandler(GeneralException.class)
    protected ResponseEntity<Object> handlerBadRequst(GeneralException generalException, WebRequest webRequest) {

        return ResponseEntity.status(generalException.getHttpStatus())
                .body(generalException.getMessageError());
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    protected ResponseEntity<Object> handlerEmailReadyExistBadRequst(DataAlreadyExistsException exception, WebRequest webRequest) {

        return ResponseEntity.status(exception.getHttpStatus())
                .body(exception.getMessageError());
    }


}
