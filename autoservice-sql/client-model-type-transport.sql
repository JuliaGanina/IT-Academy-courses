CREATE TABLE model_type
(
    id         int2 generated always AS IDENTITY PRIMARY KEY,
    model_name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO model_type(model_name)
VALUES ('AUDI A6 2006'),
       ('BMW X6 2015'),
       ('HONDA CIVIC 2009'),
       ('LEXUS RX 300 2016'),
       ('MERCEDES SPRINTER 2000'),
       ('IVECO DAILY 2008'),
       ('IVECO EUROCARGO 1993'),
       ('MAN TGM 2019'),
       ('VW TRANSPORTER T1 1952');

CREATE TABLE transport_type
(
    id        int2 generated always AS IDENTITY PRIMARY KEY,
    type_name varchar(50) UNIQUE NOT NULL
);

INSERT INTO transport_type(type_name)
VALUES ('CAR'),
       ('TRUCK'),
       ('MICROAUTOBUS');

CREATE TABLE client
(
    id         int4 generated always AS IDENTITY PRIMARY KEY,
    first_name varchar(30) NOT NULL,
    last_name  varchar(30) NOT NULL
);

INSERT INTO client(first_name, last_name)
VALUES ('Pablo', 'Picasso'),
       ('Charlie', 'Chaplin'),
       ('Jimi', 'Hendrix'),
       ('Marylin', 'Monroe'),
       ('Vasiliy', 'Pupkin');

CREATE TABLE transport
(
    id                int4 generated always AS IDENTITY PRIMARY KEY,
    model_type_id     int2 NOT NULL,
    transport_type_id int2 NOT NULL,
    client_id         int4,

    CONSTRAINT fk_transport_model_type_id FOREIGN KEY (model_type_id) REFERENCES model_type (id),
    CONSTRAINT fk_transport_transport_type_id FOREIGN KEY (transport_type_id) REFERENCES transport_type (id),
    CONSTRAINT fk_transport_client_id FOREIGN KEY (client_id) REFERENCES client (id)
);

INSERT INTO transport (model_type_id, transport_type_id, client_id)
VALUES (1, 1, 5),
       (2, 1, 1),
       (3, 1, NULL),
       (4, 1, 2),
       (5, 3, NULL),
       (6, 2, 3),
       (7, 2, NULL),
       (8, 2, NULL),
       (9, 3, 4);