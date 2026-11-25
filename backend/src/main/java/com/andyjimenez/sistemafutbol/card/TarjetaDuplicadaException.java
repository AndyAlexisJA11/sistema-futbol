package com.andyjimenez.sistemafutbol.card;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TarjetaDuplicadaException extends RuntimeException {
    public TarjetaDuplicadaException(Long idJugador, Long idPartido ) {
        super("El jugador con id: " + idJugador + " ya tiene una tarjeta amarilla registrada en el partido " + idPartido);
    }
}
