package com.andyjimenez.sistemafutbol.card.dto;

import com.andyjimenez.sistemafutbol.card.TipoTarjeta;

public class TarjetaResponse {
    private Long idTarjeta;
    private Long idPartido;
    private Long idJugador;
    private Long idEquipo;
    private TipoTarjeta tipo;

    public TarjetaResponse(
            Long idTarjeta,
            Long idPartido,
            Long idJugador,
            Long idEquipo,
            TipoTarjeta tipo) {

        this.idTarjeta = idTarjeta;
        this.idPartido = idPartido;
        this.idJugador = idJugador;
        this.idEquipo = idEquipo;
        this.tipo = tipo;
    }

    public Long getIdTarjeta() {
        return idTarjeta;
    }

    public Long getIdPartido() {
        return idPartido;
    }

    public Long getIdJugador() {
        return idJugador;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public TipoTarjeta getTipo() {
        return tipo;
    }
}
