ALTER TABLE sancion
    ADD COLUMN id_partido_origen BIGINT NULL;

ALTER TABLE sancion
    ADD CONSTRAINT fk_sancion_partido_origen
        FOREIGN KEY (id_partido_origen)
            REFERENCES partido(id_partido);