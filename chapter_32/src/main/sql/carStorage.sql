select * from users;
insert into users (login, password) values ('user_a', 'root_a');
insert into users (login, password) values ('user_b', 'root_b');
insert into users (login, password) values ('user_c', 'root_c');
insert into users (login, password) values ('user_d', 'root_d');

select * from car_bodies;
create table car_bodies(
    id serial primary key,
    name character varying(20)
);
insert into car_bodies (name) values ('cupe');
insert into car_bodies (name) values ('sedan');
insert into car_bodies (name) values ('universal');

select * from engines;
create table engines(
    id serial primary key,
    name character varying(20)
);
insert into engines (name) values ('rotor');
insert into engines (name) values ('petrol');
insert into engines (name) values ('diesel');

select * from transmissions;
create table transmissions(
    id serial primary key,
    name character varying(20)
);
insert into transmissions (name) values ('robot');
insert into transmissions (name) values ('avtomat');
insert into transmissions (name) values ('mechanical');

select * from cars;
create table cars(
    id serial primary key,
    brand character varying(20),
    model character varying(20),
    body integer references car_bodies(id),
    yearOfManufacture smallint,
    mileage integer,
    transmission_id integer references transmissions(id),
    engine_id integer references engines(id),
    description character varying(20),
    sold boolean default FALSE,
    author integer references users(id),
    foto bytea,
    create_date timestamp not null default now()
);
insert into cars (transmission_id, engine_id, body, author) values (1,1,1,1);
insert into cars (transmission_id, engine_id, body, author) values (2,2,2,2);
insert into cars (transmission_id, engine_id, body, author) values (3,3,3,3);
update cars set brand='Audi', model='A1', yearOfManufacture=2001, mileage=11111, description='good avto', author=1 where id=1;
update cars set brand='Bmw', model='X1', yearOfManufacture=2002, mileage=22222, description='vary good avto', author=2 where id=2;
update cars set brand='Citroen', model='101', yearOfManufacture=2003, mileage=33333, description='avto is cool', author=3 where id=3;