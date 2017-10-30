
create table HR (HR_ID int(5) primary key auto_increment,HR_NAME varchar(50) not null,department varchar(20),contact varchar(15),password varchar(50) not null)

create table POST (POST_ID int(5) primary key auto_increment,POST_NAME varchar(30) NOT NULL,NO_OF_POSTS int(11) not null,EXPERIENCE double(4,2) not null,HR_ID int(5) not null,DATE_OF_POST date not null,STATUS boolean not null,foreign key(HR_ID) REFERENCES HR(HR_ID))

create table SKILLSET (SKILL_ID int(5) primary key auto_increment,SKILL_NAME varchar(20) not null);

create table APPLICANT (APPLICANT_ID int(5) primary key auto_increment,NAME varchar(50) not null,EXPERIENCE double(4,2) not null,HIGHEST_QUAL varchar(20),EMAIL varchar(30) not null,CONTACT varchar(15) not null);

create table INTERVIEW (APPLICANT_ID int(5) not null auto_increment,POST_ID int(5) not null,DATE_OF_INTERVIEW date not null,DATE_OF_JOINING date not null,LOCATION varchar(20),APPLICANT_RESULT boolean not null,JOINING_STATUS boolean not null,primary key(APPLICANT_ID,POST_ID),foreign key(POST_ID,APPLICANT_ID) references APPLICANT_APPLY_POST(POST_ID,APPLICANT_ID),foreign key(POST_ID) references POST(POST_ID));

create table APPLICANT_SKILLSET(APPLICANT_ID int(5) not null,SKILL_ID int(5) not null,primary key(APPLICANT_ID,SKILL_ID),foreign key(APPLICANT_ID) references APPLICANT(APPLICANT_ID),foreign key(SKILL_ID) references SKILLSET(SKILL_ID));

create table APPLICANT_APPLY_POST (POST_ID int(5) not null,APPLICANT_ID int(5) not null,APPLY_DATE date not null,primary key(POST_ID,APPLICANT_ID),foreign key(POST_ID) references POST(POST_ID),foreign key(APPLICANT_ID) references APPLICANT(APPLICANT_ID));

create table POST_SKILLSET (POST_ID int(5) not null,SKILL_ID int(5) not null,primary key(POST_ID,SKILL_ID),foreign key(POST_ID) references POST(POST_ID),foreign key(SKILL_ID) references SKILLSET(SKILL_ID));