-- Databank immo jaar 2011 - 2012

use immo;

-- Vorige tabellen verwijderen: uitcommentariëren als je het script een tweede keer laat lopen

-- drop table bod;
-- drop table gebouwfoto;
-- drop table pand;
-- drop table persoon;
-- drop table gebouwType;
-- drop table foto;
-- drop table verwarmingsType;
-- drop table gemeente;

-- Tabellen maken

create table gemeente (

	id	int not null unique auto_increment,
	postcode int not null,
	naam  varchar(100) not null,
	primary key (id)

);

create table verwarmingsType (

	id	int not null unique auto_increment,
	naam  varchar(100) not null,
	primary key (id)

);

create table foto (

	id	int not null unique auto_increment,
	link  varchar(100) not null,
	primary key (id)

);

create table gebouwType (

	id	int not null unique auto_increment,
	naam  varchar(100) not null,
	primary key (id)

);

create table persoon (

	id	int not null unique auto_increment,
	login varchar(100) not null,
	email varchar(100) not null,
	naam  varchar(100) not null,
	wachtwoord varchar(100) not null,
	geboortedatum date not null,
	primary key (id)

);

create table pand (

	id	int not null unique auto_increment,
	aantalslaapkamers int default 0,
	tuin bool default false,
	straat varchar(100) not null,
	nummer int default 0,
	gemeenteId int not null,
	terras bool default false,
	aantalbadkamers int default 0,
	bewoonbareruimte double default 0,
	prijs double default 0,
	verwarmingsId int not null,
	status varchar(100) not null,
	gebouwId int not null,
	verkopersId int not null,
	
	primary key (id),
	foreign key (gemeenteId) references gemeente(id),
	foreign key (verwarmingsId) references verwarmingsType(id),	foreign key (gebouwId) references gebouwType(id),
	foreign key (verkopersId) references persoon(id)


);

create table gebouwFoto (
	
	id	int not null unique auto_increment,
	pandId int not null,
	fotoId int not null,

	primary key (id),
	foreign key (pandId) references pand(id),
	foreign key (fotoId) references foto(id)

);

create table bod (

	id	int not null unique auto_increment,
	persoonId int not null,
	pandId int not null,
	prijs double default 0,
	datum Date not null,

	primary key (id),
	foreign key (persoonId) references persoon(id),
	foreign key (pandId) references pand(id)

);

-- User aanmaken

GRANT ALL PRIVILEGES ON immo.* TO 'gast'@'localhost' IDENTIFIED BY 'gast' WITH GRANT OPTION;

\. gemeentes.sql
\. verwarmingsType.sql
\. gebouwType.sql
\. foto.sql
\. persoon.sql
\. pand.sql
\. gebouwfoto.sql
\. bod.sql
