package com.andyjimenez.sistemafutbol.sancion;

import com.andyjimenez.sistemafutbol.player.Jugador;
import com.andyjimenez.sistemafutbol.tournament.Torneo;
import jakarta.persistence.*;

@Entity
@Table(name = "sancion")
public class Sancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sancion", nullable = false)
    private Long idSancion;

    @ManyToOne
    @JoinColumn(name = "id_jugador",nullable = false)
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_torneo",nullable = false)
    private Torneo torneo;

    @Column(name = "partidos_suspension", nullable = false)
    private Integer partidosSuspension;

    @Column(name = "motivo",nullable = false, length = 255)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado",nullable = false, length = 20)
    private EstadoSancion estado;

    public Sancion(){}

    public Long getIdSancion() {
        return idSancion;
    }

    public void setIdSancion(Long idSancion) {
        this.idSancion = idSancion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Integer getPartidosSuspension() {
        return partidosSuspension;
    }

    public void setPartidosSuspension(Integer partidosSuspension) {
        this.partidosSuspension = partidosSuspension;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoSancion getEstado() {
        return estado;
    }

    public void setEstado(EstadoSancion estado) {
        this.estado = estado;
    }
}
