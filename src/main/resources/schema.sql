create table airports
  (airport_id int not null auto_increment primary key,
  airport_name varchar (5) not null);

create table airlines
  (airline_id int not null auto_increment primary key,
  airline_name varchar (10) not null);

create table airplanes
  (airplane_id int not null auto_increment primary key,
  airplane_name varchar (5) not null,
  airline_id int not null,
  foreign key (airline_id) references airlines (airline_id));