alter table restaurante add column ativo boolean not null default false;

update restaurante set ativo = true;