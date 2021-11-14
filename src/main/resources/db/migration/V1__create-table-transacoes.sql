create table transacoes(
id bigint not null auto_increment,
ticker varchar(6) not null,
preco decimal (18,2) not null,
quantidade int not null,
tipo varchar(100),
data date,
primary key (id)
);