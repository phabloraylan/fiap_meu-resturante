CREATE TABLE comentarios
(
    id             SERIAL PRIMARY KEY,
    restaurante_id INT          NOT NULL,
    comentario     TEXT         NOT NULL,
    nome           VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT NULL,
    FOREIGN KEY (restaurante_id) REFERENCES restaurantes (id)
);