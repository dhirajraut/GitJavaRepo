/* DB Creation Scripts. */
Create database SQLDB
use sqldb

/* Table Creation Scripts. */
create table USER (userId varchar(20) not null primary key,
	password varchar(20), firstName varchar(20), lastName varchar(20), joinDate DateTime, 
	noOfPosts integer)
create table DISCUSSIONTHREAD (userId varchar(20) not null primary key,
	password varchar(20), firstName varchar(20), lastName varchar(20), joinDate DateTime, 
	noOfPosts integer)
create table MESSAGE (userId varchar(20) not null, postDate DateTime, messageText varchar(200),
	foreign key (userId) references USER(userId))

/* Insert Scripts. */
insert into USER (userId, password, firstName, lastName, joinDate, noOfPosts) 
	values ('dhiraj_raut', 'pass', 'Dhiraj', 'Raut', '2009/11/04', 0)
insert into MESSAGE (userId, postDate, messageText) values ('dhiraj_raut', '2009/11/04', 'text')

/* Table Deletion Scripts. */
drop table USER
drop table DISCUSSION THREAD
drop table MESSAGE

/* Select Scripts. */
select * from USER



