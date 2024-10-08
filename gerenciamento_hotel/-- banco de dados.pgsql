-- banco de dados

CREATE DATABASE gerencia_hotel

CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(14) UNIQUE,
    telefone VARCHAR(20),
    email VARCHAR(255),
    endereco TEXT
);


CREATE TABLE reservas (
    id SERIAL PRIMARY KEY,
    cpf_cliente VARCHAR(14),
    numero_quarto INT,
    data_entrada DATE,
    data_saida DATE,
    numero_reserva VARCHAR(50) UNIQUE,
    FOREIGN KEY (cpf_cliente) REFERENCES clientes(cpf) ON DELETE CASCADE
);

CREATE TABLE pagamento (
    id SERIAL PRIMARY KEY,
    numero_reserva VARCHAR(50) NOT NULL,
    cpf_cliente VARCHAR(50) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    data_pagamento DATE NOT NULL
);
