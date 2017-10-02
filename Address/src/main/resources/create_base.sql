create database addresses;

create table streets (
  id serial primary key,
  name varchar(100) NOT NULL UNIQUE
);

create table houses (
  id serial primary key,
  name varchar(100) NOT NULL,
  street_id int references streets(id) NOT NULL
);

create table apartments (
  id serial primary key,
  name varchar(100) NOT NULL,
  house_id int references houses(id) NOT NULL
);