package com.andyjimenez.sistemafutbol.matchday.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class JornadaRequest {

    @NotNull
    @Min(1)
    private Integer numero;

    @NotNull
    private Long idTorneo;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Long getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Long idTorneo) {
        this.idTorneo = idTorneo;
    }
}
