package com.andyjimenez.sistemafutbol.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LimiteJugadoresException extends RuntimeException {
    public LimiteJugadoresException(Long idEquipo, Long idTorneo) {
        super("El equipo con id: " + idEquipo + " ya alcanzo el limite de 24 jugadores en el torneo con " +
                "id: " + idTorneo);
    }
}
