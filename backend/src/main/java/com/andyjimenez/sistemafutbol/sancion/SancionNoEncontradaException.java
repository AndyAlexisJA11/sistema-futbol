package com.andyjimenez.sistemafutbol.sancion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SancionNoEncontradaException extends RuntimeException {
    public SancionNoEncontradaException(Long id) {
        super("No se encontro la sancion con id: " + id);
    }
}
