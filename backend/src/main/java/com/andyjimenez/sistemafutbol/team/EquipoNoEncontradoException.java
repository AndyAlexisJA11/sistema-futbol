package com.andyjimenez.sistemafutbol.team;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipoNoEncontradoException extends RuntimeException {
    public EquipoNoEncontradoException(Long id) {
        super("No se encontro el equipo con id: " + id);
    }
}
