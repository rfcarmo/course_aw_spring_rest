delete from item_pedido;
delete from pedido;
delete from restaurante_usuario_responsavel;
delete from usuario_grupo;
delete from grupo_permissao;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from grupo;
delete from permissao;
delete from forma_pagamento;
delete from produto;
delete from restaurante;
delete from cozinha;
delete from cidade;
delete from estado;

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

insert into grupo (id, nome) values ('2c8f4317-eb7e-48f1-b89e-9d7eb0f57cff', 'Gerente');
insert into grupo (id, nome) values ('ad6f8f26-dbc6-41af-83cd-785045005de3', 'Vendedor');
insert into grupo (id, nome) values ('6880ec9c-6727-4b4c-9269-dc8f49ae3ea6', 'Secretária');
insert into grupo (id, nome) values ('191433a6-3d2d-4f89-acae-f5f61e4c1794', 'Cadastrador');

insert into usuario (id, nome, email, senha, data_cadastro) values ('bc94e54a-a3ff-4f04-a9f6-3ae9cae216aa', 'João da Silva', 'joao.ger@algafood.com', '123', current_timestamp at time zone 'utc');
insert into usuario (id, nome, email, senha, data_cadastro) values ('5ba4300a-c2a4-4ef7-a99e-c6959289e9c0', 'Maria Joaquina', 'maria.vnd@algafood.com', '123', current_timestamp at time zone 'utc');
insert into usuario (id, nome, email, senha, data_cadastro) values ('2a43ea5c-df4d-48de-9aaa-5f07d411ad26', 'José Souza', 'jose.aux@algafood.com', '123', current_timestamp at time zone 'utc');
insert into usuario (id, nome, email, senha, data_cadastro) values ('247a8320-4691-44af-939b-593908e7d05e', 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', current_timestamp at time zone 'utc');
insert into usuario (id, nome, email, senha, data_cadastro) values ('f40898bf-e27a-4b61-804a-26836e10030e', 'Manoel Lima', 'manoel.loja@gmail.com', '123', current_timestamp at time zone 'utc');

insert into usuario_grupo (usuario_id, grupo_id) values ('bc94e54a-a3ff-4f04-a9f6-3ae9cae216aa', '2c8f4317-eb7e-48f1-b89e-9d7eb0f57cff');
insert into usuario_grupo (usuario_id, grupo_id) values ('bc94e54a-a3ff-4f04-a9f6-3ae9cae216aa', 'ad6f8f26-dbc6-41af-83cd-785045005de3');
insert into usuario_grupo (usuario_id, grupo_id) values ('5ba4300a-c2a4-4ef7-a99e-c6959289e9c0', 'ad6f8f26-dbc6-41af-83cd-785045005de3');

insert into grupo_permissao (grupo_id, permissao_id) values ('2c8f4317-eb7e-48f1-b89e-9d7eb0f57cff', 'a4d593c7-84c9-4005-94f5-02a4af238dcf');
insert into grupo_permissao (grupo_id, permissao_id) values ('2c8f4317-eb7e-48f1-b89e-9d7eb0f57cff', 'cc07244d-ecc0-4f68-88a5-ca7a0741c4e4');
insert into grupo_permissao (grupo_id, permissao_id) values ('ad6f8f26-dbc6-41af-83cd-785045005de3', 'a4d593c7-84c9-4005-94f5-02a4af238dcf');
insert into grupo_permissao (grupo_id, permissao_id) values ('ad6f8f26-dbc6-41af-83cd-785045005de3', 'cc07244d-ecc0-4f68-88a5-ca7a0741c4e4');
insert into grupo_permissao (grupo_id, permissao_id) values ('6880ec9c-6727-4b4c-9269-dc8f49ae3ea6', 'a4d593c7-84c9-4005-94f5-02a4af238dcf');

insert into cozinha (id, nome) values ('5776008f-ad3f-409b-8ce9-7b1c3e16defb', 'Italiana');
insert into cozinha (id, nome) values ('6e3e80ed-1757-4263-9855-e4fe5173930d', 'Portuguesa');
insert into cozinha (id, nome) values ('5fbf1884-9959-4268-92dd-66a745cac923', 'Argentina');
insert into cozinha (id, nome) values ('fa6e19ce-6106-46c0-9270-4407eec84cac', 'Brasileira');

insert into restaurante (id, nome, taxa_frete, cozinha_id, ativo, aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao) values ('dcb70328-416b-4fa0-8bf8-407911225cfe', 'Abbraccio', 10, '5776008f-ad3f-409b-8ce9-7b1c3e16defb', true, true, '20dbab67-8f81-4586-8aa0-9894ba3e69f7', '14600-000', 'Rua Alfa', '123', 'Sacomã', current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into restaurante (id, nome, taxa_frete, cozinha_id, ativo, aberto, data_cadastro, data_atualizacao) values ('bbad22e6-bb94-4d46-b81a-e35fe8009501', 'Amico', 9.50, '5776008f-ad3f-409b-8ce9-7b1c3e16defb', true, true, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into restaurante (id, nome, taxa_frete, cozinha_id, ativo, aberto, data_cadastro, data_atualizacao) values ('4779e2ba-c3c4-4906-b97e-a05b7048e25c', 'Brasão', 15, '6e3e80ed-1757-4263-9855-e4fe5173930d', true, true, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into restaurante (id, nome, taxa_frete, cozinha_id, ativo, aberto, data_cadastro, data_atualizacao) values ('f5b04b97-efe2-4ca3-97a2-fdd90232041e', 'Java Steakhouse', 12, '5fbf1884-9959-4268-92dd-66a745cac923', true, true, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into restaurante (id, nome, taxa_frete, cozinha_id, ativo, aberto, data_cadastro, data_atualizacao) values ('f6e57d93-b50c-487d-9dc7-de8f16f6cf2e', 'Lanchonete do Tio Sam', 11, 'fa6e19ce-6106-46c0-9270-4407eec84cac', true, true, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into restaurante (id, nome, taxa_frete, cozinha_id, ativo, aberto, data_cadastro, data_atualizacao) values ('5ca62b7d-63e9-497b-9d7c-a179fcb63e9e', 'Bar da Maria', 6, 'fa6e19ce-6106-46c0-9270-4407eec84cac', true, true, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');

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

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values ('dcb70328-416b-4fa0-8bf8-407911225cfe', 'f40898bf-e27a-4b61-804a-26836e10030e');
insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values ('4779e2ba-c3c4-4906-b97e-a05b7048e25c', 'f40898bf-e27a-4b61-804a-26836e10030e');

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total) values ('beb2e388-d691-4493-a8a3-2576e07f0943', 'dcb70328-416b-4fa0-8bf8-407911225cfe', '5ba4300a-c2a4-4ef7-a99e-c6959289e9c0', '892186d5-d165-4f33-b837-cee11db7b127', '20dbab67-8f81-4586-8aa0-9894ba3e69f7', '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil', 'CRIADO', current_timestamp at time zone 'utc', 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values ('3f5142ad-9a13-407f-8f8b-7dea4812a2fd', 'beb2e388-d691-4493-a8a3-2576e07f0943', 'f6b126c9-9260-46f4-8de0-eb5d8f6dace1', 1, 78.9, 78.9, null);
insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values ('fa41bd06-91cf-41b6-a477-a621acae2355', 'beb2e388-d691-4493-a8a3-2576e07f0943', '4910d63a-ab39-47be-a955-1967d61edded', 2, 110, 220, 'Menos picante, por favor');

insert into pedido (id, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total) values ('7114eb4c-c574-4d93-aa15-1f8cdc4db7fb', 'f5b04b97-efe2-4ca3-97a2-fdd90232041e', '247a8320-4691-44af-939b-593908e7d05e', 'b61928f0-8efc-4168-a498-88c7927e0652', '20dbab67-8f81-4586-8aa0-9894ba3e69f7', '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro', 'CRIADO', current_timestamp at time zone 'utc', 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values ('12aaba18-7e79-4f45-a5a1-d37775a93955', '7114eb4c-c574-4d93-aa15-1f8cdc4db7fb', '7b8a3770-c67b-4e28-9008-efd109394b9f', 1, 79, 79, 'Ao ponto');