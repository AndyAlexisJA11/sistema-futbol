package com.andyjimenez.sistemafutbol.match.dto;

import com.andyjimenez.sistemafutbol.match.EstadoPartido;

import java.time.LocalDateTime;

public class PartidoResponse {
    private Long idPartido;
    private Long idJornada;
    private Long idEquipoLocal;
    private Long idEquipoVisitante;
    private LocalDateTime fechaHora;
    private EstadoPartido estado;
    private Integer golesLocal;
    private Integer golesVisitante;

    public PartidoResponse(Long idPartido, Long idJornada,
                           Long idEquipoLocal, Long idEquipoVisitante,
                           LocalDateTime fechaHora, EstadoPartido estado,
                           Integer golesLocal, Integer golesVisitante) {
        this.idPartido = idPartido;
        this.idJornada = idJornada;
        this.idEquipoLocal = idEquipoLocal;
        this.idEquipoVisitante = idEquipoVisitante;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public Long getIdPartido() {
        return idPartido;
    }

    public Long getIdJornada() {
        return idJornada;
    }

    public Long getIdEquipoVisitante() {
        return idEquipoVisitante;
    }

    public Long getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public EstadoPartido getEstado() {
        return estado;
    }

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public Integer getGolesVisitante() {
        return golesVisitante;
    }
}
