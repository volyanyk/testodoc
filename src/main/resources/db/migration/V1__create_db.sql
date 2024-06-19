create schema if not exists doc;

    create table doc.test_table(
    id uuid,
    data jsonb,
    create_date timestamp default current_timestamp not null,
    update_date timestamp default current_timestamp not null,

constraint pk_test_table primary key(id));