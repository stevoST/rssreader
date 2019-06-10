create table if not exists article(
id bigint not null AUTO_INCREMENT PRIMARY KEY,
description text,
link varchar (256) unique,
pub_date varchar (256),
title varchar (256)
);

create table if not exists configuration (
id bigint not null AUTO_INCREMENT PRIMARY KEY,
feed_name varchar (256),
feed_link varchar (256) unique
);