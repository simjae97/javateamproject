drop database if exists phoenix;
create database phoenix;

use phoenix;

create table grade( -- 직위
   gradeno int not null ,
    gradename varchar(10),
    
    primary key(gradeno)
);

insert into grade values(0,'전체');
insert into grade values(1,'사원');
insert into grade values(2,'대리');
insert into grade values(3,'팀장');
insert into grade values(4,'부장');
insert into grade values(5,'임원');


create table part( -- 부서
   partno int not null,
    partname varchar(30) unique,
    
    primary key(partno)
);

insert into part values(1,'인사');
insert into part values(2,'회계');
insert into part values(3,'개발');

create table employee( -- 사원
   eno int auto_increment,
    gradeno int not null default 0,
   ename varchar(30) not null,
    ephone varchar(15) not null unique,
   eemail varchar(50) not null unique,
    eid varchar(30) not null unique,
    epw varchar(30) not null,
    edate datetime default now(),
    partno int default 0,

    primary key(eno),
    foreign key(gradeno) references grade(gradeno),
    foreign key(partno) references part(partno) on update cascade on delete set null
);

insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(1,'일사원','010-0000-0000','a@co.kr','a','a',1);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(2,'일대리','010-0000-0001','b@co.kr','b','b',1);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(3,'일팀장','010-0000-0002','c@co.kr','c','c',1);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(4,'일부장','010-0000-0003','d@co.kr','d','d',1);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(5,'일임원','010-0000-0004','e@co.kr','e','e',1);

insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(1,'이사원','010-0000-0005','f@co.kr','f','f',2);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(2,'이대리','010-0000-0006','g@co.kr','g','g',2);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(3,'이팀장','010-0000-0007','h@co.kr','h','h',2);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(4,'이부장','010-0000-0008','i@co.kr','i','i',2);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(5,'이임원','010-0000-0009','j@co.kr','j','j',2);

insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(1,'삼사원','010-0000-0010','k@co.kr','k','k',3);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(2,'삼대리','010-0000-0011','l@co.kr','l','l',3);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(3,'삼팀장','010-0000-0012','m@co.kr','m','m',3);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(4,'삼부장','010-0000-0013','n@co.kr','n','n',3);
insert into employee(gradeno,ename,ephone,eemail,eid,epw,partno) values(5,'삼임원','010-0000-0014','o@co.kr','o','o',3);

/*
직급 샘플 데이터       마스터 : 임원 // 부장 팀장 대리 사원
*/

/*
부서 샘플 데이터       1회계 2인사 3개발
*/



-- insert into board(boardtitle,boardcontent,eno) values('1번 게시글','게시글 내용',1);
-- insert into board(boardtitle,boardcontent,eno) values('2번 게시글','게시글 내용',2);
-- insert into board(boardtitle,boardcontent,eno) values('3번 게시글','게시글 내용',3);
-- insert into board(boardtitle,boardcontent,eno) values('4번 게시글','게시글 내용',4);
-- insert into board(boardtitle,boardcontent,eno) values('5번 게시글','게시글 내용',5);
-- insert into board(boardtitle,boardcontent,eno) values('6번 게시글','게시글 내용',6);
-- insert into board(boardtitle,boardcontent,eno) values('7번 게시글','게시글 내용',7);
-- insert into board(boardtitle,boardcontent,eno) values('8번 게시글','게시글 내용',8);
-- insert into board(boardtitle,boardcontent,eno) values('9번 게시글','게시글 내용',9);
-- insert into board(boardtitle,boardcontent,eno) values('10번 게시글','게시글 내용',10);
-- insert into board(boardtitle,boardcontent,eno) values('11번 게시글','게시글 내용',11);
-- insert into board(boardtitle,boardcontent,eno) values('12번 게시글','게시글 내용',12);
-- insert into board(boardtitle,boardcontent,eno) values('13번 게시글','게시글 내용',13);
-- insert into board(boardtitle,boardcontent,eno) values('14번 게시글','게시글 내용',14);
-- insert into board(boardtitle,boardcontent,eno) values('15번 게시글','게시글 내용',15);

create table boardcategory(
   bcno int auto_increment,
    bcname text not null,
    constraint
    primary key(bcno)
);

create table board(
   boardno int auto_increment,
    boardtitle varchar(30) not null,
    boardcontent longtext not null,
    eno int,
    boarddate datetime default now(),
   bcno int,
    primary key(boardno),
    foreign key(eno) references employee(eno) on delete set null,
    foreign key(bcno) references boardcategory(bcno)
);
-- insert into boardpermit(boardno,gradeno,partno) values(1,1,1);
-- insert into boardpermit(boardno,gradeno,partno) values(2,2,1);
-- insert into boardpermit(boardno,gradeno,partno) values(3,3,1);
-- insert into boardpermit(boardno,gradeno,partno) values(4,4,1);
-- insert into boardpermit(boardno,gradeno,partno) values(5,5,1);

-- insert into boardpermit(boardno,gradeno,partno) values(6,1,2);
-- insert into boardpermit(boardno,gradeno,partno) values(7,2,2);
-- insert into boardpermit(boardno,gradeno,partno) values(8,3,2);
-- insert into boardpermit(boardno,gradeno,partno) values(9,4,2);
-- insert into boardpermit(boardno,gradeno,partno) values(10,5,2);

-- insert into boardpermit(boardno,gradeno,partno) values(11,1,3);
-- insert into boardpermit(boardno,gradeno,partno) values(12,2,3);
-- insert into boardpermit(boardno,gradeno,partno) values(13,3,3);
-- insert into boardpermit(boardno,gradeno,partno) values(14,4,3);
-- insert into boardpermit(boardno,gradeno,partno) values(15,5,3);

-- gradno 별 3개, partno 별 5개

create table reply(
   eno int,
    boardno int not null,
    replyno int auto_increment,
    replycontent text not null,
    replydate datetime default now(),

    primary key (replyno),
    foreign key (eno) references employee(eno) on delete set null,
    foreign key (boardno) references board(boardno) on delete cascade
);


-- insert into reply(eno,boardno,replycontent) values(2,1,"댓글내용1");
-- insert into reply(eno,boardno,replycontent) values(3,1,"댓글내용2");
-- insert into reply(eno,boardno,replycontent) values(2,2,"댓글내용3");

create table reportcategory(
   cno int not null unique,
    cname text
);

create table report(
   eno int,
   reportno int auto_increment,
   reporttitle varchar(50) not null,
   reportcontent text,
    reportdate datetime default now(),
    cno int,

    primary key(reportno),
    foreign key(eno) references employee(eno) on delete set null,
    foreign key(cno) references reportcategory(cno) on delete cascade
);


create table workreport(
   reportno int auto_increment,
   content2 text,
    foreign key(reportno) references report(reportno) on delete cascade
);

create table vacationreport(
   reportno int auto_increment,
    startdate datetime,
    enddate datetime,
    foreign key(reportno) references report(reportno) on delete cascade
);

create table purchasereport(
   reportno int auto_increment,
    itemlist text,
    totalprice int,
    pricereason text,
    foreign key(reportno) references report(reportno) on delete cascade
);

create table reportlog(
   reportno int not null,
    eno int,
    confirm bool default false,

    foreign key(reportno) references report(reportno) on delete cascade,
    foreign key(eno) references employee(eno) on delete set null
);

create table mail(
   mailno int auto_increment,
    eno int,
    mailtitle text not null,
    mailcontetnt text not null,
    maildate datetime default now(),
    constraint
    primary key(mailno),
   foreign key(eno) references employee(eno) on delete cascade
);

create table maillog(
   mailno int,
    eno int,
    mailstate int default 0,
    constraint
    foreign key(eno) references employee(eno) on delete cascade,
    foreign key(mailno) references mail(mailno) on delete cascade
);



select * from employee;
select * from board;
select * from boardcategory;
select * from reply;

select * from report;
select * from reportlog;
-- 심재훈
SELECT  report.reportno ,COUNT(*)FROM report JOIN reportlog ON report.reportno = reportlog.reportno GROUP BY report.reportno;
SELECT  report.reportno ,reportlog.confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno where report.eno = 1 GROUP BY report.reportno, reportlog.confirm;
SELECT report.*,reportlog.eno FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.eno = 1;
SELECT report.*,reportlog.confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.eno = 4;

SELECT  report.* ,reportlog.confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno;

SELECT  report.* ,reportlog.confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno where reportlog.eno = 2;



SElect report.reportno,Count(*) as count FROM report JOIN reportlog ON report.reportno = reportlog.reportno group by report.reportno;
SELECT report.reportno,Count(*) FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.confirm =true group by report.reportno, reportlog.confirm;

select * from report where reportno = 5;

select * from report;
select * from reportlog;
select * from reportlog;

SELECT report.*, r1.log_count, r2.log_count_confirm
FROM report
LEFT JOIN (SELECT report.reportno, COUNT(*) as log_count
         FROM report JOIN reportlog ON report.reportno = reportlog.reportno GROUP BY report.reportno) r1 ON report.reportno = r1.reportno
LEFT JOIN ( SELECT report.reportno, COUNT(*) AS log_count_confirm FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.confirm = true GROUP BY report.reportno, reportlog.confirm) r2
ON report.reportno = r2.reportno;




DELIMITER //

CREATE EVENT IF NOT EXISTS update_report_confirm
ON SCHEDULE EVERY 1 DAY
DO
BEGIN
  UPDATE reportlog
  SET confirm = false
  WHERE reportno IN (
    SELECT report.reportno
    FROM report
    LEFT JOIN (
      SELECT report.reportno, COUNT(*) FROM report JOIN reportlog ON report.reportno = reportlog.reportno GROUP BY report.reportno
    ) r1 ON report.reportno = r1.reportno
    LEFT JOIN (
      SELECT report.reportno, COUNT(*) FROM report JOIN reportlog ON report.reportno = reportlog.reportno WHERE reportlog.confirm = true GROUP BY report.reportno, reportlog.confirm
    ) r2 ON report.reportno = r2.reportno
    WHERE report.reportdate < NOW() - INTERVAL 7 DAY AND COALESCE(r1.count, 0) != COALESCE(r2.count, 0)
  );
END//

DELIMITER ;

show events;