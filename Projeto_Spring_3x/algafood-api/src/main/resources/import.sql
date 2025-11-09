insert into cozinha(id, nome) values (gen_random_uuid(), 'Italiana');
insert into cozinha(id, nome) values (gen_random_uuid(), 'Portuguesa');

insert into restaurante (id, nome, taxa_frete, cozinha_id) values (gen_random_uuid(), 'Abbraccio', 10, (select id from cozinha where nome = 'Italiana'));
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (gen_random_uuid(), 'Amico', 9.50, (select id from cozinha where nome = 'Italiana'));
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (gen_random_uuid(), 'Brasão', 15, (select id from cozinha where nome = 'Portuguesa'));

insert into estado (id, nome) values (gen_random_uuid(), 'Minas Gerais');
insert into estado (id, nome) values (gen_random_uuid(), 'São Paulo');
insert into estado (id, nome) values (gen_random_uuid(), 'Ceará');

insert into cidade (id, nome, estado_id) values (gen_random_uuid(), 'Uberlândia', (select id from estado where nome = 'Minas Gerais'));
insert into cidade (id, nome, estado_id) values (gen_random_uuid(), 'Belo Horizonte', (select id from estado where nome = 'Minas Gerais'));
insert into cidade (id, nome, estado_id) values (gen_random_uuid(), 'São Paulo',(select id from estado where nome = 'São Paulo'));
insert into cidade (id, nome, estado_id) values (gen_random_uuid() 'Campinas', (select id from estado where nome = 'São Paulo'));
insert into cidade (id, nome, estado_id) values (gen_random_uuid(), 'Fortaleza',(select id from estado where nome = 'Ceará'));

insert into forma_pagamento (id, descricao) values (gen_random_uuid(), 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (gen_random_uuid(), 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (gen_random_uuid(), 'Dinheiro');

insert into permissao (id, nome, descricao) values (gen_random_uuid(), 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (gen_random_uuid(), 'EDITAR_COZINHAS', 'Permite editar cozinhas');