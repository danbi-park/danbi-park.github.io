---
title:  "데이터 베이스 스키마 자동 생성"
excerpt: ""
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - jpa
tags:
  - jpa
  - basic
last_modified_at: 2022-05-16
---

## 데이터베이스 스키마 자동 생성
JPA에서는 애플리케이션 실행 시점에 DB 테이블을 자동으로 생성하는 기능을 지원한다. 
- 주로 로컬 피씨에서 개발할 때 사용
- 테이블 중심 -> 객체 중심
- **데이터베이스 방언**을 활용해서 데이터베이스에 맞는 적절한 DDL 생성
    - Oracle은 VARCHAR2
    - MySQL은 VARCHAR 와 같이
- 이렇게 생성된 DDL은 <span style="font-weight: bold; color:red">개발 장비에서만 사용한다.</span>
- 생성된 DDL은 운영서버에서는 사용하지 않거나, 적절히 다듬은 후 사용하면 된다. 

## 스키마 자동생성 실행할 때 체크

### hibernate.hbm2ddl.auto 옵션
persistence.xml에 설정되어 있는 properties중에 hibernate ddl 관련 property의 옵션별로 어떻게 테이블을 생성할 건지 정할 수 있다.
```xml
<!-- 애플리케이션 실행 시점에 데이터베이스 테이블 자동 생성 -->
<property name="hibernate.hbm2ddl.auto" value="create"/>
```
| 옵션  | 설명 |
| --- | --- | 
| create | 기존테이블 삭제 후 다시 생성 (DROP + CREATE)  |
| create-drop | create와 같으나 종료시점에 테이블을 삭제함 DROP |
| update | 변경된 부분만 반영(운영DB에는 사용하면 안됨) <br> - entity에 다가 컬럼 추가하면 alter table 해줌 <br> - 삭제는 안됨 !! |
| validate | 엔티티와 테이블이 정상 매핑되었는지만 확인 <br> - ex) 새로운 컬럼 추가 후 실행하면 컬럼이 없다고 표시함  |
| none | 사용하지 않음 (관례상 적는다고 한다. ) |


### 데이터 베이스 방언 별로 달라지는 것 확인
```xml
<property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
```
dialect -> 방언을 뜻한다. 
뒤에 H2Dialect를 Oracle12Dialect로 바꾼다면 Oracle은 가변문자의 기본이 VARCHAR2이기 때문에  VARCHAR2로 생성이 된다.  
방언에 따라서 적절한 데이터 베이스에 맞게 실행이된다.

## ❌ 주의
- 운영 장비에는 절대 create, create-drop, update를 사용하면 안된다.
- 개발 초기 단계는 create 또는 update해서 로컬에서 쓰면 된다. 
- 어느 정도 개발이 진행되고 테스트 서버에서는 update또는 validate를 사용한다.
- 스테이징과 운영 서버는 validate또는 none으로 설정해놓는다.

