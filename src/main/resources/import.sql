insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');

insert into restaurante (nome,taxa_frete,cozinha_id) values ('padrao1',1,1);
insert into restaurante (nome,taxa_frete, cozinha_id) values ('padrao2',2,2);
insert into restaurante (nome,taxa_frete, cozinha_id) values ('padrao3',0,1);
insert into restaurante (nome,taxa_frete, cozinha_id) values ('padrao4',0,2);

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

insert into forma_pagamento(id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento(id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento(id, descricao) values (3, 'Dinheiro');

insert into restaurante_forma_pagamento( restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
