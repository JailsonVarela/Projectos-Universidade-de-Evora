CREATE TABLE veiculo (
    id SERIAL PRIMARY KEY,
    matricula VARCHAR(10) UNIQUE NOT NULL,
    modelo VARCHAR(50),
    tipo VARCHAR(50),
    localizacao_atual VARCHAR(100),
    estado_aluguer VARCHAR(50),
    estado_administrativo VARCHAR(20) DEFAULT 'NAO_APROVADO',
    CONSTRAINT check_tipo_veiculo CHECK(tipo in ('COMPACTO', 'SUV', 'CAMIONETA')),
    CONSTRAINT check_estado_aluguer CHECK(estado_aluguer in ('DISPONIVEL', 'ALUGADO', 'EM_MANUTENCAO')),
    CONSTRAINT check_estado_administrativo CHECK(estado_administrativo in ('APROVADO', 'NAO_APROVADO'))
);

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    documento_identificacao VARCHAR(20) UNIQUE,
    contacto VARCHAR(50)
);

CREATE TABLE aluguer (
    id SERIAL PRIMARY KEY,
    veiculo_id INT REFERENCES veiculo(id),
    cliente_id INT REFERENCES cliente(id),
    valor_acordado DECIMAL(10, 2),
    data_inicio DATE,
    duracao_prevista INT
);
