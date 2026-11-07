CREATE TABLE inscripcion (
    id_inscripcion BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_jugador BIGINT NOT NULL,
    id_equipo BIGINT NOT NULL,
    id_torneo BIGINT NOT NULL,
    estado VARCHAR(20) NOT NULL,

    CONSTRAINT fk_inscripcion_jugador
                         FOREIGN KEY (id_jugador)
                         REFERENCES jugador(id_jugador),

    CONSTRAINT fk_inscripcion_equipo
                         FOREIGN KEY (id_equipo)
                         REFERENCES equipo(id_equipo),

    CONSTRAINT fk_inscripcion_torneo
                         FOREIGN KEY (id_torneo)
                         REFERENCES torneo(id_torneo),

    CONSTRAINT uk_inscripcion_jugador_torneo
                         UNIQUE (id_jugador, id_torneo)
);