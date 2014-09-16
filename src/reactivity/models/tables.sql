--name: create-person-table!
create table if not exists person (
  person_id bigserial primary key,
  email text unique not null,
  username text unique not null,
  password text not null,
  first_name text,
  last_name text,
  last_login timestamp with time zone,
  created_on timestamp with time zone
);

--name: create-comment-table!
create table if not exists comment (
  comment_id bigserial primary key,
  status_id bigint not null,
  person_id bigint references person (person_id),
  channel_id bigint not null,
  message json not null,
  comment_date timestamp with time zone not null
);

--name: create-status-table!
create table if not exists status (
  status_id bigserial primary key,
  person_id bigint references person (person_id),
  status_date timestamp with time zone not null,
  message json not null
);

--name: create-channel-table!
create table if not exists channel (
  channel_id bigserial primary key,
  channel_name text not null,
  channel_description text,
  organization_id bigint
);

--name: create-organization-table!
create table if not exists organization (
  organization_id bigserial primary key,
  organization_name text unique not null,
  organization_description text,
  owner bigserial references person (person_id),
  creation_date timestamp with time zone not null
);

--name: create-channel-status-junction!
create table if not exists channel_status_map (
  constraint channel_status_id PRIMARY KEY (status_id, channel_id),
  status_id bigint references status (status_id) on update cascade,
  channel_id bigint references channel (channel_id) on update cascade
);

--name: create-person-organization-junction!
create table if not exists person_organization_map (
  constraint person_organization_id PRIMARY KEY (person_id, organization_id)
  organization_id bigint references organization (organization_id) on update cascade,
  person_id bigint references person person_id) on update cascade,
  constraint organization_person PRIMARY KEY (organization_id, person_id)
);

--name: create-organization-channel-junction!
create table if not exists organization_channel_map (
  organization_id bigint references organization (organization_id) on update cascade,
  channel_id references channel (channel_id) on update cascade,
  constraint organization_person PRIMARY KEY (organization_id, channel_id)
);

--name: create-person-table-comment!
comment on table person is 'This table describes a user in the Reactivity application.';
