Drop table grade_tb purge;

CREATE table grade_tb (
no int primary key,
name varchar2(100)
);

create sequence grade_tb_seq
  start with 1
  increment by 1
  MINVALUE 0
  NOMAXVALUE
  NOCYCLE ;
COMMIT ;
select * from grade_tb;