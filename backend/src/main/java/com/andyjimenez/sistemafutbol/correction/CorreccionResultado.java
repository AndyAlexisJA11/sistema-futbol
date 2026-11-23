package com.andyjimenez.sistemafutbol.correction;

import com.andyjimenez.sistemafutbol.match.Partido;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "correccion_resultado")
public class CorreccionResultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_correccion")
    private Long idCorreccion;

    @ManyToOne
    @JoinColumn(name = "id_partido", nullable = false)
    private Partido partido;

    @Column(name = "goles_local_anterior", nullable = false)
    private Integer golesLocalAnterior;

    @Column(name = "goles_visitante_anterior", nullable = false)
    private Integer golesVisitanteAnterior;

    @Column(name = "goles_local_nuevo", nullable = false)
    private Integer golesLocalNuevo;

    @Column(name = "goles_visitante_nuevo", nullable = false)
    private Integer golesVisitaneNuevo;

    @Column(name = "motivo", nullable = false)
    private String motivo;

    @Column(name = "fecha_correccion", nullable = false)
    private LocalDateTime fechaCorreccion;

    public CorreccionResultado(){}

    public Long getIdCorreccion() {
        return idCorreccion;
    }

    public void setIdCorreccion(Long idCorreccion) {
        this.idCorreccion = idCorreccion;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Integer getGolesLocalAnterior() {
        return golesLocalAnterior;
    }

    public void setGolesLocalAnterior(Integer golesLocalAnterior) {
        this.golesLocalAnterior = golesLocalAnterior;
    }

    public Integer getGolesVisitanteAnterior() {
        return golesVisitanteAnterior;
    }

    public void setGolesVisitanteAnterior(Integer golesVisitanteAnterior) {
        this.golesVisitanteAnterior = golesVisitanteAnterior;
    }

    public Integer getGolesLocalNuevo() {
        return golesLocalNuevo;
    }

    public void setGolesLocalNuevo(Integer golesLocalNuevo) {
        this.golesLocalNuevo = golesLocalNuevo;
    }

    public Integer getGolesVisitaneNuevo() {
        return golesVisitaneNuevo;
    }

    public void setGolesVisitaneNuevo(Integer golesVisitaneNuevo) {
        this.golesVisitaneNuevo = golesVisitaneNuevo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFechaCorreccion() {
        return fechaCorreccion;
    }

    public void setFechaCorreccion(LocalDateTime fechaCorreccion) {
        this.fechaCorreccion = fechaCorreccion;
    }
}
