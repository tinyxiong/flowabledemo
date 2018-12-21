create table test_user(
  id int auto_increment primary key not null,
  name varchar(32) default null,
  password varchar(16) default '000000',
  create_time timestamp default CURRENT_TIMESTAMP ,
  modify_time timestamp default CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP
);