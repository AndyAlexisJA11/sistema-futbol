package com.andyjimenez.sistemafutbol.registration.dto;

import com.andyjimenez.sistemafutbol.registration.EstadoInscripcion;
import jakarta.validation.constraints.NotNull;

public class InscripcionRequest {

    @NotNull
    private Long idJugador;

    @NotNull
    private Long idEquipo;

    @NotNull
    private Long idTorneo;


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

    public Long getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Long idTorneo) {
        this.idTorneo = idTorneo;
    }

}
