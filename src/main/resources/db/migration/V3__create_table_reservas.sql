CREATE TABLE reservas
(
    id             SERIAL PRIMARY KEY,
    restaurante_id INT          NOT NULL,
    nome           VARCHAR(255) NOT NULL,
    mesa           INT          NOT NULL,
    data           DATE         NOT NULL,
    hora           TIME         NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT NULL,
    FOREIGN KEY (restaurante_id) REFERENCES restaurantes (id)
);