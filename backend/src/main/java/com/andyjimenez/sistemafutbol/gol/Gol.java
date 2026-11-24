package com.andyjimenez.sistemafutbol.gol;

import com.andyjimenez.sistemafutbol.match.Partido;
import com.andyjimenez.sistemafutbol.player.Jugador;
import com.andyjimenez.sistemafutbol.team.Equipo;
import jakarta.persistence.*;

@Entity
@Table(name = "gol")
public class Gol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gol")
    private Long idGol;

    @ManyToOne
    @JoinColumn(name = "id_partido", nullable = false)
    private Partido partido;

    @ManyToOne
    @JoinColumn(name = "id_jugador", nullable = false)
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_equipo", nullable = false)
    private Equipo equipo;

    public Gol(){}

    public Long getIdGol() {
        return idGol;
    }

    public void setIdGol(Long idGol) {
        this.idGol = idGol;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
