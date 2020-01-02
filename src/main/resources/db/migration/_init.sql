create table user (
    id integer not null,
    email varchar(255),
    name varchar(255),
    secondname varchar(255),
    status varchar(255),
    time_change timestamp,
    uri_image varchar(255),
    primary key (id)
);

create sequence hibernate_sequence start with 1 increment by 1;