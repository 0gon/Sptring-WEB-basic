Drop table cafeuser_tb PURGE;

CREATE table cafeuser_tb (
no int primary key,
email varchar2(100) ,
nickname varchar2(100),
password varchar2(50) not null,
grade int default 1 REFERENCES cafegrade_tb(no) ON DELETE CASCADE ,
regdate Date default sysdate
);

create sequence cafeuser_tb_seq
  start with 0
  increment by 1
  MINVALUE 0
  NOMAXVALUE
  NOCYCLE ;

select * from cafeuser_tb;
-- ----------------