CREATE TABLE gol (
    id_gol BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_partido BIGINT NOT NULL,
    id_jugador BIGINT NOT NULL,
    id_equipo BIGINT NOT NULL,

    CONSTRAINT fk_gol_partido
                 FOREIGN KEY (id_partido)
                 REFERENCES partido(id_partido),

    CONSTRAINT fk_gol_jugador
                 FOREIGN KEY (id_jugador)
                 REFERENCES jugador(id_jugador),

    CONSTRAINT fk_gol_equipo
                 FOREIGN KEY (id_equipo)
                 REFERENCES equipo(id_equipo)
);