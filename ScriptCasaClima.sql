CREATE TABLE Cliente (
    cod_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    rua VARCHAR(100),
    numero INT,
    cidade VARCHAR(50),
    bairro VARCHAR(50),
    telefone VARCHAR(15),
    cpf CHAR(11),
    cnpj CHAR(14),
    senha VARCHAR(255),
    data_cadastro DATE
);

CREATE TABLE ClienteLog (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cod_cliente INT,
    operacao VARCHAR(10),
    data_operacao DATETIME,
    detalhes VARCHAR(255),
    FOREIGN KEY (cod_cliente) REFERENCES Cliente (cod_cliente)
);

CREATE TABLE Funcionario (
    matricula VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(100),
    rua VARCHAR(100),
    numero INT,
    cidade VARCHAR(50),
    bairro VARCHAR(50),
    data_nascimento DATE,
    telefone VARCHAR(15),
    senha VARCHAR(255),
    supervisor VARCHAR(20),
    cpf VARCHAR(11),
    CONSTRAINT FK_Funcionario_Supervisor FOREIGN KEY (supervisor) REFERENCES Funcionario (matricula)
);

CREATE TABLE Transporte (
    ID_transporte INT AUTO_INCREMENT PRIMARY KEY,
    prazo_de_entrega DATE
);

CREATE TABLE Instalacao (
    ID_instalacao INT AUTO_INCREMENT PRIMARY KEY,
    valor DECIMAL(10, 2)
);

CREATE TABLE Produto (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    descricao VARCHAR(255),
    capacidade VARCHAR(50),
    preco DECIMAL(10,2),
    marca VARCHAR(50),
    ativo BOOLEAN NOT NULL DEFAULT true,
    caminho_imagem VARCHAR(255),
    desconto DECIMAL(5,2) DEFAULT 0,
    precoOriginal DECIMAL(10,2) NOT NULL
);

CREATE TABLE Promocao (
    ID_desconto INT AUTO_INCREMENT PRIMARY KEY,
    tipo_desconto VARCHAR(50),
    valor DECIMAL(10,2)
);

CREATE TABLE Pedido (
    numero INT AUTO_INCREMENT PRIMARY KEY,
    data_de_realizacao DATE,
    valor_total DECIMAL(10,2),
    status VARCHAR(20),
    metodo_pagamento VARCHAR(30),
    fk_Cliente_cod_cliente INT,
    fk_Transporte_ID_transporte INT,
    endereco_rua VARCHAR(100),
    endereco_numero INT,
    endereco_cidade VARCHAR(50),
    endereco_bairro VARCHAR(50),
    endereco_cep CHAR(8),
    CONSTRAINT FK_Pedido_cliente FOREIGN KEY (fk_Cliente_cod_cliente) REFERENCES Cliente (cod_cliente),
    CONSTRAINT FK_Pedido_transporte FOREIGN KEY (fk_Transporte_ID_transporte) REFERENCES Transporte (ID_transporte)
);

CREATE TABLE Pedido_Instalacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fk_Pedido_numero INT,
    fk_Instalacao_ID_instalacao INT,
    quantidade INT,
    CONSTRAINT FK_PedidoInstalacao_pedido FOREIGN KEY (fk_Pedido_numero) REFERENCES Pedido (numero),
    CONSTRAINT FK_PedidoInstalacao_instalacao FOREIGN KEY (fk_Instalacao_ID_instalacao) REFERENCES Instalacao (ID_instalacao)
);

CREATE TABLE Pedido_Produto (
    fk_Pedido_numero INT,
    fk_Produto_codigo INT,
    quantidade INT,
    PRIMARY KEY (fk_Produto_codigo, fk_Pedido_numero),
    CONSTRAINT FK_Pedido_Produto_pedido FOREIGN KEY (fk_Pedido_numero) REFERENCES Pedido (numero),
    CONSTRAINT FK_Pedido_Produto_produto FOREIGN KEY (fk_Produto_codigo) REFERENCES Produto (codigo)
);

CREATE TABLE Avaliacao (
    nota INT,
    descricao VARCHAR(255),
    data DATE,
    fk_Cliente_cod_cliente INT,
    fk_Produto_codigo INT,
    PRIMARY KEY (fk_Produto_codigo, fk_Cliente_cod_cliente),
    CONSTRAINT FK_Avaliacao_produto FOREIGN KEY (fk_Produto_codigo) REFERENCES Produto (codigo),
    CONSTRAINT FK_Avaliacao_cliente FOREIGN KEY (fk_Cliente_cod_cliente) REFERENCES Cliente (cod_cliente)
);

CREATE TABLE ClienteLog (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cod_cliente INT,
    operacao VARCHAR(10),
    data_operacao DATETIME,
    detalhes VARCHAR(255),
    FOREIGN KEY (cod_cliente) REFERENCES Cliente (cod_cliente)
);

USE projetobd;

INSERT INTO Cliente (nome, rua, numero, cidade, bairro, telefone, cpf, cnpj, senha, email)
VALUES
('Carlos Mendes', 'Rua dos Lírios', 50, 'Campinas', 'Jardim', '5512987654321', '00011122233', NULL, 'senhaCarlos', 'carlos@gmail.com'),
('Empresa XYZ', 'Avenida dos Estados', 200, 'Curitiba', 'Centro', '551234567890', NULL, '12345678000199', 'senhaEmpresa', 'empresa@gmail.com');

INSERT INTO Funcionario (matricula, nome, rua, numero, cidade, bairro, data_nascimento, telefone, senha, supervisor, cpf)
VALUES 
('001', 'Ana Paula', 'Rua das Flores', 100, 'São Paulo', 'Centro', '1990-05-15', '1112345678', 'senhaAna1', null, '12345678900'),
('002', 'Bruno Souza', 'Avenida Central', 150, 'Rio de Janeiro', 'Zona Sul', '1982-11-20', '1198765432', 'senhaBruno2', '001', '00987654321');

INSERT INTO Transporte (prazo_de_entrega)
VALUES
('2025-04-15'),
('2025-04-17'),
('2025-04-25'),
('2025-04-26'),
('2025-05-27');

INSERT INTO Produto (nome, descricao, capacidade, preco, marca, ativo, caminho_imagem, desconto, precoOriginal)
VALUES
('Ar Condicionado', 'Split Inverter Frio', '9000 BTUs', 1700.00, 'LG', 0, '/imagens/lg.jpg', 0, 1700.00),
('Ar Condicionado', 'Split Inverter Quente/Frio', '12000 BTUs', 2100.00, 'Samsung', 0, '/imagens/samsung.jpg', 0, 2100.00);

INSERT INTO Promocao (tipo_desconto, valor)
VALUES
('Desconto de Verão', 10.00),
('Promoção de Natal', 20.00);

INSERT INTO Avaliacao (nota, descricao, data, fk_Cliente_cod_cliente, fk_Produto_codigo)
VALUES
(5, 'Excelente produto!', '2025-03-20', 1, 1),
(4, 'Muito bom, mas poderia ser mais barato.', '2025-03-21', 2, 2);

INSERT INTO Pedido (numero, data_de_realizacao, valor_total, status, metodo_pagamento, fk_Cliente_cod_cliente, fk_Transporte_ID_transporte, endereco_rua, endereco_numero, endereco_cidade)
VALUES
(5, '2025-05-24', 9200.00, 'Pendente', 'Cartão de Débito', 15, 3, 'Rua C', 150, 'Cidade Z'),
(6, '2025-06-25', 13000.00, 'Pendente', 'Transferência Bancária', 13, 4, 'Rua D', 250, 'Cidade W'),
(7, '2025-06-30', 15000.00, 'Pendente', 'Transferência Bancária', 14, 5, 'Rua E', 50, 'Cidade E');

INSERT INTO Instalacao (ID_instalacao, valor)
VALUES
(3, 2400.00),
(4, 800.00),
(5, 1600.00);

INSERT INTO Pedido_Instalacao (id, fk_Pedido_numero, fk_Instalacao_ID_instalacao, quantidade)
VALUES
(14, 5, 3, 1),
(15, 6, 4, 2),
(16, 7, 5, 2);

INSERT INTO Pedido_Produto (fk_Pedido_numero, fk_Produto_codigo, quantidade)
VALUES
(5, 18, 7),
(6, 22, 8),
(7, 30, 3);

DELIMITER $$

CREATE TRIGGER log_update_cliente
AFTER UPDATE ON Cliente
FOR EACH ROW
BEGIN
    INSERT INTO ClienteLog (cod_cliente, operacao, data_operacao, detalhes)
    VALUES (NEW.cod_cliente, 'UPDATE', NOW(), CONCAT('Nome:', OLD.nome, ' -> ', NEW.nome));
END$$

CREATE TRIGGER data_cadastro_cliente
BEFORE INSERT ON Cliente
FOR EACH ROW
BEGIN
    SET NEW.data_cadastro = NOW();
END$$

DELIMITER ;
