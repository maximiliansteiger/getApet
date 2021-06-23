-- Setup Development Schema
drop schema if exists get_a_pet cascade;

create schema get_a_pet;

create table get_a_pet.User
(
    id         int primary key generated always as identity,
    name       varchar(255),
    email      varchar(255),
    Password   varchar(255),
    phoneNr    varchar(255),
    lastAnimal int
);



insert into get_a_pet.User (name, email, Password, phoneNr, lastAnimal)
values ('maxi', 'maximiliansteiger15@gmail.com', md5('maxi'), '065056249883', 1),
       ('robin', 'maximiliansteiger15@gmail.com', md5('robin'), '96647892345', 3),
       ('Elias', 'maximiliansteiger15@gmail.com', md5('Elias'), '365423432534', 1),
       ('Nico', 'maximiliansteiger15@gmail.com', md5('passpass'), '8764577686', 1),
       ('Eder', 'maximiliansteiger15@gmail.com', md5('140403'), '098786543433', 1),
       ('Mia', 'maximiliansteiger15@gmail.com', md5('dasIstMeinPasswort'), '42536776548', 1),
       ('Tierheim Leonding', 'maximiliansteiger15@gmail.com', md5('leondingOnAir'), '0664243589', 1);

create table get_a_pet.Animal
(
    id       int primary key generated always as identity,
    name     varchar(255),
    age      int,
    gender   varchar(255),
    species  varchar(255),
    breed    varchar(255),
    height   DOUBLE PRECISION,
    weight   DOUBLE PRECISION,
    city     varchar(255),
    ownerId  int,
    imageURL varchar(255),
    FOREIGN KEY (ownerId) REFERENCES get_a_pet.User (id)
);


insert into get_a_pet.Animal (name, age, gender, species, breed, height, weight, city, ownerId,imageURL)
values ('Bello', 5, 'MALE', 'Dog', 'Bulldog', 100, 30, 'Kirchdorf', 4,'https://cdn.discordapp.com/attachments/781930875490926604/855396817634328618/puppy-1118584_1920.jpg'),
       ('Rolf', 3, 'MALE', 'Dog', 'Bulldog', 120, 40, 'Linz', 3,'https://upload.wikimedia.org/wikipedia/commons/a/a4/Racib%C3%B3rz_2007_082.jpg'),
       ('Minki', 1, 'FEMALE', 'Cat', 'American Shorthair', 3.5, 45.5, 'Linz', 3,'https://cdn.wamiz.de/media/cache/upload_16by9_1200/uploads/animal/breed/cat/adult/5cd024bd63691237029043.jpg'),
       ('Tommi', 5, 'MALE', 'Cat', 'Bengal', 21, 4, 'Linz', 4,'https://excitedcats.com/wp-content/uploads/2020/12/bengal-cat-standing-by-the-window.jpg'),
       ('Tiger', 12, 'MALE', 'Cat', 'Bengal', 23, 5, 'Gunskirchen', 2,'https://petkeen.com/wp-content/uploads/2021/03/bengal-cat-lying-outdoor.jpg'),
       ('Birdy', 2, 'FEMALE', 'Bird', 'Australian King Parrot', 0.3, 45.5, 'Kirchdorf', 4,'http://2.bp.blogspot.com/-aSEgXNBNom0/Te46sUZHFFI/AAAAAAAABkc/ki-gZfWTxpA/s1600/Australian-king_parrot.jpg'),
       ('Nissi', 4, 'FEMALE', 'Snake', 'Python', 100, 17, 'Schlierbach', 1,'http://livingjunglepetshop.com.au/wp-content/uploads/2014/11/King-Parrot-Parakeet2.jpg'),
       ('Abby', 1, 'FEMALE', 'Bird', 'Australian King Parrot', 10, 0.4, 'Kirchdorf', 4,'https://pfotencafe.de/wp-content/uploads/2017/11/bengal-2042592_640.jpg'),
       ('Amy', 1, 'FEMALE', 'Cat', 'Bengal', 10, 3.4, 'Kirchdorf', 4,'https://pfotencafe.de/wp-content/uploads/2017/11/bengal-2042592_640.jpg'),
       ('Aida', 1, 'FEMALE', 'Dog', 'Springer Spaniel', 60, 45.5, 'Kirchdorf', 4,'https://www.zooplus.de/magazin/wp-content/uploads/2018/05/English-Springer-Spaniel-schwarz.jpg'),
       ('Batman', 5, 'MALE', 'Cat', 'Bengal', 24, 2, 'Leonding', 7,'http://bero-bengal.eu/wp-content/uploads/2020/05/IMG_20190409_124752-scaled-e1588773923957-1024x530.jpg'),
       ('Albatros', 4, 'MALE', 'Dog', 'Bullmastiff', 30, 60, 'Leonding', 7,'https://passion-hund.de/wp-content/uploads/2021/02/shutterstock_1765149920.jpg'),
       ('Alex', 23, 'MALE', 'Cat', 'Bengal', 22, 3.5, 'Leonding', 7,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHaOxLJR52hNpBD9MqLVvrVl9Ylt6HRn9mT15sQhtL9gzPZ1IpYefSWR8tbf1AfedHDe8&usqp=CAU'),
       ('Alpha', 2, 'MALE', 'Dog', 'Bullmastiff', 80, 83, 'Leonding', 7,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpS6ntv-Uk8ZVEE-dWB8Ij2Iq_sYREVX8xCw&usqp=CAU'),
       ('Leo', 3, 'MALE', 'Cat', 'Bengal', 20, 4, 'Leonding', 7,'https://www.zooroyal.de/magazin/wp-content/uploads/2015/08/Bengalkatze-1-760x570.jpg'),
       ('Lisa', 9, 'FEMALE', 'Dog', 'Whippet', 90, 80, 'Leonding', 7,'https://windhundhilfe.de/images/2019/08/29/remi19_1.jpg'),
       ('Alvin', 2, 'MALE', 'Cat', 'Bengal', 17, 4, 'Leonding', 7,'https://www.petplan.de/wp-content/uploads/2019/04/Bengalkatze.jpg'),
       ('Alesi', 13, 'MALE', 'Dog', 'Bulldog', 40, 23, 'Leonding', 7,'https://www.zooplus.de/magazin/wp-content/uploads/2020/07/olde-english-bulldogge-schwarz-im-garten-1024x797.jpeg'),
       ('Julian', 2, 'MALE', 'Cat', 'Norwegische Waldkatze', 19, 6, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855396231085555722/pexels-anete-lusina-4790621.jpg'),
       ('Birgit', 19, 'FEMALE', 'Cat', 'Chartreux', 38, 10, 'Schlierbach', 1,'https://cdn.discordapp.com/attachments/781930875490926604/855396711501660170/Chartreux850.jpeg'),
       ('Helena', 2, 'FEMALE', 'Cat', 'Deutsch Langhaar', 11, 13, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855397437917233182/Deutsch-Langhaar-Rasseportrait.jpeg'),
       ('Maria', 4, 'FEMALE', 'Cat', 'Wildkatze', 11, 13, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855397887915982888/shutterstock_118634098-1270x608.jpeg'),
       ('Markus', 7, 'MALE', 'Dog', 'Beagle', 24, 18, 'Leonding', 7,'https://img2.storyblok.com/f/83829/1200x628/bccf29e577/chien-race-beagle.jpg'),
       ('Alois', 15, 'MALE', 'Dog', 'Bernhardiner', 44, 13, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855400063748800512/dog-726006_1920.jpeg'),
       ('Alexi', 6, 'FEMALE', 'Dog', 'Bull Terrier', 19, 5, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855400632060084244/hunde-staffordshire-bullterrier.jpeg'),
       ('Bernhard', 8, 'MALE', 'Dog', 'Bernhardiner', 39, 16, 'Gunskirchen', 2,'https://cdn.discordapp.com/attachments/781930875490926604/855400107046076416/bernhardiner-ernahrung-768x511.jpeg'),
       ('Martin', 16, 'MALE', 'Cat', 'Korat', 34, 18, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855400282057867294/perser-katzenbaby-weiss-1024x768.png'),
       ('Asrael', 14, 'FEMALE', 'Cat', 'Tiegerkatze', 23, 9, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855396551539294238/243024974.jpeg'),
       ('Bert',2, 'FEMALE', 'Snake', 'Python', 4, 30, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855399816821080064/yellow-anaconda-53997_1920.jpg'),
       ('Brad',2, 'MALE', 'Bird', 'Eagle', 3, 45.5, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855400554230448139/adler-2386314_1920.jpg'),
       ('Schweinchen',3, 'FEMALE', 'Guinea Pig', 'Peruan Guinea Pig', 10, 1.2, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855397202335498251/the-guinea-pig-2752716_1280.jpg'),
       ('Hans',8, 'MALE', 'Snake', 'Anaconda', 9, 200, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855399816821080064/yellow-anaconda-53997_1920.jpg'),
       ('Gerti',2, 'FEMALE', 'Gecko', 'Day gecko', 8, 0.02, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855396394478338058/malagasy-taggecko-653653_1920.jpg'),
       ('Tim',10, 'MALE', 'Dog', 'Bengal', 20, 40, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855398114571583488/dog-1868871_1920.jpg'),
       ('Rammler',5, 'MALE', 'Rabbit', 'Blue wiener', 20, 10, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855397587272073216/Blaue-Wiener-3.png'),
       ('Constance',5, 'FEMALE', 'Cat', 'persian Katze', 20, 4, 'Leonding', 7,'https://www.die-tier-welt.com/wp-content/uploads/2018/08/212958494_fotolia_perserkatze.jpg'),
       ('geki',5, 'MALE', 'Gecko', 'Leopard gecko', 10, 0.04, 'Leonding', 7,'https://cdn.discordapp.com/attachments/781930875490926604/855396119956553798/gecko-1373165_1920.jpg');



create table get_a_pet.likes
(
    id_user   int,
    id_animal int,
    FOREIGN KEY (id_user) REFERENCES get_a_pet.User (id),
    FOREIGN KEY (id_animal) REFERENCES get_a_pet.Animal (id)
);

insert into get_a_pet.likes (id_user, id_animal)
values (2, 3);
insert into get_a_pet.likes (id_user, id_animal)
values (2, 4);
insert into get_a_pet.likes (id_user, id_animal)
values (2, 5);
insert into get_a_pet.likes (id_user, id_animal)
values (2, 6);
insert into get_a_pet.likes (id_user, id_animal)
values (3, 1);
insert into get_a_pet.likes (id_user, id_animal)
values (3, 2);
insert into get_a_pet.likes (id_user, id_animal)
values (4, 3);




