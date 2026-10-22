package com.andyjimenez.sistemafutbol.season.dto;

import com.andyjimenez.sistemafutbol.season.EstadoTemporada;

public class TemporadaResponse {

    private Long idTemporada;
    private String nombre;
    private Integer anio;
    private EstadoTemporada estado;

    public TemporadaResponse(Long idTemporada, String nombre, Integer anio, EstadoTemporada estado) {
        this.idTemporada = idTemporada;
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }

    public Long getIdTemporada() {
        return idTemporada;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getAnio() {
        return anio;
    }

    public EstadoTemporada getEstado() {
        return estado;
    }
}
