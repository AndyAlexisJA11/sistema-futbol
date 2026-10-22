CREATE TABLE torneo (
    d_torneo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_inicio DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    jornada_limite_inscripcion INT NOT NULL,
    id_temporada BIGINT NOT NULL,

    CONSTRAINT fk_torneo_temporada
    FOREIGN KEY (id_temporada)
    REFERENCES temporada(id_temporada)
    );