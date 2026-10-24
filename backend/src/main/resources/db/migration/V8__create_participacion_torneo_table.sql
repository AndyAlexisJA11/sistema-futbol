CREATE TABLE participacion_torneo (
  id_participacion BIGINT AUTO_INCREMENT PRIMARY KEY,
  id_torneo BIGINT NOT NULL,
  id_equipo BIGINT NOT NULL,

  CONSTRAINT fk_participacion_torneo
        FOREIGN KEY (id_torneo)
        REFERENCES torneo(id_torneo),

  CONSTRAINT fk_participacion_equipo
         FOREIGN KEY (id_equipo)
         REFERENCES equipo(id_equipo),

  CONSTRAINT uk_torneo_equipo
     UNIQUE (id_torneo, id_equipo)
);