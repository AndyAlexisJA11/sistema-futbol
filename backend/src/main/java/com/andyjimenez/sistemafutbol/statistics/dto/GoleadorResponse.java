package com.andyjimenez.sistemafutbol.statistics.dto;

public class GoleadorResponse {

    private Long idJugador;
    private String nombreJugador;
    private Long idEquipo;
    private String nombreEquipo;
    private Long goles;


    public GoleadorResponse(Long idJugador, String nombreJugador, Long idEquipo, String nombreEquipo, Long goles) {
        this.idJugador = idJugador;
        this.nombreJugador = nombreJugador;
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.goles = goles;
    }

    public Long getIdJugador() {
        return idJugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public Long getGoles() {
        return goles;
    }
}
