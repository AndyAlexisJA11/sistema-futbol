package com.andyjimenez.sistemafutbol.sancion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EstadoSancionInvalidoException extends RuntimeException {
    public EstadoSancionInvalidoException(String message) {
        super(message);
    }
}
