select * from cars
select * from users
select * from transmissions

create table users(
    id serial primary key,
    login character varying(20)
);

update cars set brand='Audi', model='A1', yearOfManufacture=2001, mileage=11111, description='good avto', author=1 where id=1;
update cars set brand='Bmw', model='X1', yearOfManufacture=2002, mileage=22222, description='vary good avto', author=2 where id=2;
update cars set brand='Citroen', model='101', yearOfManufacture=2003, mileage=33333, description='avto is cool', author=3 where id=3;

delete from cars where id in (12,13,14);

alter table cars add column brand character varying(20);
alter table cars add column model character varying(20);
alter table cars add column yearOfManufacture smallint;
alter table cars add column mileage integer;
alter table cars add column description character varying(200);
alter table cars add column sold boolean default FALSE;
alter table cars add column author integer references users(id);

alter table cars add column create_date timestamp not null default now();