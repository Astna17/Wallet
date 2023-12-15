create table if not exists "category"(
    "id_category" int primary key not null,
    "category_name" varchar(100) not null
);

insert into "category" values (1, 'nourritures et boissons');
insert into "category" values (2, 'achats');
insert into "category" values (3, 'logement');
insert into "category" values (4, 'transports');
insert into "category" values (5, 'véhicule ');
insert into "category" values (6, 'loisirs');
insert into "category" values (7, 'multimédia,PC');
insert into "category" values (8, 'frais financiers');
insert into "category" values (9, 'investissements');
insert into "category" values (10, 'revenu');
insert into "category" values (11, 'autres');
