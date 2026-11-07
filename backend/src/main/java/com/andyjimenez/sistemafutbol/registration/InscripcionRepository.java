package com.andyjimenez.sistemafutbol.registration;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    boolean existsByJugador_IdJugadorAndTorneo_IdTorneo(Long idJugador, Long idTorneo);
}
