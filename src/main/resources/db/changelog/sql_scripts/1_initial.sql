-- auto-generated definition
create table users
(
    id         uuid
        primary key,
    created_at timestamp,
    updated_at timestamp,
    email varchar(255),
    username varchar(255),
    password   varchar(255)
);

create table roles
(
    id         uuid
        primary key,
    name varchar(255)
);

create table user_roles
(
    user_id uuid not null
        constraint fk_user_id
        references users,
    role_id uuid not null
        constraint fr_role_id
        references roles
);