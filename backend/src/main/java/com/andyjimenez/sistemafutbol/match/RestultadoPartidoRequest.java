package com.andyjimenez.sistemafutbol.match;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RestultadoPartidoRequest {

    @NotNull
    @Min(0)
    private Integer golesLocal;

    @NotNull
    @Min(0)
    private Integer golesVisitante;

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(Integer golesLocal) {
        this.golesLocal = golesLocal;
    }

    public Integer getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(Integer golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
}
