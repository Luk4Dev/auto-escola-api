CREATE TABLE instrutores (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(160) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    cnh VARCHAR(11) NOT NULL UNIQUE,
    especialidade VARCHAR(20) NOT NULL,
    logradouro VARCHAR(120) NOT NULL,
    numero INTEGER,
    complemento VARCHAR(80),
    bairro VARCHAR(80) NOT NULL,
    cidade VARCHAR(80) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL,
    atualizado_em TIMESTAMP NOT NULL,
    CONSTRAINT chk_instrutor_especialidade
        CHECK (especialidade IN ('MOTOS', 'CARROS', 'VANS', 'CAMINHOES')),
    CONSTRAINT chk_instrutor_cnh
        CHECK (cnh ~ '^[0-9]{11}$')
);

CREATE INDEX idx_instrutores_ativos_nome ON instrutores (ativo, nome);

CREATE TABLE alunos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(160) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    logradouro VARCHAR(120) NOT NULL,
    numero INTEGER,
    complemento VARCHAR(80),
    bairro VARCHAR(80) NOT NULL,
    cidade VARCHAR(80) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    criado_em TIMESTAMP NOT NULL,
    atualizado_em TIMESTAMP NOT NULL,
    CONSTRAINT chk_aluno_cpf
        CHECK (cpf ~ '^[0-9]{11}$')
);

CREATE INDEX idx_alunos_nome ON alunos (nome);
