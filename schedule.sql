create table schedule
(
    id bigint auto_increment primary key,
    task text not null,
    author_name varchar(100) not null,
    password varchar(100) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp

)