package com.example.usuarioapi.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageError {

    // int codeError;
    String message;
}
