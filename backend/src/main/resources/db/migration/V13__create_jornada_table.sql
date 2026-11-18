CREATE TABLE jornada (
    id_jornada BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    id_torneo BIGINT NOT NULL,

    CONSTRAINT fk_jornada_torneo
                     FOREIGN KEY (id_torneo)
                     REFERENCES torneo(id_torneo),

    CONSTRAINT uk_jornada_torneo_numero
                     UNIQUE (id_torneo, numero)



);