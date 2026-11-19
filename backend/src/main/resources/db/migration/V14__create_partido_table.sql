CREATE TABLE partido(
    id_partido BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_jornada BIGINT NOT NULL,
    id_equipo_local BIGINT NOT NULL,
    id_equipo_visitante BIGINT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    estado VARCHAR(20) NOT NULL,
    goles_local INT,
    goles_visitante INT,

    CONSTRAINT fk_partido_jornada
                    FOREIGN KEY (id_jornada)
                    REFERENCES jornada(id_jornada),

    CONSTRAINT fk_partido_equipo_local
                    FOREIGN KEY (id_equipo_local)
                    REFERENCES equipo(id_equipo),

    CONSTRAINT fk_partido_equipo_visitante
                    FOREIGN KEY (id_equipo_visitante)
                    REFERENCES equipo(id_equipo)
);