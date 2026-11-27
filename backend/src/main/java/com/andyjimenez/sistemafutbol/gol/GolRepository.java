package com.andyjimenez.sistemafutbol.gol;

import com.andyjimenez.sistemafutbol.statistics.dto.GoleadorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GolRepository extends JpaRepository<Gol, Long> {

    @Query("""
        SELECT new com.andyjimenez.sistemafutbol.statistics.dto.GoleadorResponse(
            g.jugador.idJugador,
            g.jugador.nombreCompleto,
            g.equipo.idEquipo,
            g.equipo.nombre,
            COUNT(g)
        )
        FROM Gol g
        WHERE g.partido.jornada.torneo.idTorneo = :idTorneo
        GROUP BY
            g.jugador.idJugador,
            g.jugador.nombreCompleto,
            g.equipo.idEquipo,
            g.equipo.nombre
        ORDER BY COUNT(g) DESC""")
    List<GoleadorResponse> obtenerGoleadoresPorTorneo(
            @Param("idTorneo") Long idTorneo
    );
}
