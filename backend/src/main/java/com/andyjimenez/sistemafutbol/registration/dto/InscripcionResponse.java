package com.andyjimenez.sistemafutbol.registration.dto;

import com.andyjimenez.sistemafutbol.registration.EstadoInscripcion;

public class InscripcionResponse {

    private Long idInscripcion;
    private Long idJugador;
    private Long idEquipo;
    private Long idTorneo;
    private EstadoInscripcion estado;

    public InscripcionResponse(Long idInscripcion, Long idJugador,Long idEquipo,Long idTorneo,EstadoInscripcion estado) {
        this.idInscripcion = idInscripcion;
        this.idJugador = idJugador;
        this.estado = estado;
        this.idTorneo = idTorneo;
        this.idEquipo = idEquipo;
    }

    public Long getIdInscripcion() {
        return idInscripcion;
    }

    public Long getIdJugador() {
        return idJugador;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public EstadoInscripcion getEstado() {
        return estado;
    }

    public Long getIdTorneo() {
        return idTorneo;
    }
}
