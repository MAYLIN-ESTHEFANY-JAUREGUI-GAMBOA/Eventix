-- Sistema de Gestión de Eventos - Base de Datos MySQL
-- Script de creación de tablas y datos iniciales

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS eventix_db;
USE eventix_db;

-- Tabla Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL
);

-- Tabla Eventos
CREATE TABLE IF NOT EXISTS eventos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombreEvento VARCHAR(100) NOT NULL,
    tipoEvento VARCHAR(50) NOT NULL,
    modalidad VARCHAR(50) NOT NULL,
    servicios VARCHAR(255),
    fechaEvento DATE NOT NULL,
    costo DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(20) DEFAULT 'ACTIVO'
);

-- Insertar usuarios de prueba
INSERT INTO usuarios (usuario, contraseña) VALUES
('admin', 'admin123'),
('usuario', 'usuario123');

-- Insertar eventos de prueba
INSERT INTO eventos (nombreEvento, tipoEvento, modalidad, servicios, fechaEvento, costo, estado) VALUES
('Taller de Java', 'Taller', 'Presencial', 'Certificado,Material Digital,Coffee Break', '2024-07-15', 150.00, 'ACTIVO'),
('Seminario de Arquitectura', 'Seminario', 'Virtual', 'Certificado,Material Digital', '2024-08-20', 200.00, 'ACTIVO'),
('Conferencia de Tecnología', 'Conferencia', 'Presencial', 'Certificado,Coffee Break', '2024-09-10', 100.00, 'ACTIVO'),
('Webinar de Spring Boot', 'Webinar', 'Virtual', 'Material Digital', '2024-10-05', 50.00, 'ACTIVO');
