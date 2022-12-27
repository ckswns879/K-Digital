USE pnusw12db;
CREATE TABLE usertbl -- 회원 테이블
( userID  	CHAR(8) NOT NULL PRIMARY KEY, -- 사용자 아이디(PK)
  name    	VARCHAR(10) NOT NULL, -- 이름
  birthYear   INT NOT NULL,  -- 출생년도
  addr	  	CHAR(2) NOT NULL, -- 지역(경기,서울,경남 식으로 2글자만입력)
  mobile1	CHAR(3), -- 휴대폰의 국번(011, 016, 017, 018, 019, 010 등)
  mobile2	CHAR(8), -- 휴대폰의 나머지 전화번호(하이픈제외)
  height    	SMALLINT,  -- 키
  mDate    	DATE  -- 회원 가입일
);
CREATE TABLE buytbl -- 회원 구매 테이블(Buy Table의 약자)
(  num 		INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 순번(PK)
   userID  	CHAR(8) NOT NULL, -- 아이디(FK)
   prodName 	CHAR(6) NOT NULL, --  물품명
   groupName 	CHAR(4)  , -- 분류
   price     	INT  NOT NULL, -- 단가
   amount    	SMALLINT  NOT NULL, -- 수량
   FOREIGN KEY (userID) REFERENCES usertbl(userID)
);

INSERT INTO usertbl VALUES('LSG', '이승기', 1987, '서울', '011', '1111111', 182, '2008-8-8');
INSERT INTO usertbl VALUES('KBS', '김범수', 1979, '경남', '011', '2222222', 173, '2012-4-4');
INSERT INTO usertbl VALUES('KKH', '김경호', 1971, '전남', '019', '3333333', 177, '2007-7-7');
INSERT INTO usertbl VALUES('JYP', '조용필', 1950, '경기', '011', '4444444', 166, '2009-4-4');
INSERT INTO usertbl VALUES('SSK', '성시경', 1979, '서울', NULL  , NULL      , 186, '2013-12-12');
INSERT INTO usertbl VALUES('LJB', '임재범', 1963, '서울', '016', '6666666', 182, '2009-9-9');
INSERT INTO usertbl VALUES('YJS', '윤종신', 1969, '경남', NULL  , NULL      , 170, '2005-5-5');
INSERT INTO usertbl VALUES('EJW', '은지원', 1972, '경북', '011', '8888888', 174, '2014-3-3');
INSERT INTO usertbl VALUES('JKW', '조관우', 1965, '경기', '018', '9999999', 172, '2010-10-10');
INSERT INTO usertbl VALUES('BBK', '바비킴', 1973, '서울', '010', '0000000', 176, '2013-5-5');
INSERT INTO buytbl VALUES(NULL, 'KBS', '운동화', NULL   , 30,   2);
INSERT INTO buytbl VALUES(NULL, 'KBS', '노트북', '전자', 1000, 1);
INSERT INTO buytbl VALUES(NULL, 'JYP', '모니터', '전자', 200,  1);
INSERT INTO buytbl VALUES(NULL, 'BBK', '모니터', '전자', 200,  5);
INSERT INTO buytbl VALUES(NULL, 'KBS', '청바지', '의류', 50,   3);
INSERT INTO buytbl VALUES(NULL, 'BBK', '메모리', '전자', 80,  10);
INSERT INTO buytbl VALUES(NULL, 'SSK', '책'    , '서적', 15,   5);
INSERT INTO buytbl VALUES(NULL, 'EJW', '책'    , '서적', 15,   2);
INSERT INTO buytbl VALUES(NULL, 'EJW', '청바지', '의류', 50,   1);
INSERT INTO buytbl VALUES(NULL, 'BBK', '운동화', NULL   , 30,   2);
INSERT INTO buytbl VALUES(NULL, 'EJW', '책'    , '서적', 15,   1);
INSERT INTO buytbl VALUES(NULL, 'BBK', '운동화', NULL   , 30,   2);

SELECT * FROM usertbl;
SELECT * FROM buytbl;


-- usertbl; 테이블에서 전체 컬럼 조회
SELECT * FROM usertbl;
-- usertbl; 테이블에서 이름과 주소 검색
select name,addr from usertbl;
-- usertbl; 테이블에서 김경호를 검색
select * from usertbl where name='김경호';
-- usertbl; 테이블에서 키가 177보다 큰 사람의 이름과 키를 검색
select name,height from usertbl where height>177;
-- 1970년 이후에 출생하고 키가 182 이상인 사람의 아이디와 이름을 검색
select userID,name from usertbl where birthYear>1970 and height>=182;
-- 1970년 이후에 출생했거나 키가 182 이상인 사람의 아이디와 이름을 검색
select userID,name from usertbl where birthYear>1970 or height>=182;
-- 키가 180~183인 사람의 이름과 키를 검색 (and 및 between으로 검색)
select name,height from usertbl where height between 180 and 183;
-- 지역(addr)이 경남, 전남, 경북인 사람의 이름과 지역을 검색하시오. (or 및 in으로 검색)
select name,addr from usertbl where addr in('경남','전남','경북');
-- 김씨인 사람의 이름과 키를 검색
select name,height from usertbl where name like '김%';
-- 김씨가 아닌 사람들의 이름과 키를 검색
select name,height from usertbl where name not like '김%';
-- 이름이 종신인 사람의 이름과 키를 검색
select name,height from usertbl where name like '%종신';
-- 김경호 키보다 큰 사람의 이름과 키를 검색. (서브쿼리)
select name,height from usertbl where height > (select height from usertbl where name='김경호');
-- 먼저 가입한 순서대로 회원의 이름과 가입일(mDate)을 검색
select name,mDate from usertbl order by mDate ASC;
-- 최근에 가입한 순서대로 회원의 이름과 가입일(mDate)을 검색
 select name,mDate from usertbl order by mDate DESC;
 -- 키가 큰 순서대로 정렬하되 키가 같을 경우 이름 순서대로 회원의 이름과 키을 검색
SELECT name, height FROM usertbl ORDER BY height DESC, name ASC;
-- 주소가 1개씩만 출력(중복X)
SELECT DISTINCT addr FROM usertbl;
-- buytbl; 테이블에서 사용자별로 이름, 수량(amount)의 합계를 검색
select userID,sum(amount) from buytbl group by userID;
-- 위의 문제에서 userID는 ‘사용자아이디’ SUM(amount)는 ‘총 구매 개수’로 출력
select userID'사용자아이디',sum(amount)as'총구매 개수'from buytbl group by userID;
-- buytbl; 테이블에서 사용자별로 이름, 총구매액(amount*price)을 검색
SELECT userID,sum(amount*price)from buytbl buytbl group by userID;
-- buytbl; 테이블에서 사용자별로 이름, 총구매액(amount*price)이 1000 이상인 사용자를 검색
SELECT userID,sum(amount*price)from buytbl buytbl group by userID having sum(amount*price)>=1000;
-- buytbl; 테이블에서 사용자별로 이름, 총구매액(amount*price)이 1000 이상인 사용자를 구매액이 적은 사람부터 출력
SELECT userID,sum(amount*price)from buytbl buytbl group by userID having sum(amount*price)>=1000 order by sum(amount*price) asc;
-- 전체 구매자가 구매한 물품수량(amount)의 평균을 검색(컬럼명은 ‘평균구매개수’로 출력)
select AVG(amount)as'평균구매개수'from buytbl;
-- 각 사용자별로 평균구매개수를 검색하여 사용자아이디, 평균구매개수 출력
select userID'사용자아이디', AVG(amount)as'평균구매개수'from buytbl group by userID;
-- usertbl; 테이블에서 가장 큰 키값과 가장 작은 키값을 출력
select  MAX(height), min(height) from usertbl;
-- usertbl; 테이블에서 휴대폰이 있는 사람의 수를 ‘휴대폰이 있는 사용자’로 출력
SELECT COUNT(mobile1) AS '휴대폰이 있는 사용자' FROM usertbl;
-- 물품을 구매한 회원의 이름, 주소, 연락처 정보를 출력
SELECT DISTINCT u.name, u.addr, u.mobile1, u.mobile2 FROM usertbl u JOIN buytbl b ON u.userid = b.userID;
-- 구매한 회원중에 BBK라는 아이디를 가진 회원의 이름, 주소, 연락처를 검색
select name, addr, mobile1, mobile2 from buytbl inner join usertbl on buytbl.userID = usertbl.userID where buytbl.userID='BBK';




