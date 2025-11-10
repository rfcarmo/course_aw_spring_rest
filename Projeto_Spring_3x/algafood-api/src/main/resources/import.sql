insert into cozinha(id, nome) values ('5776008f-ad3f-409b-8ce9-7b1c3e16defb', 'Italiana');
insert into cozinha(id, nome) values ('6e3e80ed-1757-4263-9855-e4fe5173930d', 'Portuguesa');

insert into restaurante (id, nome, taxa_frete, cozinha_id) values ('dcb70328-416b-4fa0-8bf8-407911225cfe', 'Abbraccio', 10, '5776008f-ad3f-409b-8ce9-7b1c3e16defb');
insert into restaurante (id, nome, taxa_frete, cozinha_id) values ('bbad22e6-bb94-4d46-b81a-e35fe8009501', 'Amico', 9.50, '5776008f-ad3f-409b-8ce9-7b1c3e16defb');
insert into restaurante (id, nome, taxa_frete, cozinha_id) values ('4779e2ba-c3c4-4906-b97e-a05b7048e25c', 'Brasão', 15, '6e3e80ed-1757-4263-9855-e4fe5173930d');

insert into estado (id, nome) values ('8d8c0d3e-2a20-4e4c-ad0c-59fc178bbb0f', 'Minas Gerais');
insert into estado (id, nome) values ('4da910e5-f32b-41de-88c9-709cfe8f41b5', 'São Paulo');
insert into estado (id, nome) values ('9d9951e1-9d8b-42a6-b217-638c1bf6fc11', 'Ceará');

insert into cidade (id, nome, estado_id) values ('50e2d92c-0c06-43dc-a88c-206c34417e8f', 'Uberlândia', '8d8c0d3e-2a20-4e4c-ad0c-59fc178bbb0f');
insert into cidade (id, nome, estado_id) values ('82c2617f-375b-43ed-b693-69aa58e10a52', 'Belo Horizonte', '8d8c0d3e-2a20-4e4c-ad0c-59fc178bbb0f');
insert into cidade (id, nome, estado_id) values ('20dbab67-8f81-4586-8aa0-9894ba3e69f7', 'São Paulo', '4da910e5-f32b-41de-88c9-709cfe8f41b5');
insert into cidade (id, nome, estado_id) values ('6af7042f-0216-4255-9982-a7f93bf1a9a1', 'Campinas', '4da910e5-f32b-41de-88c9-709cfe8f41b5');
insert into cidade (id, nome, estado_id) values ('0b78c8c1-abd7-4c5e-8c11-71ce3513c5b2', 'Fortaleza', '9d9951e1-9d8b-42a6-b217-638c1bf6fc11');

insert into forma_pagamento (id, descricao) values ('892186d5-d165-4f33-b837-cee11db7b127', 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values ('b61928f0-8efc-4168-a498-88c7927e0652', 'Cartão de débito');
insert into forma_pagamento (id, descricao) values ('a3e942b1-3f45-4125-9ccf-73ecb31bedf1', 'Dinheiro');

insert into permissao (id, nome, descricao) values ('a4d593c7-84c9-4005-94f5-02a4af238dcf', 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values ('cc07244d-ecc0-4f68-88a5-ca7a0741c4e4', 'EDITAR_COZINHAS', 'Permite editar cozinhas');