package com.andyjimenez.sistemafutbol.tournament.dto;

import com.andyjimenez.sistemafutbol.tournament.EstadoTorneo;

import java.time.LocalDate;

public class TorneoResponse {

    private Long idTorneo;
    private String nombre;
    private LocalDate fechaInicio;
    private EstadoTorneo estado;
    private Integer jornadaLimiteInscripcion;
    private Long idTemporada;

    public TorneoResponse(Long idTorneo, String nombre, LocalDate fechaInicio, EstadoTorneo estado, Integer jornadaLimiteInscripcion, Long idTemporada) {
        this.idTorneo = idTorneo;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.jornadaLimiteInscripcion = jornadaLimiteInscripcion;
        this.idTemporada = idTemporada;
    }

    public Long getIdTorneo() {
        return idTorneo;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public EstadoTorneo getEstado() {
        return estado;
    }

    public Integer getJornadaLimiteInscripcion() {
        return jornadaLimiteInscripcion;
    }

    public Long getIdTemporada() {
        return idTemporada;
    }
}
