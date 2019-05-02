create table if not exists article(
id bigint not null AUTO_INCREMENT PRIMARY KEY,
description text,
link varchar (256) unique,
pub_date varchar (256),
title varchar (256)
);