# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ethics_form_model (
  id                        integer not null,
  state                     integer,
  constraint ck_ethics_form_model_state check (state in (0)),
  constraint pk_ethics_form_model primary key (id))
;

create table user_model (
  curtin_id                 varchar(255) not null,
  name                      varchar(255),
  encrypted_password        varchar(255),
  constraint pk_user_model primary key (curtin_id))
;

create sequence ethics_form_model_seq;

create sequence user_model_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists ethics_form_model;

drop table if exists user_model;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists ethics_form_model_seq;

drop sequence if exists user_model_seq;

