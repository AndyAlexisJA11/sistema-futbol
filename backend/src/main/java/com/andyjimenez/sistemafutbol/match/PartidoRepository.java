package com.andyjimenez.sistemafutbol.match;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidoRepository extends JpaRepository<Partido, Long> {

    List<Partido> findByJornada_Torneo_IdTorneoAndEstado(
            Long idTorneo,
            EstadoPartido estado
    );
}
