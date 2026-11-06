package com.andyjimenez.sistemafutbol.player.dto;

import java.time.LocalDate;

public class JugadorResponse {

    private Long idJugador;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private String lugarNacimiento;
    private String documentoIdentificacion;
    private String foto;

    public JugadorResponse(Long idJugador, String nombreCompleto, LocalDate fechaNacimiento, String documentoIdentificacion, String lugarNacimiento, String foto) {
        this.idJugador = idJugador;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.documentoIdentificacion = documentoIdentificacion;
        this.lugarNacimiento = lugarNacimiento;
        this.foto = foto;
    }

    public Long getIdJugador() {
        return idJugador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public String getDocumentoIdentificacion() {
        return documentoIdentificacion;
    }

    public String getFoto() {
        return foto;
    }
}
