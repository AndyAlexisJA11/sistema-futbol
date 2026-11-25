package com.andyjimenez.sistemafutbol.sancion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SancionRequest {
    @NotNull
    private Long idJugador;

    @NotNull
    private Long idTorneo;

    @NotNull
    @Min(1)
    private Integer partidosSuspension;

    @NotBlank
    private String motivo;

    public Long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
    }

    public Long getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Long idTorneo) {
        this.idTorneo = idTorneo;
    }

    public Integer getPartidosSuspension() {
        return partidosSuspension;
    }

    public void setPartidosSuspension(Integer partidosSuspension) {
        this.partidosSuspension = partidosSuspension;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
