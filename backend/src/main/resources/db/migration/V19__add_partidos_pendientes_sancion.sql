ALTER TABLE sancion
ADD COLUMN partidos_pendientes INT NULL;

UPDATE sancion
SET partidos_pendientes =
    CASE
        WHEN estado = 'ACTIVA' THEN partidos_suspension
        ELSE 0
    END;

ALTER TABLE sancion
MODIFY COLUMN partidos_pendientes INT NOT NULL;