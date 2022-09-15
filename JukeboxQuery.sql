create database if not exists jukeboxdb;
use jukeboxdb;
select*from user;
select*from songs ;

create table user(user_id int primary key ,name varchar(30),password varchar(30));

create table songs(song_id int primary key ,song_name varchar(30),
song_artist varchar(20),song_duration varchar(30),
song_album varchar(30) ,song_genre varchar(30), filepath varchar(90));

create table podcast (podcast_id int primary key ,podcast_name varchar(30),
user_id int,filepath varchar (40),
constraint foreign key(user_id) references user(user_id));

create table playlist( playlist_id int ,playlist_name  varchar(30) ,
user_id int,sorp_id int ,
constraint foreign key (user_id) references user(user_id));

create table playlist_details(playlist_name varchar(30) primary key,
playlist_id int ,song_id int,user_id int,
constraint foreign key (song_id) references songs(song_id),
constraint foreign key (user_id) references user(user_id));
 
 
insert into  user values(1,'ajay','abhay@123');
insert into  user values(2,'Mukul','mukul@123');
insert into  user values(3,'Paliwal','paliwal@123');

 
insert into podcast values(201,'chanakya',1,'E:\\jukebox\\podcast\\chanakya.wav');
insert into podcast values(202,'Stereo',2,'E:\\jukebox\\podcast\\sterio.wav');
insert into podcast values(203,'PUBG',3,'E:\\jukebox\\podcast\\pubg.wav');
insert into podcast values(204,'KGF',1,'E:\\jukebox\\podcast\\kgf.wav');

 
 insert into songs values(100,'song1','arjit','02:30','love','romantic',
 'E:\\jukebox\\music\\song1.wav');
 insert into songs values(101,'song2','k.k','02:45','hindi','romantic',
 'E:\\jukebox\\music\\song2.wav');
 insert into songs values(102,'song3','jeetu sharma','05:55','shiv bhakt','devotional',
 'E:\\jukebox\\music\\song3.wav');
 insert into songs values(103,'song4','rochak kohli','03:40','bolywood','romantic',
 'E:\\jukebox\\music\\song4.wav');

drop table playlist_details;
drop table playlist;
drop table podcast;
drop table songs;
drop table user;

select * from songs where song_name = 'song1' 


