CREATE TABLE IF NOT EXISTS role (
                      id bigint PRIMARY KEY,
                      name character varying(255)
);

CREATE TABLE IF NOT EXISTS users (
                        id bigint PRIMARY KEY,
                        username character varying(250),
                        password character varying(250)
);

CREATE TABLE IF NOT EXISTS users_roles (
                            user_id bigint REFERENCES users(id),
                            roles_id bigint REFERENCES role(id),
                            CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, roles_id)
);
