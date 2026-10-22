package com.andyjimenez.sistemafutbol.tournament.dto;

import com.andyjimenez.sistemafutbol.tournament.EstadoTorneo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TorneoRequest {

    @NotBlank
    private String nombre;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private EstadoTorneo estado;

    @NotNull
    @Min(1)
    private Integer jornadaLimiteInscripcion;

    @NotNull
    private Long idTemporada;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public EstadoTorneo getEstado() {
        return estado;
    }

    public void setEstado(EstadoTorneo estado) {
        this.estado = estado;
    }

    public Integer getJornadaLimiteInscripcion() {
        return jornadaLimiteInscripcion;
    }

    public void setJornadaLimiteInscripcion(Integer jornadaLimiteInscripcion) {
        this.jornadaLimiteInscripcion = jornadaLimiteInscripcion;
    }

    public Long getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(Long idTemporada) {
        this.idTemporada = idTemporada;
    }
}
