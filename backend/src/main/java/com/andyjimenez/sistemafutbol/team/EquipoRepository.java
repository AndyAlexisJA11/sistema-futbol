package com.andyjimenez.sistemafutbol.team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdEquipoNot(String nombre, Long idEquipo);
}
