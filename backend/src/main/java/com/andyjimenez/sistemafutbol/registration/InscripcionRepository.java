package com.andyjimenez.sistemafutbol.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    boolean existsByJugador_IdJugadorAndTorneo_IdTorneo(Long idJugador, Long idTorneo);

    long countByEquipo_IdEquipoAndTorneo_IdTorneo(
            Long idEquipo,
            Long idTorneo
    );

    long countByEquipo_IdEquipoAndTorneo_IdTorneoAndEstadoNot(
            Long idEquipo,
            Long idTorneo,
            EstadoInscripcion estado
    );

    boolean existsByJugador_IdJugadorAndEquipo_IdEquipoAndTorneo_IdTorneoAndEstado(
            Long idJugador,
            Long idEquipo,
            Long idTorneo,
            EstadoInscripcion estado
    );

    Optional<Inscripcion> findByJugador_IdJugadorAndTorneo_IdTorneoAndEstado(
            Long idJugador,
            Long idTorneo,
            EstadoInscripcion estado
    );


}
