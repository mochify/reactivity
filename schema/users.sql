create table person (
  person_id bigserial primary key,
  email varchar(254) unique not null,
  username varchar(64) unique not null,
  password character(60) not null,
  first_name varchar(100),
  last_name varchar(100),
  last_login timestamp with time zone,
  created_on timestamp with time zone
);

create table comment (
  comment_id bigserial primary key,
  status_id bigint not null,
  person_id bigint not null,
  channel_id bigint not null,
  message json not null,
  comment_date timestamp with time zone not null
);

create table status (
  status_id bigserial primary key,
  person_id bigint not null,
  status_date timestamp with time zone not null,
  message json not null
);

create table channel (
  channel_id bigserial primary key,
  channel_name varchar(255) not null,
  organization_id bigint
);

create table organization (
  organization_id bigserial primary key,
  organization_name varchar(255) unique not null,
  owner bigserial
  creation_date timestamp with time zone not null
);
