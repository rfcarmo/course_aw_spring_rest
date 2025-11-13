-- Habilita gen_random_uuid()
CREATE EXTENSION IF NOT EXISTS pgcrypto;

--CREATE TABLE IF NOT EXISTS public.cidade (
--    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
--    nome varchar(255) NOT NULL,
--    estado_id uuid NOT NULL,
--    CONSTRAINT fk_cidade_estado FOREIGN KEY (estado_id) REFERENCES public.estado(id)
--);

create table estado (
    id uuid not null,
    nome varchar(60) not null,
    primary key (id));

create table cidade (
    id uuid not null,
    nome varchar(60) not null,
    estado_id uuid not null,
    primary key (id));

create table cozinha (
    id uuid not null,
    nome varchar(60) not null,
    primary key (id));

create table forma_pagamento (
    id uuid not null,
    descricao varchar(150) not null,
    primary key (id));

create table restaurante (
    id uuid not null,
    nome varchar(60) not null,
    taxa_frete numeric(38,2) not null,
    data_cadastro timestamp(6) with time zone not null,
    data_atualizacao timestamp(6) with time zone not null,
    endereco_logradouro varchar(150),
    endereco_numero varchar(10),
    endereco_complemento varchar(150),
    endereco_bairro varchar(60),
    endereco_cep varchar(20),
    endereco_cidade_id uuid,
    cozinha_id uuid not null,
    primary key (id));

create table produto (
    id uuid not null,
    nome varchar(60) not null,
    descricao varchar(150) not null,
    preco numeric(38,2) not null,
    ativo boolean not null,
    restaurante_id uuid not null,
    primary key (id));

create table restaurante_forma_pagamento (
    forma_pagamento_id uuid not null,
    restaurante_id uuid not null);

create table usuario (
    id uuid not null,
    nome varchar(60) not null,
    email varchar(60) not null,
    senha varchar(60) not null,
    data_cadastro timestamp(6) with time zone not null,
    primary key (id));

create table permissao (
    id uuid not null,
    nome varchar(60) not null,
    descricao varchar(150) not null,
    primary key (id));

create table grupo (
    id uuid not null,
    nome varchar(60) not null,
    primary key (id));

create table usuario_grupo (
    grupo_id uuid not null,
    usuario_id uuid not null);

create table grupo_permissao (
    grupo_id uuid not null,
    permissao_id uuid not null);

alter table if exists cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado;
alter table if exists produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante;
alter table if exists restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha;
alter table if exists restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade;
alter table if exists restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento;
alter table if exists restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante;
alter table if exists usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo;
alter table if exists usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario;
alter table if exists grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao;
alter table if exists grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo;