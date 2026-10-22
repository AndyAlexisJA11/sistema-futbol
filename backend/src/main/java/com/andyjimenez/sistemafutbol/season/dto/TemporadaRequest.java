package com.andyjimenez.sistemafutbol.season.dto;

import com.andyjimenez.sistemafutbol.season.EstadoTemporada;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TemporadaRequest {

    @NotBlank
    private String nombre;

    @NotNull
    @Min(2000)
    private Integer anio;

    @NotNull
    private EstadoTemporada estado;

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Integer getAnio(){
        return anio;
    }

    public void setAnio(Integer anio){
        this.anio = anio;
    }

    public EstadoTemporada getEstado(){
        return estado;
    }

    public void setEstado(EstadoTemporada estado){
        this.estado = estado;
    }


}
