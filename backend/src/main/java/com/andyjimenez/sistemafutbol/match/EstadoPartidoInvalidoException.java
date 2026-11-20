package com.andyjimenez.sistemafutbol.match;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EstadoPartidoInvalidoException extends RuntimeException {
    public EstadoPartidoInvalidoException(String message) {
        super(message);
    }
}
