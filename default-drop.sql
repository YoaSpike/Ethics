SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists ethics_form_model;

drop table if exists ethics_triage_model;

drop table if exists user_model;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists ethics_form_model_seq;

drop sequence if exists ethics_triage_model_seq;

drop sequence if exists user_model_seq;

