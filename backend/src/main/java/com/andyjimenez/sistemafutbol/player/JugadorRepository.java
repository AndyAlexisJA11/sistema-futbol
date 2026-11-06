package com.andyjimenez.sistemafutbol.player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    boolean existsByDocumentoIdentificacion(String documentoIdentificacion);

    boolean existsByDocumentoIdentificacionAndIdJugadorNot(String documentoIdentificacion, Long idJugador);
}
