CREATE TABLE  if not exists "currencyValue" (
                                                idCurrency int PRIMARY KEY AUTO_INCREMENT,
                                                id_source_currency int not null ,
                                                id_target_currency int not null,
                                                balance double precision,
                                                date_update date
);

INSERT INTO CurrencyValue (id_currency, id_currency_source, id_target_currency, balance, date_update)
VALUES ('ID_Devise_euro', 'ID_Devise_Ariary', 4500, '2023-12-05');

INSERT INTO CurrencyValue (id_currency, id_currency_source, id_target_currency, balance, date_update)
VALUES ('ID_Devise_euro', 'ID_Devise_Ariary', 4600, '2023-12-06');

INSERT INTO CurrencyValue (id_currency, id_currency_source id_target_currency, balance, date_update)
VALUES ('ID_Devise_euro', 'ID_Devise_Ariary', 5000, '2023-12-07');
