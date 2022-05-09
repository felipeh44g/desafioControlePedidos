CREATE TABLE produto (
	id serial,
	nome VARCHAR(255) NOT NULL,
    preco DECIMAL NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ordem_pedido (
    id serial,
    PRIMARY KEY (id)
);

CREATE TABLE pedido (
	id serial,
	valor_total DECIMAL NOT NULL,
	ordem_pedido_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (ordem_pedido_id) REFERENCES ordem_pedido (id)
);

CREATE TABLE pedido_produto (
	produto_id INT,
	pedido_id INT,
	CONSTRAINT pedido_pedido_produto FOREIGN KEY (pedido_id) REFERENCES Pedido (id),
	CONSTRAINT produto_produtos FOREIGN KEY (produto_id) REFERENCES Produto (id)
);

CREATE SEQUENCE produto_id
START 10
INCREMENT 10
MINVALUE 10
OWNED BY produto.id;

CREATE SEQUENCE pedido_id
START 10
INCREMENT 10
MINVALUE 10
OWNED BY pedido.id;

CREATE SEQUENCE ordem_id
START 10
INCREMENT 10
MINVALUE 10
OWNED BY ordem_pedido.id;

INSERT INTO produto(nome, preco) VALUES ('Banana', 200);

INSERT INTO produto(nome, preco) VALUES ('Maçã', 200);

INSERT INTO produto(nome, preco) VALUES ('Abacate', 200);