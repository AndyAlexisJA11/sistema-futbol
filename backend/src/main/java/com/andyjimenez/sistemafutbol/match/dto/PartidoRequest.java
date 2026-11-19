package com.andyjimenez.sistemafutbol.match.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class PartidoRequest {

    @NotNull
    private Long idJornada;

    @NotNull
    private Long idEquipoLocal;

    @NotNull
    private Long idEquipoVisitante;

    @NotNull
    private LocalDateTime fechaHora;

    public Long getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Long idJornada) {
        this.idJornada = idJornada;
    }

    public Long getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(Long idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    public Long getIdEquipoVisitante() {
        return idEquipoVisitante;
    }

    public void setIdEquipoVisitante(Long idEquipoVisitante) {
        this.idEquipoVisitante = idEquipoVisitante;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
