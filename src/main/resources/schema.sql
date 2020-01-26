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

create table sources
  (source_id int not null auto_increment primary key,
    date_time datetime not null,
    airport_id int not null,
    foreign key (airport_id) references airports(airport_id));

create table destinations
  (destination_id int not null auto_increment primary key,
    date_time datetime not null,
    airport_id int not null,
    foreign key (airport_id) references airports(airport_id));

create table customers
  (customer_id int not null auto_increment primary key,
    first_name varchar(15) not null,
    last_name varchar (15) null,
    email varchar (25) not null unique);

create table flights
  (flight_id int not null auto_increment primary key,
    flight_code varchar (5) not null,
    source_id int not null,
    destination_id int not null,
    airplane_id int not null,
    fare decimal(5, 2) not null,
    capacity int not null,
    status varchar(9) not null,
    foreign key (source_id) references sources (source_id),
    foreign key (destination_id) references destinations (destination_id),
    foreign key (airplane_id) references airplanes (airplane_id));

create table customers_flights
  (customer_id int not null,
    flight_id int not null,
    foreign key (customer_id) references customers (customer_id),
    foreign key (flight_id) references flights (flight_id));

create table reservations
  (reservation_id int not null auto_increment primary key,
    date_time datetime not null,
    status varchar (9) not null,
    customer_id int not null,
    flight_id int not null,
    foreign key (customer_id) references customers (customer_id),
    foreign key (flight_id) references flights (flight_id));