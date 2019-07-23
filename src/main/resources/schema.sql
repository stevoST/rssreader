create table if not exists article(
id bigint not null AUTO_INCREMENT PRIMARY KEY,
description text,
link varchar (256) not null unique,
pub_date varchar (256) not null,
title varchar (256) not null,
test varchar (256) not null default ''
);

create table if not exists configuration (
id bigint not null AUTO_INCREMENT PRIMARY KEY,
feed_name varchar (256) not null,
feed_link varchar (256) not null unique,
feed_item varchar (256) not null
);