package com.andyjimenez.sistemafutbol.season;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TemporadaDuplicadaException extends RuntimeException {

    public TemporadaDuplicadaException(Integer anio){
        super("Ya existe una temporada para el año: " + anio);
    }
}
