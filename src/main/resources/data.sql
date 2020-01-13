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

insert into sources (date_time, airport_id) values ('2019-01-14 11:30:34', 1);
insert into destinations (date_time, airport_id) values ('2019-01-14 13:35:56', 2);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('1FF1', 1, 1, 5, 45.50, 10, 'ON_TIME');

insert into sources (date_time, airport_id) values ('2019-01-15 9:30:34', 2);
insert into destinations (date_time, airport_id) values ('2019-01-15 13:35:56', 3);
insert into flights (flight_code, source_id, destination_id, airplane_id, fare, capacity, status)
values ('2FF2', 2, 2, 10, 50.75, 5, 'ON_TIME');