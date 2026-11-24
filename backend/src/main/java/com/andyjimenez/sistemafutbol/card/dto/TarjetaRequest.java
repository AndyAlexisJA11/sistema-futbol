package com.andyjimenez.sistemafutbol.card.dto;

import com.andyjimenez.sistemafutbol.card.TipoTarjeta;
import jakarta.validation.constraints.NotNull;

public class TarjetaRequest {
    @NotNull
    private Long idPartido;

    @NotNull
    private Long idJugador;

    @NotNull
    private Long idEquipo;

    @NotNull
    private TipoTarjeta tipo;

    public Long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

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

    public TipoTarjeta getTipo() {
        return tipo;
    }

    public void setTipo(TipoTarjeta tipo) {
        this.tipo = tipo;
    }
}
