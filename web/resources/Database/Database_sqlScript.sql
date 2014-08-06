Create Database webrtcDB;

use webrtcDB;

create table user(

userid int AUTO_INCREMENT,
fname varchar(20),
lname varchar(20),
username varchar(20),
password varchar(20),
securityq varchar(50),
securitya varchar(50),
email varchar(30),
phone numeric,
street varchar(15),
city varchar(15),
state varchar(15),
zipcode int,
country varchar(15),
is_a char(1),
constraint user_userid_pk primary key (userid)
);

create table teacher(
userid int,
department varchar(20),
constraint teacher_userid_pk primary key (userid),
constraint teacher_userid_fk foreign key (userid)
	references user (userid) ON DELETE CASCADE
);

create table student(
userid int,
major varchar(20),
constraint student_userid_pk primary key (userid),
constraint student_userid_fk foreign key (userid)
	references user (userid) ON DELETE CASCADE
);


create table onlineclass(
onlineclassid int AUTO_INCREMENT,
title varchar(10),
description longtext,
roomid int,

constraint onlineclass_onlineclassid_pk primary key (onlineclassid),
constraint onlineclass_roomid_uk unique (roomid)
);


create table scheduleclass(
scheduleclassid int AUTO_INCREMENT,
teacherid int,
onlineclassid int,
startdate date,
enddate date,
starttime varchar(10),
endtime varchar(10),
constraint scheduleclass_scheduleclassid_pk primary key (scheduleclassid),
constraint scheduleclass_teacherid_fk foreign key (teacherid)
	references teacher(userid) ON DELETE CASCADE,
constraint scheduleclass_onlineclassid_fk foreign key (onlineclassid)
	references onlineclass (onlineclassid) ON DELETE CASCADE,
constraint scheduleclass_startdate_uk unique (startdate),
constraint scheduleclass_enddate_uk unique (enddate)
);

create table roomparticipant(
onlineclassid int ,
studentid int,
constraint roomparticipant_onlineclassid_studentid_pk primary key (onlineclassid, studentid),
constraint roomparticipant_onlineclassid_fk foreign key (onlineclassid)
	references onlineclass(onlineclassid) ON DELETE CASCADE,
constraint roomparticipant_studentid_fk foreign key (studentid)
	references student (userid) ON DELETE CASCADE,
constraint roomparticipant_onlineclassid_studentid_uk unique(onlineclassid,studentid)

);

create table discussion(
discussionid int AUTO_INCREMENT,
title varchar (20),
description longtext,
startdate date,
enddate date,
starttime varchar(10),
endtime varchar(10),
createby int,
onlineclassid int,

constraint discussion_discussionid_pk primary key (discussionid),
constraint discussion_createby_fk foreign key (createby)
	references teacher (userid) ON DELETE CASCADE,
constraint discussion_onlineclassid_fk foreign key (onlineclassid)
references onlineclass (onlineclassid) ON DELETE CASCADE,
constraint discussion_title_uk unique (title),
constraint discussion_description_uk unique (description)

);


create table reaction(
reactionid int AUTO_INCREMENT,
discussionid int,
postby int,
reaction longtext,

constraint reaction_reactionid_pk primary key (reactionid),
constraint reaction_discussionid_fk foreign key (discussionid)
references discussion (discussionid),
constraint reaction_postby_fk foreign key (postby)
references user (userid) ON DELETE CASCADE
);

create table ressource(
ressourceid int AUTO_INCREMENT,
teacherid int,
datecreated date  NOT NULL DEFAULT CURDATE(),
onlineclassid int,
has_prezi char(1),
has_reveal char(1),
has_studytool char(1),
constraint ressource_ressourceid_pk primary key (ressourceid),
constraint ressource_teacherid_fk foreign key (teacherid)
	references teacher (userid) ON DELETE CASCADE,
constraint ressource_onlinecalssid_fk foreign key (onlineclassid)
	references onlineclass (onlineclassid) ON DELETE CASCADE
);


create table prezicontent (
ressourceid int,
preziid varchar(20),

constraint prezicontent_ressourceid_pk primary key (ressourceid),
constraint prezicontent_ressourceid_fk foreign key (ressourceid)
	references ressource (ressourceid) ON DELETE CASCADE,
Constraint prezicontent_preziid_uk unique(preziid)

);

create table studytool(
ressourceid int,
articlelink varchar(20),
videoslink varchar(20),

constraint studytool_ressourceid_pk primary key (ressourceid),
constraint studytool_ressourceid_fk foreign key (ressourceid)
	references ressource (ressourceid) ON DELETE CASCADE
);

create table revealcontent(
revealid int AUTO_INCREMENT,
ressourceid int,
constraint revealcontent_revealid_pk primary key (revealid),
constraint revealcontent_ressourceid_fk foreign key (ressourceid)
	references ressource (ressourceid) ON DELETE CASCADE
);

create table content(
contentid int AUTO_INCREMENT,
revealid int,
page int,
contenttext Longtext,
constraint content_contentid_revealid_pk primary key (contentid, revealId),
Constraint content_revealid_fk foreign key (revealid)
references revealcontent(revealid) ON DELETE CASCADE
);

create table file(
filesid int AUTO_INCREMENT,
filename varchar(20),
size int,
postedby int,
location varchar(50),

constraint files_filesid_pk primary key (filesid),
constraint files_postedby_fk foreign key (postedby)
	references teacher (userid) ON DELETE CASCADE,
Constraint file_filename_uk Unique (filename)

);

create table chat(
chatId int AUTO_INCREMENT,
onlineclassid int,
userid int,
chatdate date,

constraint chat_chatid_pk primary key (chatid),
constraint chat_onlineclassid_fk foreign key (onlineclassid)
	references onlineclass(onlineclassid) ON DELETE CASCADE,
constraint chat_userid_fk foreign key (userid)
	references user (userid) ON DELETE CASCADE,
Constraint chat_onlinceClassId_uk unique (onlineclassid)


);

create Table chatreaction(

chatid int,
userid int,
chatmessage longtext,
isPrivate TINYINT(1),
reactionDate date,
reactiontime int,

constraint chatreaction_chatid_userId_pk primary key (chatid, userid),
constraint chatreaction_chatid_fk foreign key (chatid)
references chat (chatid) ON DELETE CASCADE,
constraint chatreaction_userid_fk foreign key (userid)
references user (userid) ON DELETE CASCADE

);







CREATE USER 'webrtc'@'localhost' IDENTIFIED BY 'webrtc';

GRANT ALL PRIVILEGES ON * . * TO 'kawibi'@'localhost';


CREATE USER 'webrtc'@'%' IDENTIFIED BY 'webrtc';

GRANT ALL PRIVILEGES ON * . * TO 'webrtc'@'%';

FLUSH PRIVILEGES;