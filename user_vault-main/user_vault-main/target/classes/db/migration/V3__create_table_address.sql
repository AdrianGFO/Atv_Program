CREATE TABLE address
(
    id serial NOT NULL,
    cep character varying,
    address_name character varying,
    district character varying,
    city character varying,
    state character varying,
    user_create_id serial,
    PRIMARY KEY (id)
);
