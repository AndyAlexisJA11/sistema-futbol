package com.andyjimenez.sistemafutbol.matchday;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JornadaRepository extends JpaRepository<Jornada, Long> {

    boolean existsByTorneo_IdTorneoAndNumero(Long idTorneo, Integer numero);
}
