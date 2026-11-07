package com.andyjimenez.sistemafutbol.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InscripcionNoEncontradaException extends RuntimeException {
    public InscripcionNoEncontradaException(Long id) {
        super("No se encontro la inscripcion con id: " + id);
    }
}
