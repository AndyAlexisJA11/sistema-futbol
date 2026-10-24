package com.andyjimenez.sistemafutbol.participation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParticipacionNoEncontradaException extends RuntimeException {
    public ParticipacionNoEncontradaException(Long id) {
        super("No se encontro la participacion con id: " + id);
    }
}
