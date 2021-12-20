insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Argentina');
insert into cozinha (nome) values ('Brasileira');

insert into estado(nome) values ('Sao Paulo');
insert into estado(nome) values ('Rio de Janeiro');
insert into estado(nome) values ('Minas Gerais');
insert into estado(nome) values ('Amazonas');
insert into estado(nome) values ('Goias');

insert into cidade(nome, estado_id) values ('Contagem', 3);
insert into cidade(nome, estado_id) values ('Uberlandia', 3);
insert into cidade(nome, estado_id) values ('Sumare', 1);
insert into cidade(nome, estado_id) values ('Campinas', 1);
insert into cidade(nome, estado_id) values ('Goiania', 5);

insert into restaurante (nome,taxa_frete,cozinha_id, data_cadastro, data_atualizacao) values ('padrao1',1,1, utc_timestamp, utc_timestamp);
insert into restaurante (nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('padrao2',2,2, utc_timestamp, utc_timestamp);
insert into restaurante (nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('padrao3',0,1, utc_timestamp, utc_timestamp);
insert into restaurante (nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('padrao4',0,2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao) values ('Thai Gourmet', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);


insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);


insert into forma_pagamento(id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento(id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento(id, descricao) values (3, 'Dinheiro');

insert into restaurante_forma_pagamento( restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);