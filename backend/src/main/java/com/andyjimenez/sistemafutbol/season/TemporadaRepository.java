package com.andyjimenez.sistemafutbol.season;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TemporadaRepository extends JpaRepository<Temporada, Long> {

    boolean existsByAnio(Integer anio);

    boolean existsByAnioAndIdTemporadaNot(Integer anio, Long idTemporada);

}
