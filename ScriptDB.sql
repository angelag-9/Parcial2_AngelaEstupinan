-- ================================================================
--  Script MySQL — examen2_estupinan
--
--  Estrategia ORM: JOINED TABLE (InheritanceType.JOINED)
--  Una tabla por clase → sin repetición de columnas → normalizado.
-- ================================================================

CREATE DATABASE IF NOT EXISTS examen2_estupinan
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE examen2_estupinan;

-- ================================================================
-- TABLA 1 — asegurado
-- ================================================================
CREATE TABLE IF NOT EXISTS asegurado (

    id      VARCHAR(50)  NOT NULL,
    nombre  VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)

) ENGINE = InnoDB;

-- ================================================================
-- TABLA 2 — seguro (clase base)
-- ================================================================
CREATE TABLE IF NOT EXISTS seguro (

    numero        VARCHAR(20)  NOT NULL,
    fecha_exp     VARCHAR(20)  NOT NULL,
    estado        TINYINT(1)   NOT NULL DEFAULT 1,
    asegurado_id  VARCHAR(50)  NOT NULL,

    PRIMARY KEY (numero),

    CONSTRAINT fk_seguro_asegurado
        FOREIGN KEY (asegurado_id)
        REFERENCES asegurado (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT

) ENGINE = InnoDB;

-- ================================================================
-- TABLA 3 — seguro_vida
-- ================================================================
CREATE TABLE IF NOT EXISTS seguro_vida (

    numero        VARCHAR(20)  NOT NULL,
    beneficiario  VARCHAR(100) NOT NULL,

    PRIMARY KEY (numero),

    CONSTRAINT fk_vida_seguro
        FOREIGN KEY (numero)
        REFERENCES seguro (numero)
        ON UPDATE CASCADE
        ON DELETE CASCADE

) ENGINE = InnoDB;

-- ================================================================
-- TABLA 4 — seguro_vehiculo
-- ================================================================
CREATE TABLE IF NOT EXISTS seguro_vehiculo (

    numero  VARCHAR(20)  NOT NULL,
    marca   VARCHAR(100) NOT NULL,

    PRIMARY KEY (numero),

    CONSTRAINT fk_vehiculo_seguro
        FOREIGN KEY (numero)
        REFERENCES seguro (numero)
        ON UPDATE CASCADE
        ON DELETE CASCADE

) ENGINE = InnoDB;

-- ================================================================
-- DATOS DE PRUEBA
-- ================================================================

INSERT INTO asegurado (id, nombre) VALUES
('T001', 'Test'),
('A001', 'Juan Perez'),
('A002', 'Maria Lopez');

INSERT INTO seguro (numero, fecha_exp, estado, asegurado_id) VALUES
('S001', '12/2027', 1, 'A001'),
('S002', '06/2026', 1, 'A001'),
('S003', '09/2025', 0, 'A002');

INSERT INTO seguro_vida (numero, beneficiario) VALUES
('S001', 'Ana Perez');

INSERT INTO seguro_vehiculo (numero, marca) VALUES
('S002', 'Toyota'),
('S003', 'Honda');