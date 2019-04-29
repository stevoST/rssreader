drop table if exists article;

create table article(
id bigint not null primary key,
description varchar (256),
link varchar (256) unique,
pub_date varchar (256),
title varchar (256)
);