package com.andyjimenez.sistemafutbol.match;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EquipoNoParticipaEnPartidoException extends RuntimeException {
    public EquipoNoParticipaEnPartidoException(Long idEquipo, Long idTorneo) {
        super("El equipo con id: " + idEquipo + " no participa en el torneo con id: " + idTorneo);
    }
}
