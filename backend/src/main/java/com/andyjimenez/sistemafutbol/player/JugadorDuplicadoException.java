package com.andyjimenez.sistemafutbol.player;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class JugadorDuplicadoException extends RuntimeException {

    public JugadorDuplicadoException(String documentoIdentificacion) {
        super("Ya existe un jugador con el documento: " + documentoIdentificacion);
    }
}
