create table public.goods (
  goods_id serial not null
  , name character varying(100) not null
  , deleted_flag boolean default false
  , created_by character varying(50) not null
  , created_at timestamp(6) with time zone default now()
  , primary key (goods_id)
);

create table public.user_tbl (
  id serial not null
  , name character varying(100) not null
  , primary key (id)
);

insert into public.goods(name,deleted_flag,created_by,created_at) values
    ('あくすた',False,'sys',TIMESTAMP '2025-10-28 22:59:15.554')
  , ('まつーぴー人形',False,'sys',TIMESTAMP '2025-10-28 23:47:25.993');

insert into public.user_tbl(name) values
    ('まつうぽり')
  , ('もたもたのもったん')
  , ('たも')
  , ('てれ')
  , ('まつーぴー')
  , ('まつーぴーず');


CREATE DATABASE kongdb;
GRANT ALL PRIVILEGES ON DATABASE kongdb TO postgres;