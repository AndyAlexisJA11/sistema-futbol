package com.andyjimenez.sistemafutbol.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EquipoNoParticipaEnTorneoException extends RuntimeException {
    public EquipoNoParticipaEnTorneoException(Long idEquipo, Long idTorneo) {
        super("El equipo con id " + idEquipo + " no participa en el torneo con id: " + idTorneo);
    }
}
