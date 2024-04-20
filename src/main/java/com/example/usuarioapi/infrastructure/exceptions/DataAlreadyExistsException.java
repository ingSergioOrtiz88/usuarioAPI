package com.example.usuarioapi.infrastructure.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class DataAlreadyExistsException extends RuntimeException {

    private MessageError messageError;
    private HttpStatus httpStatus;

    public DataAlreadyExistsException(String message) {
        messageError = new MessageError(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }
}
