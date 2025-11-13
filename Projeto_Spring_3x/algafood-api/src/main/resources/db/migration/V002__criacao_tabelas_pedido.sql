create table pedido (
    id uuid not null,
    subtotal numeric(38,2) not null,
    taxa_frete numeric(38,2) not null,
    valor_total numeric(38,2) not null,

    restaurante_id uuid not null,
    usuario_cliente_id uuid not null,
    forma_pagamento_id uuid not null,

    endereco_cidade_id uuid not null,
    endereco_cep varchar(9) not null,
    endereco_logradouro varchar(100) not null,
    endereco_numero varchar(20) not null,
    endereco_complemento varchar(60) null,
    endereco_bairro varchar(60) not null,

    status varchar(10) not null,
    data_criacao timestamp(6) with time zone not null,
    data_confirmacao timestamp(6) with time zone null,
    data_cancelamento timestamp(6) with time zone null,
    data_entrega timestamp(6) with time zone null,

    primary key (id),

    constraint fk_pedido_endereco_cidade foreign key (endereco_cidade_id) references cidade,
    constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante,
    constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario,
    constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento
);

create table item_pedido (
    id uuid not null,
    quantidade integer not null,
    preco_unitario numeric(38,2) not null,
    preco_total numeric(38,2) not null,
    observacao varchar(255) null,
    pedido_id uuid not null,
    produto_id uuid not null,

    primary key (id),
    constraint uk_item_pedido_produto unique (pedido_id, produto_id),

    constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido,
    constraint fk_item_pedido_produto foreign key (produto_id) references produto
);