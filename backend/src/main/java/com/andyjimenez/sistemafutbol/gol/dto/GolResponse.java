package com.andyjimenez.sistemafutbol.gol.dto;

public class GolResponse {
    private Long idGol;
    private Long idPartido;
    private Long idJugador;
    private Long idEquipo;

    public GolResponse(
            Long idGol,
            Long idPartido,
            Long idJugador,
            Long idEquipo) {

        this.idGol = idGol;
        this.idPartido = idPartido;
        this.idJugador = idJugador;
        this.idEquipo = idEquipo;
    }

    public Long getIdGol() {
        return idGol;
    }

    public Long getIdPartido() {
        return idPartido;
    }

    public Long getIdJugador() {
        return idJugador;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }
}
