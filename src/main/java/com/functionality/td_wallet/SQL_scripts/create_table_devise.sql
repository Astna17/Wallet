create table if not exists "devise"(
    "id_devise" int primary key not null,
    "name" varchar(20) not null,
    "code" varchar(10) not null
);

insert into "devise" values (1, 'Ariary','AR');
insert into "devise" values (2, 'Euro','EUR');