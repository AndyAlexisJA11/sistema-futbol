package com.andyjimenez.sistemafutbol.participation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ParticipacionDuplicadaException extends RuntimeException {


    public ParticipacionDuplicadaException(Long idTorneo, Long idEquipo) {
        super("El equipo con id: " + idEquipo
                + " ya esta inscrito en el torneo con el id: "+ idTorneo);
    }
}
