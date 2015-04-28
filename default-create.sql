create table ethics_form_model (
  id                        integer not null,
  owner_id                  integer,
  state                     integer,
  constraint ck_ethics_form_model_state check (state in (0)),
  constraint pk_ethics_form_model primary key (id))
;

create table ethics_triage_model (
  id                        integer not null,
  form_id                   integer not null,
  interventions_and_therapies boolean,
  human_genetics            boolean,
  pregnant_women_or_fetuses boolean,
  medically_dependant       boolean,
  cognitively_impaired      boolean,
  aboriginal_or_torres_strait_islander boolean,
  illegal_activities        boolean,
  justification             varchar(255),
  constraint pk_ethics_triage_model primary key (id))
;

create table user_model (
  id                        integer not null,
  curtin_id                 varchar(255),
  name                      varchar(255),
  email                     varchar(255),
  hashed_password           varchar(255),
  constraint pk_user_model primary key (id))
;

create sequence ethics_form_model_seq;

create sequence ethics_triage_model_seq;

create sequence user_model_seq;

alter table ethics_form_model add constraint fk_ethics_form_model_owner_1 foreign key (owner_id) references user_model (id) on delete restrict on update restrict;
create index ix_ethics_form_model_owner_1 on ethics_form_model (owner_id);
alter table ethics_triage_model add constraint fk_ethics_triage_model_form_2 foreign key (form_id) references ethics_form_model (id) on delete restrict on update restrict;
create index ix_ethics_triage_model_form_2 on ethics_triage_model (form_id);


