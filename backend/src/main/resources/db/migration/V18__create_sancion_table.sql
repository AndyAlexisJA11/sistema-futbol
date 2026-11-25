CREATE TABLE sancion(
    id_sancion BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_jugador BIGINT NOT NULL,
    id_torneo BIGINT NOT NULL,
    partidos_suspension INT NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    estado VARCHAR(20) NOT NULL,

    CONSTRAINT fk_sancion_jugador
                    FOREIGN KEY (id_jugador)
                    REFERENCES jugador(id_jugador),

    CONSTRAINT fk_sancion_torneo
                    FOREIGN KEY (id_torneo)
                    REFERENCES torneo(id_torneo)
);