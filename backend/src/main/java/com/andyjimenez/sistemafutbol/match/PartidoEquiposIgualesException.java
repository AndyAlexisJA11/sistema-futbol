package com.andyjimenez.sistemafutbol.match;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PartidoEquiposIgualesException extends RuntimeException {
    public PartidoEquiposIgualesException( ) {
        super("Un equipo no puede jugar contra si mismo");
    }
}
