create table if not exists configuration (
id bigint not null AUTO_INCREMENT PRIMARY KEY,
feed_name varchar (256),
feed_link varchar (256) unique,
);