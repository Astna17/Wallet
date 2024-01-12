CREATE TABLE  if not exists "currencyValue" (
                                                idCurrency int PRIMARY KEY not null,
                                                id_source_currency int not null ,
                                                id_target_currency int not null,
                                                balance double precision,
                                                date_update date
);

INSERT INTO "currencyValue" VALUES (1, 3, 2, 4500, '2023-12-05');

INSERT INTO "currencyValue" VALUES (2, 1, 4, 4600, '2023-12-06');

INSERT INTO "currencyValue" VALUES (3, 5, 1, 5000, '2023-12-07');
