-- Borrar tablas si existen
DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS usuario;


-- Crear tabla usuario
CREATE TABLE usuario
(
    id       VARCHAR(36) PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified datetime,
    last_login datetime,
    isactive bit default true,
    UNIQUE (email)
);

-- Crear tabla telefono
CREATE TABLE phone
(
    id          VARCHAR(36) PRIMARY KEY,
    number      VARCHAR(20) NOT NULL,
    citycode    VARCHAR(10) NOT NULL,
    countrycode VARCHAR(10) NOT NULL,
    usuario_id  INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)

);