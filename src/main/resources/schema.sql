-- Borrar tablas si existen
DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS usuarios_roles;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS roles;


-- Crear tabla usuario
CREATE TABLE usuario
(
    id        UUID DEFAULT RANDOM_UUID()PRIMARY KEY,
    name     VARCHAR(250) NOT NULL,
    email    VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    created datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified datetime,
    last_login datetime,
    username  VARCHAR(50),
    isactive bit default true,
    UNIQUE (email)
);

-- Crear tabla telefono
CREATE TABLE phone
(
    id           UUID DEFAULT RANDOM_UUID()PRIMARY KEY,
    number      VARCHAR(20) NOT NULL,
    citycode    VARCHAR(10) NOT NULL,
    countrycode VARCHAR(10) NOT NULL,
    usuario_id  UUID,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)

);
CREATE TABLE roles (
     id  UUID DEFAULT RANDOM_UUID()PRIMARY KEY,
     name  VARCHAR(255) NOT NULL

);

CREATE TABLE usuarios_roles (
                                usuario_id UUID,
                                rol_id UUID,
                                FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                                FOREIGN KEY (rol_id) REFERENCES roles(id)
);