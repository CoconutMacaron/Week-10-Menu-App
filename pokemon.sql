create database if not exists pokedex;

use pokedex;

drop table if exists pokemon;

create table pokemon (
	id int(10) not null auto_increment,
	name varchar(50) not null,
	type varchar(25) not null,
	primary key(id)
);
