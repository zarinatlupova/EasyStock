create table if not exists flyway_schema_history
(
    installed_rank integer                 not null
    constraint flyway_schema_history_pk
    primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
    );

alter table flyway_schema_history
    owner to postgres;

create index if not exists flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table if not exists customer
(
    id      integer generated always as identity (minvalue 2)
    primary key,
    name    varchar(255) not null,
    surname varchar(255) not null,
    email   varchar(255),
    address varchar(255)
    );

alter table customer
    owner to postgres;

create table if not exists item
(
    id         integer generated by default as identity
    primary key,
    name       varchar(255),
    supplier   varchar(255),
    size       double precision,
    price      double precision,
    asile      integer,
    rack       integer,
    shelf      integer,
    fk_item_id integer
    );

alter table item
    owner to postgres;

create table if not exists zone
(
    id         integer not null
    primary key,
    wave_id    integer,
    name       varchar(255),
    type       varchar(255),
    item_id    integer
    constraint fk_item_id
    references item,
    item_count integer
    );

alter table zone
    owner to postgres;

create table if not exists employee
(
    id         integer generated always as identity
    primary key,
    username   varchar(255),
    role       varchar(255),
    created_at timestamp,
    zone_id    integer
    constraint fk_zone_id
    references zone
    );

alter table employee
    owner to postgres;


create table if not exists wave
(
    id            integer generated always as identity
    primary key,
    order_list_id integer,
    wave_priority varchar(255),
    wave_status   varchar(255),
    fk_wave_id    integer
    constraint fksnmmijgfhryv6rgh1kt7xsqq1
    references zone
    );

alter table wave
    owner to postgres;



create table if not exists order_list
(
    id               integer generated by default as identity
    primary key,
    customer_id      integer
    constraint fk_customer
    references customer,
    order_status     varchar(255),
    total_price      double precision,
    order_date       timestamp,
    delivery_date    timestamp,
    order_list_id    integer
    constraint fk320o3s2gc582ja971rfby4m44
    references wave,
    fk_order_list_id integer
    constraint fk3u4atiir18l8vvmfuwn3r64l7
    references wave
    );

alter table order_list
    owner to postgres;


create table if not exists order_list_item
(
    order_list_id integer not null
    references order_list,
    item_id       integer not null
    references item,
    constraint id
    primary key (order_list_id, item_id)
    );

alter table order_list_item
    owner to postgres;

create table if not exists role
(
    id   integer generated by default as identity
    primary key,
    name varchar(255) not null
    );

alter table role
    owner to postgres;

create table if not exists role_employee
(
    role_id     integer not null
    references role,
    employee_id integer not null
    references employee,
    constraint role_employee_id
    primary key (role_id, employee_id)
    );

alter table role_employee
    owner to postgres;