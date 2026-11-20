package com.andyjimenez.sistemafutbol.match;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartidoNoEncontradoException extends RuntimeException {
    public PartidoNoEncontradoException(Long id) {
        super("No se encontro el partido con el id: " + id);
    }
}
