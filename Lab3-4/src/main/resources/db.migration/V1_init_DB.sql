CREATE TABLE IF NOT EXISTS cat (
                     id bigint PRIMARY KEY,
                     name character varying(255) NOT NULL,
                     birthdate character varying(255),
                     breed character varying(255),
                     colour character varying(255),
                     tailLength int,
                     ownerid bigint REFERENCES owner(id)
);

CREATE TABLE IF NOT EXISTS owner (
                    id bigint PRIMARY KEY,
                    name character varying(255),
                    birthdate date,
                    email character varying(255),
                    password character varying(255)
);

CREATE TABLE IF NOT EXISTS flea (
                       id bigint PRIMARY KEY,
                       name character varying(255),
                       catid bigint REFERENCES cat(id)
);

