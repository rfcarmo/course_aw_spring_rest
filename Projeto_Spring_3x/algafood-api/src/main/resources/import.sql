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

insert into cozinha (id, nome) values ('5776008f-ad3f-409b-8ce9-7b1c3e16defb', 'Italiana');
insert into cozinha (id, nome) values ('6e3e80ed-1757-4263-9855-e4fe5173930d', 'Portuguesa');
insert into cozinha (id, nome) values ('5fbf1884-9959-4268-92dd-66a745cac923', 'Argentina');
insert into cozinha (id, nome) values ('fa6e19ce-6106-46c0-9270-4407eec84cac', 'Brasileira');

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao) values ('dcb70328-416b-4fa0-8bf8-407911225cfe', 'Abbraccio', 10, '5776008f-ad3f-409b-8ce9-7b1c3e16defb', '20dbab67-8f81-4586-8aa0-9894ba3e69f7', '14600-000', 'Rua Alfa', '123', 'Sacomã', current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('bbad22e6-bb94-4d46-b81a-e35fe8009501', 'Amico', 9.50, '5776008f-ad3f-409b-8ce9-7b1c3e16defb', current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('4779e2ba-c3c4-4906-b97e-a05b7048e25c', 'Brasão', 15, '6e3e80ed-1757-4263-9855-e4fe5173930d', current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('f5b04b97-efe2-4ca3-97a2-fdd90232041e', 'Java Steakhouse', 12, '5fbf1884-9959-4268-92dd-66a745cac923', current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('f6e57d93-b50c-487d-9dc7-de8f16f6cf2e', 'Lanchonete do Tio Sam', 11, 'fa6e19ce-6106-46c0-9270-4407eec84cac', current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('5ca62b7d-63e9-497b-9d7c-a179fcb63e9e', 'Bar da Maria', 6, 'fa6e19ce-6106-46c0-9270-4407eec84cac', current_timestamp, current_timestamp);

insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values ('f6b126c9-9260-46f4-8de0-eb5d8f6dace1', 'Gnocchi ao Pesto', 'Deliciosa massa com molho pesto', 78.90, true, 'dcb70328-416b-4fa0-8bf8-407911225cfe');
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values ('4910d63a-ab39-47be-a955-1967d61edded', 'Risoto Camarão', '16 camarões grandes nesse maravilhoso risoto', 110, true, 'dcb70328-416b-4fa0-8bf8-407911225cfe');
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values ('d39e60b8-82e7-4ee2-9dac-69c9b21374d9', 'Pizza Quatro Queijos', 'Massa fina e variedade única de queijos italianos', 87.20, true, 'bbad22e6-bb94-4d46-b81a-e35fe8009501');
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values ('b2c6c57d-4ccc-460b-a604-fb003740e7a7', 'Lasanha Bolonhesa', 'Tradicional prato italiano com molho bolonhesa', 21, true, 'bbad22e6-bb94-4d46-b81a-e35fe8009501');
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values ('05292b4c-998b-4f0a-a41a-2a90a0b747fe', 'Francesinha', 'Deliciosa Francesinha com molho picante', 43, true, '4779e2ba-c3c4-4906-b97e-a05b7048e25c');
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values ('7e460ac5-062f-4ecc-962f-b85ad42635d5', 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 'f5b04b97-efe2-4ca3-97a2-fdd90232041e');
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values ('7b8a3770-c67b-4e28-9008-efd109394b9f', 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 'f5b04b97-efe2-4ca3-97a2-fdd90232041e');
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values ('f2c121dc-70ca-434a-aa09-80feb4f3346b', 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 'f6e57d93-b50c-487d-9dc7-de8f16f6cf2e');
insert into produto (id, nome, descricao, preco, ativo, restaurante_id) values ('edae05d4-246b-48e6-80e4-40e36b549ed0', 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, '5ca62b7d-63e9-497b-9d7c-a179fcb63e9e');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('dcb70328-416b-4fa0-8bf8-407911225cfe', '892186d5-d165-4f33-b837-cee11db7b127');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('dcb70328-416b-4fa0-8bf8-407911225cfe', 'b61928f0-8efc-4168-a498-88c7927e0652');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('dcb70328-416b-4fa0-8bf8-407911225cfe', 'a3e942b1-3f45-4125-9ccf-73ecb31bedf1');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('bbad22e6-bb94-4d46-b81a-e35fe8009501', 'b61928f0-8efc-4168-a498-88c7927e0652');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('bbad22e6-bb94-4d46-b81a-e35fe8009501', 'a3e942b1-3f45-4125-9ccf-73ecb31bedf1');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('4779e2ba-c3c4-4906-b97e-a05b7048e25c', 'a3e942b1-3f45-4125-9ccf-73ecb31bedf1');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('f5b04b97-efe2-4ca3-97a2-fdd90232041e', '892186d5-d165-4f33-b837-cee11db7b127');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('f5b04b97-efe2-4ca3-97a2-fdd90232041e', 'b61928f0-8efc-4168-a498-88c7927e0652');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('f5b04b97-efe2-4ca3-97a2-fdd90232041e', 'a3e942b1-3f45-4125-9ccf-73ecb31bedf1');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('f6e57d93-b50c-487d-9dc7-de8f16f6cf2e', 'b61928f0-8efc-4168-a498-88c7927e0652');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('f6e57d93-b50c-487d-9dc7-de8f16f6cf2e', 'a3e942b1-3f45-4125-9ccf-73ecb31bedf1');
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values ('5ca62b7d-63e9-497b-9d7c-a179fcb63e9e', 'a3e942b1-3f45-4125-9ccf-73ecb31bedf1');
