create table if not exists commodity (
    id INTEGER PRIMARY KEY NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(4096),
    price bigint NOT NULL,
    available boolean NOT NULL
);