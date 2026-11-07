package com.andyjimenez.sistemafutbol.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EstadoInscripcionInvalidoException extends RuntimeException {
    public EstadoInscripcionInvalidoException(String message) {
        super(message);
    }
}
