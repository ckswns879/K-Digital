  - Service 클래스에 log 객체 생성하여 명령문 DB에 저장하기
    LogDAO log = new LogDAO();
  - DB 저장할 정보
    ㅇ seq : int / Auto Increase / Primary Key
    ㅇ method : varchar(10)
    ㅇ query : varchar(255)
    ㅇ exedate : Date