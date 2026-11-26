package com.andyjimenez.sistemafutbol.sancion.dto;

import com.andyjimenez.sistemafutbol.sancion.EstadoSancion;

public class SancionResponse {
    private Long idSancion;
    private Long idJugador;
    private Long idTorneo;
    private Integer partidosSuspension;
    private String motivo;
    private EstadoSancion estado;
    private Integer partidosPendientes;

    public SancionResponse(Long idSancion,
                           Long idJugador,
                           Long idTorneo,
                           Integer partidosSuspension,
                           Integer partidosPendientes,
                           String motivo,
                           EstadoSancion estado)
                            {
        this.idSancion = idSancion;
        this.idJugador = idJugador;
        this.idTorneo = idTorneo;
        this.partidosSuspension = partidosSuspension;
        this.partidosPendientes = partidosPendientes;
        this.motivo = motivo;
        this.estado = estado;

    }

    public Long getIdSancion() {
        return idSancion;
    }

    public Long getIdJugador() {
        return idJugador;
    }

    public Long getIdTorneo() {
        return idTorneo;
    }

    public Integer getPartidosSuspension() {
        return partidosSuspension;
    }

    public String getMotivo() {
        return motivo;
    }

    public EstadoSancion getEstado() {
        return estado;
    }

    public Integer getPartidosPendientes() {
        return partidosPendientes;
    }
}
