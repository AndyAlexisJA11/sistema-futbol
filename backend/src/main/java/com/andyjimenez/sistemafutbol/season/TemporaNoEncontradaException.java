package com.andyjimenez.sistemafutbol.season;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TemporaNoEncontradaException extends RuntimeException{

    public TemporaNoEncontradaException(Long id){
        super("No se encontro la temporada con id: " + id);
    }
}
