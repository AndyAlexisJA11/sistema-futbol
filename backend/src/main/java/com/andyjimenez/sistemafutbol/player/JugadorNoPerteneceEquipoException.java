package com.andyjimenez.sistemafutbol.player;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class JugadorNoPerteneceEquipoException extends RuntimeException {
    public JugadorNoPerteneceEquipoException(Long idJugador, Long idEquipo) {
        super("El jugador con id: " + idJugador + " no pertenece al equipo con id: " + idEquipo);
    }
}
