package com.andyjimenez.sistemafutbol.matchday.dto;

public class JornadaResponse {

    private Long idJornada;
    private Integer numero;
    private Long idTorneo;

    public JornadaResponse(
            Long idJornada,
            Integer numero,
            Long idTorneo
    ) {
        this.idJornada = idJornada;
        this.numero = numero;
        this.idTorneo = idTorneo;
    }

    public Long getIdJornada() {
        return idJornada;
    }

    public Integer getNumero() {
        return numero;
    }

    public Long getIdTorneo() {
        return idTorneo;
    }
}
