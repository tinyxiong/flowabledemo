create table test_user(
  id number primary key not null,
  name varchar2(32) default null,
  password varchar2(16) default '000000',
  create_time timestamp default sysdate,
  modify_time timestamp default sysdate
);
comment on column test_user.name is '用户名';
comment on column test_user.password is '密码';