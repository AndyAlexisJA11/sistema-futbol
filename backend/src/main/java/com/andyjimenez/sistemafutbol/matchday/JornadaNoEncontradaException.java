package com.andyjimenez.sistemafutbol.matchday;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JornadaNoEncontradaException extends RuntimeException {
    public JornadaNoEncontradaException(Long id) {
        super("La jornada con el id: " + id + " no se encuentra");
    }
}
