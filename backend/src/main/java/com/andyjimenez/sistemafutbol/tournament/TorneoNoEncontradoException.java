package com.andyjimenez.sistemafutbol.tournament;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TorneoNoEncontradoException extends RuntimeException{

    public TorneoNoEncontradoException(Long id){
        super("No se encontro el torneo con el id: " + id);
    }

}
