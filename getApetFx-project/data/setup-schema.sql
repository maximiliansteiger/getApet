-- Setup Development Schema
drop schema if exists get_a_pet cascade;

create schema get_a_pet;

create table get_a_pet.User
(
    id       int primary key generated always as identity,
    name     varchar(255),
    email    varchar(255),
    Password varchar(255),
    phoneNr varchar (255)
);



insert into get_a_pet.User (name, email, Password,phoneNr)
values ('maxi', 'maximiliansteiger15@gmail.com', md5('maxi'),'065056249883'),
       ('robin', 'maximiliansteiger15@gmail.com', md5('robin'),'96647892345'),
       ('Elias', 'maximiliansteiger15@gmail.com', md5('Elias'),'365423432534'),
       ('Nico', 'maximiliansteiger15@gmail.com', md5('passpass'),'8764577686'),
       ('Eder', 'maximiliansteiger15@gmail.com', md5('f34bifesfha9384awhe0oaihf'),'098786543433'),
       ('Mia', 'maximiliansteiger15@gmail.com', md5('dasIstMeinPasswort'),'42536776548');


create table get_a_pet.Animal
(
    id      int primary key generated always as identity,
    name    varchar(255),
    age     int,
    gender  varchar(255),
    species varchar(255),
    breed   varchar(255),
    height  DOUBLE PRECISION,
    weight  DOUBLE PRECISION,
    city    varchar(255),
    ownerId int,
    FOREIGN KEY (ownerId) REFERENCES get_a_pet.User (id)
);


insert into get_a_pet.Animal (name,age, gender, species, breed, height, weight, city, ownerId)
values ('Bello',5, 'MALE', 'Dog', 'Bulldog', 50, 45.5, 'LA', 4),
       ('Rolf',3, 'MALE', 'Dog', 'Bulldog', 50, 45.5, 'LA', 3),
       ('Minki',1, 'FEMALE', 'Cat', 'American Shorthair', 50, 45.5, 'LA', 3),
       ('Tommi',5, 'MALE', 'Cat', 'Bengal', 50, 45.5, 'LA', 4),
       ('Tiger',12, 'MALE', 'Cat', 'Bengal', 50, 45.5, 'LA', 2),
       ('Birdy',2, 'FEMALE', 'Bird', 'Australian King Parrot', 50, 45.5, 'LA', 4),
       ('Ebby',4, 'FEMALE', 'Dog', 'Akani', 50, 45.5, 'LA', 1);


create table get_a_pet.likes
(
    id_user      int,
    id_animal    int,
    FOREIGN KEY (id_user) REFERENCES get_a_pet.User (id),
FOREIGN KEY (id_animal) REFERENCES get_a_pet.Animal (id)
);

insert into get_a_pet.likes (id_user, id_animal) values (2,3);
insert into get_a_pet.likes (id_user, id_animal) values (2,4);
insert into get_a_pet.likes (id_user, id_animal) values (2,5);
insert into get_a_pet.likes (id_user, id_animal) values (2,6);
insert into get_a_pet.likes (id_user, id_animal) values (3,1);
insert into get_a_pet.likes (id_user, id_animal) values (3,2);
insert into get_a_pet.likes (id_user, id_animal) values (4,3);
