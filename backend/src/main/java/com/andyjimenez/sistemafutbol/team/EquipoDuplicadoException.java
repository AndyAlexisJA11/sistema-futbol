package com.andyjimenez.sistemafutbol.team;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EquipoDuplicadoException extends RuntimeException {
    public EquipoDuplicadoException(String nombre) {
        super("Ya existe un equipo con el nombre: " + nombre) ;
    }
}
