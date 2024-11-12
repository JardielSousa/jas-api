CREATE TABLE produto (
    id bigserial NOT NULL,
    nome VARCHAR(60) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    preco NUMERIC(19, 2) NOT NULL,
    status INT NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    data_alteracao TIMESTAMP NOT NULL,
    CONSTRAINT produto_pkey PRIMARY KEY (id)
);