package com.andyjimenez.sistemafutbol.tournament;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TorneoRepository extends JpaRepository<Torneo, Long> {

    boolean existsByTemporada_IdTemporada(Long idTemporada);
}
