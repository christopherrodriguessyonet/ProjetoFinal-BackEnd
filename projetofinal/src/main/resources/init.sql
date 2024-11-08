CREATE TABLE IF NOT EXISTS clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    nascimento DATE,
    UNIQUE(email)
);

CREATE TABLE IF NOT EXISTS noticias (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    descricao TEXT NOT NULL,
    link VARCHAR(200),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    enviada BOOLEAN DEFAULT FALSE
);
