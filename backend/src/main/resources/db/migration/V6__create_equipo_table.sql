CREATE TABLE equipo (
    id_equipo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nombre_corto VARCHAR(50) NOT NULL,
    cancha VARCHAR(150),
    estado VARCHAR(20) NOT NULL
);