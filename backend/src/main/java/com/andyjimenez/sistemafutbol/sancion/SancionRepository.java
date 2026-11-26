package com.andyjimenez.sistemafutbol.sancion;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SancionRepository extends JpaRepository<Sancion, Long> {

    List<Sancion> findByTorneo_IdTorneoAndEstadoOrderByIdSancionAsc(
            Long idTorneo,
            EstadoSancion estado
    );


}
