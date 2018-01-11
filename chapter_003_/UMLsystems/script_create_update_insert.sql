create table users (
    id serial primary key,
    first_name text,
    last_name text
);

create table roles (
    id serial primary key,
    description text
);

alter table users add column role_id integer references roles(id);

create table rules (
    id serial primary key,
    description text
);

create table roles_rules (
    role_id integer references roles(id),
    rule_id integer references rules(id)
);

create table items (
    id serial primary key,
    user_id integer references users(id)
);

alter table users add column item_id integer references items(id);

create table icomments (
    id serial primary key,
    description text
);

alter table items add column comment_id integer references icomments(id);

create table attaches (
    id serial primary key,
    file_name text
);

alter table items add column attach_id integer references attaches(id);

create table states (
    id serial primary key,
    description text
);

alter table items add column state_id integer references states(id);

create table categorys (
    id serial primary key,
    description text
);

alter table items add column category_id integer references categorys(id);

insert into users(first_name, last_name) values('roota_name', 'roota');
insert into users(first_name, last_name) values('rootb_name', 'rootb');
insert into users(first_name, last_name) values('rootc_name', 'rootc');
insert into users(first_name, last_name) values('rootd_name', 'rootd');

insert into roles(description) values('Admin');
insert into roles(description) values('Manager');
insert into roles(description) values('User');

insert into rules(description) values('create item');
insert into rules(description) values('delete item');
insert into rules(description) values('change item');
insert into rules(description) values('change state item');

insert into roles_rules(role_id, rule_id) values(1, 1);
insert into roles_rules(role_id, rule_id) values(1, 2);
insert into roles_rules(role_id, rule_id) values(1, 3);
insert into roles_rules(role_id, rule_id) values(1, 4);
insert into roles_rules(role_id, rule_id) values(2, 1);
insert into roles_rules(role_id, rule_id) values(2, 3);
insert into roles_rules(role_id, rule_id) values(3, 1);

insert into icomments(description) values('delivery');
insert into icomments(description) values('pickup');

insert into attaches(file_name) values('c:\picture\1.png');
insert into attaches(file_name) values('c:\picture\2.txt');

insert into states(description) values('in process');
insert into states(description) values('complete');
insert into states(description) values('delete');

insert into categorys(description) values('made by phone call');
insert into categorys(description) values('made by on-line');
insert into categorys(description) values('made by mail');

insert into items(user_id, comment_id, attach_id, state_id, category_id) values(1, 1, 1, 1, 1);
insert into items(user_id, comment_id, attach_id, state_id, category_id) values(2, 2, 2, 1, 1);
insert into items(user_id, comment_id, attach_id, state_id, category_id) values(3, 1, 1, 1, 3);
insert into items(user_id, comment_id, attach_id, state_id, category_id) values(4, 2, 2, 2, 3);
