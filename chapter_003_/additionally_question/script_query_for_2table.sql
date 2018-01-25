CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values(1, 'company_1');
insert into company(id, name) values(2, 'company_2');
insert into company(id, name) values(3, 'company_3');
insert into company(id, name) values(4, 'company_4');
insert into company(id, name) values(5, 'company_5');

insert into person(id, name, company_id) values(1, 'person_1', 1);
insert into person(id, name, company_id) values(2, 'person_2', 1);
insert into person(id, name, company_id) values(3, 'person_3', 2);
insert into person(id, name, company_id) values(4, 'person_4', 3);
insert into person(id, name, company_id) values(5, 'person_5', 3);
insert into person(id, name, company_id) values(6, 'person_6', 3);
insert into person(id, name, company_id) values(7, 'person_7', 5);
insert into person(id, name, company_id) values(8, 'person_8', 5);
insert into person(id, name, company_id) values(9, 'person_9', 5);

select * from company;
select * from person;

--1
select p.name, c.name from person as p left join company as c ON p.company_id=c.id
where c.id !=5;

--2 пример с выводом одной строчки
select c.name, count(p.id) from person as p right join company as c ON p.company_id=c.id
group by c.name
order by count(p.id) DESC
LIMIT 1;

--2 пример если строк несколько
select c.name, count(p.id) from person as p right join company as c ON p.company_id=c.id
group by c.name
having count(p.id)=(
    select MAX(y.col_pers) from (
		select c.name, count(p.id) as col_pers from person as p right join company as c ON p.company_id=c.id
	group by c.name) y
);