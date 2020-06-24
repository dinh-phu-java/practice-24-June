create database demo_transaction;
use demo_transaction;

create table users(
                      id int primary key ,
                      name nvarchar(100),
                      username nvarchar(100),
                      password nvarchar(100),
                      amount double
);

select * from    users;

insert into users value(1,'Dinh Phu','dinhphu','123',5000);
insert into users value(2,'Hung 3D','hung3d','123',2000);
insert into users value(3,'Vu 4D','vu4d','123',6000);
update users set amount=(6000-300) where id=1;
select * from users;

alter table users
    change column amount amount double check (amount>0);

ALTER TABLE users
    ADD CONSTRAINT CHK_Amount CHECK (amount>=0 );

# update users set amount=-2000 where id=1;
