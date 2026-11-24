CREATE TABLE tarjeta(
    id_tarjeta BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_partido BIGINT NOT NULL,
    id_jugador BIGINT NOT NULL,
    id_equipo BIGINT NOT NULL,
    tipo VARCHAR(20) NOT NULL,

    CONSTRAINT fk_tarjeta_partido
                    FOREIGN KEY (id_partido)
                    REFERENCES partido(id_partido),

    CONSTRAINT fk_tarjeta_jugador
                    FOREIGN KEY (id_jugador)
                    REFERENCES jugador(id_jugador),

    CONSTRAINT fk_tarjeta_equipo
                    FOREIGN KEY (id_equipo)
                    REFERENCES equipo(id_equipo)
);