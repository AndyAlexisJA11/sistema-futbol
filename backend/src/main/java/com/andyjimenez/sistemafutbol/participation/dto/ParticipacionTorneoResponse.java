package com.andyjimenez.sistemafutbol.participation.dto;

public class ParticipacionTorneoResponse {

    private Long idParticipacion;
    private Long idTorneo;
    private Long idEquipo;

    public ParticipacionTorneoResponse(Long idParticipacion, Long idTorneo, Long idEquipo) {
        this.idParticipacion = idParticipacion;
        this.idTorneo = idTorneo;
        this.idEquipo = idEquipo;
    }

    public Long getIdParticipacion() {
        return idParticipacion;
    }

    public Long getIdTorneo() {
        return idTorneo;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }
}
