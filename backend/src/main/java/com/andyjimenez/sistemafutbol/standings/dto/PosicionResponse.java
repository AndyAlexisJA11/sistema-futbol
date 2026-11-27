package com.andyjimenez.sistemafutbol.standings.dto;

public class PosicionResponse {
    private Long idEquipo;
    private String nombreEquipo;
    private Integer partidosJugados;
    private Integer ganados;
    private Integer empatados;
    private Integer perdidos;
    private Integer golesFavor;
    private Integer golesContra;
    private Integer diferenciaGoles;
    private Integer puntos;

    public PosicionResponse(
            Long idEquipo,
            String nombreEquipo,
            Integer partidosJugados,
            Integer ganados,
            Integer empatados,
            Integer perdidos,
            Integer golesFavor,
            Integer golesContra,
            Integer diferenciaGoles,
            Integer puntos) {

        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.partidosJugados = partidosJugados;
        this.ganados = ganados;
        this.empatados = empatados;
        this.perdidos = perdidos;
        this.golesFavor = golesFavor;
        this.golesContra = golesContra;
        this.diferenciaGoles = diferenciaGoles;
        this.puntos = puntos;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public Integer getPartidosJugados() {
        return partidosJugados;
    }

    public Integer getGanados() {
        return ganados;
    }

    public Integer getEmpatados() {
        return empatados;
    }

    public Integer getPerdidos() {
        return perdidos;
    }

    public Integer getGolesFavor() {
        return golesFavor;
    }

    public Integer getGolesContra() {
        return golesContra;
    }

    public Integer getDiferenciaGoles() {
        return diferenciaGoles;
    }

    public Integer getPuntos() {
        return puntos;
    }
}
