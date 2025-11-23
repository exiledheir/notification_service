-- DB Merchants:
-- - id
-- - name
-- - webhook
-- - tax-number
-- - created-at
-- - login
-- -password
create table merchants
(
    id        bigint generated always as identity primary key,
    name      varchar(50)  not null,
    webhook   varchar(100) not null,
    tax_number varchar(50)  not null unique,
    created_at timestamp default current_timestamp,
    login     varchar(50)  not null unique,
    password  varchar not null
);

-- DB Notifications:
-- - id
-- - status (CREATED, SENT, FAILED)
-- - created-at
-- - updated-at
-- - content
-- - merchant-id
-- - receiver

create table notifications
(
    id         bigint generated always as identity primary key,
    status     varchar(10)        not null default 'CREATED',
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp,
    content    varchar not null,
    merchant_id bigint       not null,
    receiver   varchar(50)  not null,
    constraint fk_merchant foreign key (merchant_id) references merchants (id) on delete cascade
);