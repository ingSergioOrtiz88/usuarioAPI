package com.example.usuarioapi.infrastructure.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class GeneralException extends RuntimeException {

    private MessageError messageError;
    private HttpStatus httpStatus;

    public GeneralException(String message) {
        messageError = new MessageError(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
