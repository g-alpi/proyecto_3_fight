drop database battles;
create database battles;
use battles;
create table weapons(
weapon_name varchar(500) primary key,
weapon_image varchar(500),
weapon_speed int,
weapon_strenght int,
weapon_race varchar(500),
weapon_points int
);

create table race(
race_name varchar(50) primary key,
health int,
strenght int,
defense int,
agility int ,
speed int,
points int
);

create table warriors(
warrior_name varchar(500) primary key,
warrior_image varchar(500),
race_name varchar(50),
Portrait varchar(500),
FstandLoop varchar(500),
BstandLoop varchar(500),
Fattack varchar(500),
Battack varchar(500),
Fattack_bow varchar(500),
Battack_bow varchar(500),
Fdie varchar(500),
Bdie varchar(500),
Fwound varchar(500),
Bwound varchar(500),
Fdodge varchar(500),
Bdodge varchar(500),
dance varchar(500),
cry varchar(500)
);

alter table warriors add constraint fk_warrior_race foreign key (race_name) references race(race_name);


create table players(
player_id int primary key auto_increment,
player_name varchar(50)
);

create table ranking(
player_id int,
total_points int,
warrior_name varchar(500)
);

alter table ranking add constraint fk_ranking_player foreign key (player_id) references players(player_id);
alter table ranking add constraint fk_ranking_warrior foreign key (warrior_name) references warriors(warrior_name);

create table battle (
battle_id int primary key auto_increment,
player_id int,
warrior_name varchar(500),
warrior_weapon_name varchar(500),
opponent_name varchar(500),
opponent_weapon_name varchar(500),
injuries_caused int,
injuries_suffered int,
batlle_points int
);

alter table battle add constraint fk_player_battle  foreign key (player_id) references players(player_id);
alter table battle add constraint fk_battle_warrior  foreign key (warrior_name) references warriors(warrior_name);
alter table battle add constraint fk_battle_weapon  foreign key (warrior_weapon_name) references weapons(weapon_name);
alter table battle add constraint fk_battle_opponent  foreign key (opponent_name) references warriors(warrior_name);
alter table battle add constraint fk_battle_opponent_weapon3  foreign key (opponent_weapon_name) references weapons(weapon_name);



insert into race (race_name,health,strenght,defense,agility,speed,points)
values ('human',50,5,3,6,5,20),
('elf',40,4,2,7,7,21),
('dwarf',60,6,4,5,3,21)
;

insert into weapons (weapon_name,weapon_image,weapon_speed,weapon_strenght,weapon_race,weapon_points)
values ('dagger',null,3,0,'human,elf',50),
('sword',null,1,1,'human,elf,dwarf',50),
('axe',null,0,3,'human,dwarf',50),
('double swords',null,2,2,'human,elf',14),
('scimitar',null,2,1,'human,elf',14),
('bow',null,5,1,'elf',15),
('katana',null,3,2,'human',18),
('dirk',null,4,0,'human,elf,dwarf',12),
('two-handed ax',null,3,0,'human,elf',20);

select * from race;
select * from warriors;

insert into warriors values('Pepe','./warriors/portrait_Pepe.png','human','./warriors/portrait_Pepe.gif','./warriors/FstandLoop_Pepe.gif',
'./warriors/BstandLoop_Pepe.gif','./warriors/Fattack_Pepe.gif','./warriors/Battack_Pepe.gif','./warriors/Fattack_bow_Pepe.gif','./warriors/Battack_bow_Pepe.gif',
'./warriors/Fdie_Pepe.gif','./warriors/Bdie_Pepe.gif','./warriors/Fwound_Pepe.gif','./warriors/Bwound_Pepe.gif','./warriors/Fdodge_Pepe.gif','./warriors/Bdodge_Pepe.gif',
'./warriors/dance_Pepe.gif','./warriors/cry_Pepe.gif')
,('Maria','./warriors/portrait_Maria.png','human','./warriors/portrait_Maria.gif','./warriors/FstandLoop_Maria.gif',
'./warriors/BstandLoop_Maria.gif','./warriors/Fattack_Maria.gif','./warriors/Battack_Maria.gif','./warriors/Fattack_bow_Maria.gif','./warriors/Battack_bow_Maria.gif',
'./warriors/Fdie_Maria.gif','./warriors/Bdie_Maria.gif','./warriors/Fwound_Maria.gif','./warriors/Bwound_Maria.gif','./warriors/Fdodge_Maria.gif','./warriors/Bdodge_Maria.gif',
'./warriors/dance_Maria.gif','./warriors/cry_Maria.gif'),
('Eduardo','./warriors/portrait_Eduardo.png','elf','./warriors/portrait_Eduardo.gif','./warriors/FstandLoop_Eduardo.gif',
'./warriors/BstandLoop_Eduardo.gif','./warriors/Fattack_Eduardo.gif','./warriors/Battack_Eduardo.gif','./warriors/Fattack_bow_Eduardo.gif','./warriors/Battack_bow_Eduardo.gif',
'./warriors/Fdie_Eduardo.gif','./warriors/Bdie_Eduardo.gif','./warriors/Fwound_Eduardo.gif','./warriors/Bwound_Eduardo.gif','./warriors/Fdodge_Eduardo.gif','./warriors/Bdodge_Eduardo.gif',
'./warriors/dance_Eduardo.gif','./warriors/cry_Eduardo.gif'),
('Epifania','./warriors/portrait_Epifania.png','elf','./warriors/portrait_Epifania.gif','./warriors/FstandLoop_Epifania.gif',
'./warriors/BstandLoop_Epifania.gif','./warriors/Fattack_Epifania.gif','./warriors/Battack_Epifania.gif','./warriors/Fattack_bow_Epifania.gif','./warriors/Battack_bow_Epifania.gif',
'./warriors/Fdie_Epifania.gif','./warriors/Bdie_Epifania.gif','./warriors/Fwound_Epifania.gif','./warriors/Bwound_Epifania.gif','./warriors/Fdodge_Epifania.gif','./warriors/Bdodge_Epifania.gif',
'./warriors/dance_Epifania.gif','./warriors/cry_Epifania.gif'),
('Torbjorn','./warriors/portrait_Torbjorn.png','dwarf','./warriors/portrait_Torbjorn.gif','./warriors/FstandLoop_Torbjorn.gif',
'./warriors/BstandLoop_Torbjorn.gif','./warriors/Fattack_Torbjorn.gif','./warriors/Battack_Torbjorn.gif','./warriors/Fattack_bow_Torbjorn.gif','./warriors/Battack_bow_Torbjorn.gif',
'./warriors/Fdie_Torbjorn.gif','./warriors/Bdie_Torbjorn.gif','./warriors/Fwound_Torbjorn.gif','./warriors/Bwound_Torbjorn.gif','./warriors/Fdodge_Torbjorn.gif','./warriors/Bdodge_Torbjorn.gif',
'./warriors/dance_Torbjorn.gif','./warriors/cry_Torbjorn.gif'),
('Torbjina','./warriors/portrait_Torbjina.png','dwarf','./warriors/portrait_Torbjina.gif','./warriors/FstandLoop_Torbjina.gif',
'./warriors/BstandLoop_Torbjina.gif','./warriors/Fattack_Torbjina.gif','./warriors/Battack_Torbjina.gif','./warriors/Fattack_bow_Torbjina.gif','./warriors/Battack_bow_Torbjina.gif',
'./warriors/Fdie_Torbjina.gif','./warriors/Bdie_Torbjina.gif','./warriors/Fwound_Torbjina.gif','./warriors/Bwound_Torbjina.gif','./warriors/Fdodge_Torbjina.gif','./warriors/Bdodge_Torbjina.gif',
'./warriors/dance_Torbjina.gif','./warriors/cry_Torbjina.gif');
