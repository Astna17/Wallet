create table if not exists "transaction"(
    "id_transaction" int primary key not null,
    "label" text not null,
    "amount" float not null,
    "date" datetime not null,
    "type" varchar(30) not null
);


insert into "transaction" values (1, 'prêt bancaire', '100000', '2023-10-20 13:14', 'débit');
insert into "transaction" values (2, 'salaire', '200000', '2023-11-14 08:00', 'crédit');
insert into "transaction" values (3, 'prime', '65000', '2023-12-02 17:30', 'crédit');

alter table transaction add "category" int references category(id_category);
