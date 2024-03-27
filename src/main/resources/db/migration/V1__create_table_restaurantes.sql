CREATE TABLE restaurantes (
    id          SERIAL PRIMARY KEY,
    nome        VARCHAR(255) NOT NULL,
    endereco    VARCHAR(255) NOT NULL,
    tipo_de_cozinha VARCHAR(255) NOT NULL,
    mesas       INTEGER NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT NULL
);