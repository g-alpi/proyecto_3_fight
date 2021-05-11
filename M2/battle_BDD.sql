drop database battles;
create database battles;
use battles;
/*drop table weapons;*/
create table weapons(
weapon_id int primary key auto_increment,
weapon_name varchar(100),
weapon_image varchar(200),
weapon_speed int,
weapon_strenght int,
weapon_race varchar(100),
weapon_points int
);
/*select* from weapons;*/

/*drop table race;*/
create table race(
race_id int primary key auto_increment,
race_name varchar(10),
health int,
strenght int,
defense int,
agility int ,
speed int,
points int
);

create table warriors(
warrior_id int primary key auto_increment,
warrior_name varchar(100),
warrior_image varchar(200),
race_id int
);


alter table warriors add constraint fk_warrior_race foreign key (race_id) references race(race_id);

create table players(
player_id int primary key auto_increment,
player_name varchar(50)
);

create table ranking(
player_id int,
total_points int,
warrior_id int
);

alter table ranking add constraint fk_ranking_player foreign key (player_id) references players(player_id);
alter table ranking add constraint fk_ranking_warrior foreign key (warrior_id) references warriors(warrior_id);

create table battle (
battle_id int primary key auto_increment,
player_id int,
warrior_id int,
warrior_weapon_id int,
opponent_id int,
opponent_weapon_id int,
injuries_caused int,
injuries_suffered int,
batlle_points int
);

alter table battle add constraint fk_player_battle  foreign key (player_id) references players(player_id);
alter table battle add constraint fk_battle_warrior  foreign key (warrior_id) references warriors(warrior_id);
alter table battle add constraint fk_battle_weapon  foreign key (warrior_weapon_id) references weapons(weapon_id);
alter table battle add constraint fk_battle_opponent  foreign key (opponent_id) references warriors(warrior_id);
alter table battle add constraint fk_battle_opponent_weapon2  foreign key (opponent_weapon_id) references weapons(weapon_id);





insert into race (race_name,health,strenght,defense,agility,speed,points)
values ('human',50,5,3,6,5,20),
('elf',40,4,2,7,7,21),
('dwarf',60,6,4,5,3,21)
;

insert into weapons (weapon_name,weapon_image,weapon_speed,weapon_strenght,weapon_race,weapon_points)
values ('dagger',null,3,0,'human,elf',10),
('sword',null,1,1,'human,elf,dwarf',10),
('axe',null,0,3,'human,dwarf',10),
('double swords',null,2,2,'human,elf',14),
('scimitar',null,2,1,'human,elf',14),
('bow',null,5,1,'elf',15),
('katana',null,3,2,'human',18),
('dirk',null,4,0,'human,elf,dwarf',12),
('two-handed ax',null,3,0,'human,elf',20);





select*from weapons;