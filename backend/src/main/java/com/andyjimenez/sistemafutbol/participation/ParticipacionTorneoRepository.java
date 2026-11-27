package com.andyjimenez.sistemafutbol.participation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParticipacionTorneoRepository extends JpaRepository<ParticipacionTorneo, Long> {

    boolean existsByTorneo_IdTorneoAndEquipo_IdEquipo(Long idTorneo, Long idEquipo);

    List<ParticipacionTorneo> findByTorneo_IdTorneo(Long idTorneo);
}
