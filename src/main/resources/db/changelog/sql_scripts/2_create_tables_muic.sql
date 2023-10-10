create table images
(
    id         uuid
        primary key,
    name varchar(255),
    type varchar(255),
    data oid
);

create table singers
(
    id         uuid
        primary key,
    created_at timestamp,
    updated_at timestamp,
    name varchar(255),
    image_id uuid,
    FOREIGN KEY (image_id) REFERENCES images(id)
);

create table genres
(
    id         uuid
        primary key,
    name varchar(255)
);

create table musics
(
    id         uuid
        primary key,
    created_at timestamp,
    updated_at timestamp,
    name varchar(255),
    date_of_creation timestamp,
    link varchar(255),
    image_id uuid,
    FOREIGN KEY (image_id) REFERENCES images(id)
);



create table music_singers
(
    music_id uuid not null
        constraint fk_music_id_singers
            references musics,
    singer_id uuid not null
        constraint fr_singer_id
            references singers
);

create table music_genres
(
    music_id uuid not null
        constraint fk_music_id_genres
            references musics,
    genre_id uuid not null
        constraint fr_genre_id
            references genres
);