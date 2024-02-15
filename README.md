# 개인 블로그 프로젝트

---

## 구현기능
- ajax 비동기 구현
  - 회원가입, 로그인, 회원정보 수정/탈퇴 
  - 게시글 등록/수정/삭제 
  - 게시글 이미지 등록 (로컬 구현 - WebMvcConfig, FileRegist 경로 확인 필요)
- 조회수 기능(쿠키를 통한 중복 조회수 방지)
- RSA, Hash
  - 사용자 정보 전송 및 DB 저장시 암호화

## 개발 환경
- OS : Windows
- IDE : Intelli J
- DB : MySQL, MyBatis
- Language : Java, Javascript
- Framework : SpringBoot
- template : JSP
- Dependency : Maven, Lombok, JSTL

## DDL
```angular2html
create table user(
    id       int auto_increment                  primary key,
    name     varchar(20)                         not null,
    email    varchar(100)                        not null,
    password varchar(64)                         not null,
    username varchar(100)                        not null,
    regDate  timestamp default CURRENT_TIMESTAMP null,
    salt     varchar(24)                         not null,
    constraint user_unique
        unique (email, username)
);



```
```angular2html
create table board(
    id         int auto_increment                  primary key,
    title      varchar(100)                        not null,
    content    varchar(100)                        not null,
    count      int       default 0                 null,
    regdate    timestamp default CURRENT_TIMESTAMP null,
    category   varchar(100)                        null,
    fileName   varchar(100)                        null,
    writer     varchar(100)                        null,
    filePath   varchar(100)                        null,
    userId     int                                 not null,
    updateDate timestamp                           null,
    constraint board_user_FK
        foreign key (userId) references user (id)
            on delete cascade
);



```
```angular2html
create table reply(
    id      int auto_increment                  primary key,
    userId  int                                 not null,
    content varchar(100)                        not null,
    regDate timestamp default CURRENT_TIMESTAMP null,
    boardId int                                 not null,
    constraint reply_board_FK
        foreign key (boardId) references mybatis_db.board (id)
            on delete cascade,
    constraint reply_user_FK
        foreign key (userId) references mybatis_db.user (id)
            on delete cascade
);


```

### application.properties
```angular2html
# spring
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
#spring.mvc.static-path-pattern=/resources/**

#server.servlet.context-path= /
server.servlet.encoding.charset=utf-8
server.servlet.encoding.enabled=true
server.servlet.encoding.force=true

# mysql
spring.datasource.url=jdbc:mysql://localhost:3306/DB이름?useSSL=false&serverTimezone=Asia/Seoul
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=
spring.datasource.password=

server.servlet.jsp.init-parameters.development=true

# mybatis
mybatis.type-aliases-package=com.example.seo.blog.model
mybatis.mapper-locations=/mapper/**/*.xml
mybatis.configuration.map-underscore-to-camel-case=true
logging.level.hello.board.repository.mybatis=trace
```

