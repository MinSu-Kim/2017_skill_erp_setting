LOAD data LOCAL INFILE 'D:/worksapce-skill2017/Skill_Erp_Setting/DataFiles/Post/제주특별자치도.txt' INTO table  post   character set 'euckr'  fields TERMINATED by '|' IGNORE 1 lines (@zipcode, @sido, @d, @sigungu , @d, @eupmyun, @d,  @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @courtdong, @ri ,@admindong, @d, @zibun1, @d, @zibun2, @d, @d) set zipcode=@zipcode, sido=@sido, sigungu=@sigungu ,eupmyun=@eupmyun, doro=@doro, building1=@building1, building2=@building2, courtdong=@courtdong, ri=@ri,admindong=@admindong, zibun1=@zibun1, zibun2=@zibun2;


LOAD DATA LOCAL INFILE 'D:/worksapce-skill2017/Skill_Erp_Setting/DataFiles/title.txt' INTO TABLE title character set 'UTF8' fields TERMINATED by ',';


select password('1234'), length(password('1234')) from dual;


select password('1234'), pass, if (password('1234')=pass, "참", "거짓") from employee where empno=1003;

select count(*) from employee where dno=1;

select count(*) from employee where title=1;

select count(*) from employee where title=1;

select left(pic,8) from employee; where empno=1003;;

update employee set pic=load_file('D:\worksapce-skill2017\Skill_Erp_Application\DataFiles\img\gongu.jpg') where empno=1003;

