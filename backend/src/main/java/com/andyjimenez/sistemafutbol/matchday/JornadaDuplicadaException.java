package com.andyjimenez.sistemafutbol.matchday;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class JornadaDuplicadaException extends RuntimeException {
    public JornadaDuplicadaException(Long idTorneo, Integer numero) {
        super("Ya existe la jornada: " + numero + " en el torneo con id: " + idTorneo);
    }
}
