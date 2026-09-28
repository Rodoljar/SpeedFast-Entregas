CREATE DATABASE IF NOT EXISTS speedfast_s6;
USE speedfast_s6;

CREATE TABLE IF NOT EXISTS pedido (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      direccion VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    estado VARCHAR(50) DEFAULT 'PENDIENTE',
    repartidor VARCHAR(100) DEFAULT 'Sin asignar'
    );