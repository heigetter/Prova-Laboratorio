CREATE DATABASE gerenciamento_cards;

USE gerenciamento_cards;

CREATE TABLE cartas (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    franquia VARCHAR(50) NOT NULL,
    nome VARCHAR(150) NOT NULL,
    idioma VARCHAR(50) NOT NULL,
    edicao VARCHAR(150) NOT NULL,
    numeroC VARCHAR(50) NOT NULL,
    preco DOUBLE NOT NULL,
    quantidade INT NOT NULL,
    fornecedor VARCHAR(100)
    );
    
CREATE TABLE produtos (
idP BIGINT AUTO_INCREMENT PRIMARY KEY,
franquiaP VARCHAR(50) NOT NULL,
nomeP VARCHAR(150) NOT NULL,
idiomaP VARCHAR(50) NOT NULL,
tipoP VARCHAR(50) NOT NULL,
precoP DOUBLE NOT NULL,
quantidadeP INT NOT NULL,
fornecedorP VARCHAR(100)
);
