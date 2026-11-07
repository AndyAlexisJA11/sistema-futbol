package com.andyjimenez.sistemafutbol.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InscripcionDuplicadaException extends RuntimeException {
    public InscripcionDuplicadaException(Long idJugador, Long idTorneo) {
        super("El jugador con id: " + idJugador + " ya esta inscrito en el torneo con id: " + idTorneo);
    }
}
