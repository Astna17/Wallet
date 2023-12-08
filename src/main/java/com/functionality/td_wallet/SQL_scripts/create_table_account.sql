create table if not exists "account"(
    "id_account" int primary key not null,
    "name" varchar(100) not null,
    "balance" float not null,
    "balance_update" date not null,
    "type" varchar(40) not null,
    "id_devise" int references devise(id_devise)
);

insert into "account" values (1,'compte courant', '4000000', '2023-11-30', 'banque',1);
insert into "account" values (2,'compte courant', '550', '2023-12-01', 'banque',2);
insert into "account" values (3,'compte épargne', '90000', '2023-12-05', 'mobile money',1);