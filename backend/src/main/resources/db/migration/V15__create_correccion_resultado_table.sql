CREATE TABLE correccion_resultado (
    id_correccion BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_partido BIGINT NOT NULL,
    goles_local_anterior INT NOT NULL,
    goles_visitante_anterior INT NOT NULL,
    goles_local_nuevo INT NOT NULL,
    goles_visitante_nuevo INT NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    fecha_correccion DATETIME NOT NULL,

    CONSTRAINT fk_correccion_partido
                                  FOREIGN KEY (id_partido)
                                  REFERENCES partido(id_partido)
);