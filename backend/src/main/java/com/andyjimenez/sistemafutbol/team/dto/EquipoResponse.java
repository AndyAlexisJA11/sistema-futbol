package com.andyjimenez.sistemafutbol.team.dto;

import com.andyjimenez.sistemafutbol.team.EstadoEquipo;

public class EquipoResponse {

    private Long idEquipo;
    private String nombre;
    private String nombreCorto;
    private String cancha;
    private EstadoEquipo estado;

    public EquipoResponse(Long idEquipo, String nombre, String nombreCorto, String cancha, EstadoEquipo estado) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.cancha = cancha;
        this.estado = estado;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public String getCancha() {
        return cancha;
    }

    public EstadoEquipo getEstado() {
        return estado;
    }
}
