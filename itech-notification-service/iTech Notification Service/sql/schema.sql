CREATE TABLE message_type
(
    id                  varchar(32) primary key,
    name                text not null
);

CREATE TABLE sender
(
    id                serial primary key,
    account_type      varchar(32) not null default 'TELEGRAM',
    account           text not null,
    secret            text,
    config            text,
    enabled           boolean not null default true,

    created_time      timestamp with time zone not null,
    creator_id        integer not null default 1,
    last_updated_time timestamp with time zone,
    last_updated_id   integer
);

CREATE TABLE message_type_sender
(
    sender_id integer NOT NULL references sender (id) ON DELETE CASCADE,
    message_type_id  varchar(32) NOT NULL references message_type (id) ON DELETE CASCADE,
    PRIMARY KEY (sender_id, message_type_id)
);

CREATE TABLE receiver
(
    id                serial primary key,
    account_type      varchar(32) not null default 'TELEGRAM',
    account           text not null,
    config            text,
    enabled           boolean not null default true,

    created_time      timestamp with time zone not null,
    creator_id        integer not null default 1,
    last_updated_time timestamp with time zone,
    last_updated_id   integer
);
CREATE INDEX idx_receiver_account_type on receiver (account_type);

CREATE TABLE message_type_receiver
(
    receiver_id integer NOT NULL references receiver (id) ON DELETE CASCADE,
    message_type_id  varchar(32) NOT NULL references message_type (id) ON DELETE CASCADE,
    PRIMARY KEY (receiver_id, message_type_id)
);

CREATE TABLE message
(
    id                serial primary key,
    message_type      varchar(32) NOT NULL references message_type (id),
    subject           text,
    message           text not null,
    delivered_time    timestamp with time zone,
    retry_count       integer not null default 0,

    created_time      timestamp with time zone not null,
    creator_id        integer not null default 1,
    last_updated_time timestamp with time zone,
    last_updated_id   integer
);
CREATE INDEX idx_message_delivered_time on message (delivered_time);
CREATE INDEX idx_message_retry_count on message (retry_count);


