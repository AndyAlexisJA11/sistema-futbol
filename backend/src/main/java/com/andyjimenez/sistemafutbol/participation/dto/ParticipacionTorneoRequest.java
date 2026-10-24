package com.andyjimenez.sistemafutbol.participation.dto;

import jakarta.validation.constraints.NotNull;

public class ParticipacionTorneoRequest {

    @NotNull
    private Long idTorneo;

    @NotNull
    private Long idEquipo;

    public Long getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Long idTorneo) {
        this.idTorneo = idTorneo;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }
}
