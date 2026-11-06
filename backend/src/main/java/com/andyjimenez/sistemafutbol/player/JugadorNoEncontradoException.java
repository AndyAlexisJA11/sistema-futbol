package com.andyjimenez.sistemafutbol.player;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JugadorNoEncontradoException extends RuntimeException {
    public JugadorNoEncontradoException(long id) {
        super("No se encontro el jugador con id: " + id);
    }
}
