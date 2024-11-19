CREATE TABLE USERS(
ID INTEGER PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(255) NOT NULL,
EMAIL VARCHAR(255) NOT NULL,
PASSWORD VARCHAR(255) NOT NULL,
CPF_CNPJ VARCHAR(255) NOT NULL,
IS_ACTIVE BOOLEAN DEFAULT TRUE
);

create table produtos(
    id integer primary key auto_increment,
    nome varchar(255) not null,
    preco DOUBLE,
    quantidade integer,
    USER_ID INTEGER REFERENCES USERS (id)
);

CREATE TABLE sales (
                       id INTEGER PRIMARY KEY AUTO_INCREMENT,
                       user_id INTEGER NOT NULL,
                       product_id INTEGER NOT NULL,
                       quantity INTEGER NOT NULL,
                       price DOUBLE NOT NULL,
                       sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (user_id) REFERENCES users(id),
                       FOREIGN KEY (product_id) REFERENCES produtos(id)
);
