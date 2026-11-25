package com.andyjimenez.sistemafutbol.card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {

    long countByJugador_IdJugadorAndPartido_Jornada_Torneo_IdTorneoAndTipo(
            Long idJugador,
            Long idTorneo,
            TipoTarjeta tipo
    );

    boolean existsByPartido_IdPartidoAndJugador_IdJugadorAndTipo(
            Long idPartido,
            Long idJugador,
            TipoTarjeta tipo
    );
}
