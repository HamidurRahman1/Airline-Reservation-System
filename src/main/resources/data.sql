insert into airports (airport_name) values ('JFK'), ('DTW'), ('LAX'), ('LAS');

insert into airlines (airline_name) values ('Delta'), ('JetBlue'), ('Spirit'), ('United');

insert into airplanes (airplane_name, airline_id) values
('D107', 1), ('D235', 1), ('D380', 1), ('D710', 1),
('J130', 2), ('J238', 2), ('J395', 2), ('J725', 2),
('S133', 3), ('S236', 3), ('S390', 3), ('S720', 3),
('U135', 4), ('U238', 4), ('U387', 4), ('U718', 4);

insert into customers (first_name, last_name, email) values
('Hamidur', 'Rahman', 'random@email.com'),
('user2', 'user2', 'user2@email.com'),
('John', 'Doe', 'john@email.com'),
('Jane', 'Doe', 'jane@email.com'),
('user1first', 'user1last', 'user1first@email.com');

insert into sources (date_time, airport_id) values ('2020-01-14 11:30:34', 1);
insert into destinations (date_time, airport_id) values ('2020-01-14 13:35:56', 2);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('1FF1', 1, 1, 1, 45.50, 10, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-15 9:30:34', 2);
insert into destinations (date_time, airport_id) values ('2020-01-15 13:35:56', 3);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('2FF2', 2, 2, 5, 50.75, 5, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-14 11:30:34', 3);
insert into destinations (date_time, airport_id) values ('2020-01-14 13:35:56', 4);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('3FF3', 3, 3, 9, 45.50, 10, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-15 9:30:34', 4);
insert into destinations (date_time, airport_id) values ('2020-01-15 13:35:56', 1);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('4FF4', 4, 4, 13, 50.75, 5, 'ON_TIME');

--

insert into sources (date_time, airport_id) values ('2020-01-14 11:30:34', 2);
insert into destinations (date_time, airport_id) values ('2020-01-14 13:35:56', 4);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('5FF5', 5, 5, 2, 55.50, 10, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-15 9:30:34', 1);
insert into destinations (date_time, airport_id) values ('2020-01-15 13:35:56', 3);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('6FF6', 6, 6, 6, 57.75, 5, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-14 11:30:34', 3);
insert into destinations (date_time, airport_id) values ('2020-01-14 13:35:56', 2);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('7FF7', 7, 7, 10, 58.50, 10, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-15 9:30:34', 4);
insert into destinations (date_time, airport_id) values ('2020-01-15 13:35:56', 1);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('8FF8', 8, 8, 14, 54.75, 5, 'ON_TIME');

--

insert into sources (date_time, airport_id) values ('2020-01-14 11:30:34', 4);
insert into destinations (date_time, airport_id) values ('2020-01-14 13:35:56', 3);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('9FF9', 9, 9, 3, 68.50, 10, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-15 9:30:34', 3);
insert into destinations (date_time, airport_id) values ('2020-01-15 13:35:56', 1);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('10FF', 10, 10, 7, 69.75, 5, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-14 11:30:34', 1);
insert into destinations (date_time, airport_id) values ('2020-01-14 13:35:56', 2);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('11FF', 11, 11, 11, 67.50, 10, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-15 9:30:34', 2);
insert into destinations (date_time, airport_id) values ('2020-01-15 13:35:56', 4);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('12FF', 12, 12, 15, 68.75, 5, 'ON_TIME');

--

insert into sources (date_time, airport_id) values ('2020-01-14 11:30:34', 1);
insert into destinations (date_time, airport_id) values ('2020-01-14 13:35:56', 4);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('13FF', 13, 13, 4, 90.00, 10, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-15 9:30:34', 2);
insert into destinations (date_time, airport_id) values ('2020-01-15 13:35:56', 3);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('14FF', 14, 14, 8, 94.25, 5, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-14 11:30:34', 3);
insert into destinations (date_time, airport_id) values ('2020-01-14 13:35:56', 1);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('15FF', 15, 15, 12, 93.99, 10, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2020-01-15 9:30:34', 4);
insert into destinations (date_time, airport_id) values ('2020-01-15 13:35:56', 2);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('16FF', 16, 16, 16, 91.75, 5, 'ON_TIME');

--

insert into reservations (date_time, status, customer_id, flight_id) values
('2020-01-14 15:20:23', 'ACTIVE', 1, 1),
('2020-01-14 15:20:23', 'ACTIVE', 1, 2),
('2020-01-14 15:20:23', 'CANCELLED', 1, 3),
('2020-01-14 15:20:23', 'CANCELLED', 1, 4),

('2020-01-14 15:20:23', 'ACTIVE', 2, 3),
('2020-01-14 15:20:23', 'ACTIVE', 2, 4),
('2020-01-14 15:20:23', 'CANCELLED', 2, 1),
('2020-01-14 15:20:23', 'CANCELLED', 2, 2),

('2020-01-14 15:20:23', 'ACTIVE', 3, 5),
('2020-01-14 15:20:23', 'ACTIVE', 3, 6),
('2020-01-14 15:20:23', 'CANCELLED', 3, 7),
('2020-01-14 15:20:23', 'CANCELLED', 3, 8),

('2020-01-14 15:20:23', 'ACTIVE', 4, 7),
('2020-01-14 15:20:23', 'ACTIVE', 4, 8),
('2020-01-14 15:20:23', 'CANCELLED', 4, 5),
('2020-01-14 15:20:23', 'CANCELLED', 4, 6);

insert into customers_flights (flight_id, customer_id) values (3, 2)