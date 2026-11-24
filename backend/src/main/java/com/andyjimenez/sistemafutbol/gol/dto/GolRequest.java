package com.andyjimenez.sistemafutbol.gol.dto;

import jakarta.validation.constraints.NotNull;

public class GolRequest {
    @NotNull
    private Long idPartido;

    @NotNull
    private Long idJugador;

    @NotNull
    private Long idEquipo;

    public Long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

    public Long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }
}
