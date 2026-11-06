CREATE TABLE jugador (
    id_jugador BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(150) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    lugar_nacimiento VARCHAR(150) NOT NULL,
    documento_identifiacion VARCHAR(50) NOT NULL,
    foto VARCHAR(255)
);