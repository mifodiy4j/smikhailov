create table type (
    id serial primary key,
    name character varying (200)
);

create table product (
    id serial primary key,
    name character varying (200),
    type_id integer references type(id),
    expired_date date,
    price integer
);

insert into type(name) values('СЫР');
insert into type(name) values('МОЛОКО');
insert into type(name) values('КОНДИТЕРСКОЕ ИЗДЕЛИЕ');
insert into type(name) values('МЯСО');
insert into type(name) values('РЫБА');

select * from type;

insert into product(name, type_id, expired_date, price) values('ГАУДА', 1, '2018-02-08', 25);
insert into product(name, type_id, expired_date, price) values('РОССИЙСКИЙ_1', 1, '2018-03-08', 21);
insert into product(name, type_id, expired_date, price) values('РОССИЙСКИЙ_2', 1, '2018-03-08', 22);
insert into product(name, type_id, expired_date, price) values('РОССИЙСКИЙ_3', 1, '2018-03-08', 23);
insert into product(name, type_id, expired_date, price) values('РОССИЙСКИЙ_4', 1, '2018-03-08', 24);
insert into product(name, type_id, expired_date, price) values('РОССИЙСКИЙ_5', 1, '2018-03-08', 25);
insert into product(name, type_id, expired_date, price) values('РОССИЙСКИЙ_6', 1, '2018-03-08', 26);
insert into product(name, type_id, expired_date, price) values('РОССИЙСКИЙ_7', 1, '2018-03-08', 27);
insert into product(name, type_id, expired_date, price) values('РОССИЙСКИЙ_8', 1, '2018-03-08', 28);
insert into product(name, type_id, expired_date, price) values('РОССИЙСКИЙ_9', 1, '2018-03-08', 29);
insert into product(name, type_id, expired_date, price) values('РОССИЙСКИЙ_10', 1, '2018-03-08', 30);
insert into product(name, type_id, expired_date, price) values('ПРОСТОКВАШИНО', 2, '2018-01-20', 49);
insert into product(name, type_id, expired_date, price) values('МОРОЖЕНОЕ', 3, '2018-04-01', 22);
insert into product(name, type_id, expired_date, price) values('МОРОЖЕНОЕ МЯСО СВИНИНА', 4, '2018-03-08', 17);
insert into product(name, type_id, expired_date, price) values('СЕМГА МОРОЖЕНОЕ', 5, '2018-04-13', 27);

select * from product;

--1.Написать запрос получение всех продуктов с типом "СЫР"
select * from product as pr
where pr.type_id = 1;

--2.Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product as pr
where pr.name like '%МОРОЖЕНОЕ%';

--3.Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product as pr
where pr.expired_date < '2018-02-01';

--4.Написать запрос, который вывод самый дорогой продукт.
select pr.name from product as pr
where pr.price=(
    select max(pr.price) from product as pr
);

--5.Написать запрос, который выводит количество всех продуктов определенного типа.
select count(pr.id) from product as pr
where pr.type_id = 1;

--6.Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product as pr
where pr.type_id in (1, 2);

--7.Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select res_type.type_id from (
    select pr.type_id, count(pr.type_id) as type_count
    from product as pr
    group by pr.type_id
) as res_type
where res_type.type_count < 10;

--8.Вывести все продукты и их тип.
select pr.name, t.name from type as t
inner join product as pr on t.id = pr.type_id;