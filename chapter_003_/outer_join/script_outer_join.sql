create table transmissions (
    id serial primary key,
    name character varying(200)
);

create table car_bodies (
    id serial primary key,
    name character varying(200)
);

create table engines (
    id serial primary key,
    name character varying(200)
);

create table cars (
    id serial primary key,
    name character varying(200),
    transmission_id integer references transmissions(id),
    car_body_id integer references car_bodies(id),
    engine_id integer references engines(id)
);

insert into transmissions(name) values('automatic');
insert into transmissions(name) values('mechanical');
insert into transmissions(name) values('variator');

insert into car_bodies(name) values('sedan');
insert into car_bodies(name) values('station wagon');
insert into car_bodies(name) values('crossover');

insert into engines(name) values('petrol');
insert into engines(name) values('diesel');
insert into engines(name) values('rotor');

insert into cars(name, transmission_id, car_body_id, engine_id) values('car_1',1,1,1);
insert into cars(name, transmission_id, car_body_id, engine_id) values('car_2',2,2,2);
insert into cars(name, transmission_id, car_body_id, engine_id) values('car_3',1,2,1);

--1.Информация про машину(имя машины-имя трансми-имя кузова-имя двигателя)
select c.name, t.name, cb.name, e.name from cars as c
left outer join transmissions as t on c.transmission_id=t.id
left outer join car_bodies as cb on c.car_body_id=cb.id
left outer join engines as e on c.engine_id=e.id;

--2.
select t.name from cars as c 
right outer join transmissions as t on c.transmission_id=t.id
where c.transmission_id is null; 

--3.
select cb.name from cars as c 
right outer join car_bodies as cb on c.car_body_id=cb.id
where c.car_body_id is null; 

--4.
select e.name from cars as c 
right outer join engines as e on c.engine_id=e.id
where c.engine_id is null; 