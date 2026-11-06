ALTER TABLE jugador
ADD CONSTRAINT uk_jugador_documento
UNIQUE (documento_identificacion);