create table merchants
(
    id        bigint generated always as identity primary key,
    name      varchar(50)  not null,
    email      varchar(50) not null,
    webhook   varchar(100) not null,
    tax_number varchar(50)  not null unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    login     varchar(50)  not null unique,
    password  varchar not null
);

create table notifications
(
    id         bigint generated always as identity primary key,
    status     varchar(10)        not null default 'CREATED',
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    text          text        not null,
    merchant_id bigint       not null,
    type          varchar(10) not null,
    receiver_info varchar(50) not null,
    constraint fk_merchant foreign key (merchant_id) references merchants (id) on delete cascade
);

create table price
(
    id         bigint generated always as identity primary key,
    created_at timestamp not null default current_timestamp,
    is_active  boolean            default true,
    price      decimal   not null
);