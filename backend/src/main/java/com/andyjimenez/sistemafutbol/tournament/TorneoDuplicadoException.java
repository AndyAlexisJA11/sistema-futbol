package com.andyjimenez.sistemafutbol.tournament;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TorneoDuplicadoException extends RuntimeException {

    public TorneoDuplicadoException(Long idTemporada){
        super("Ya existe un torneo para la temporada con id: " + idTemporada);
    }


}
