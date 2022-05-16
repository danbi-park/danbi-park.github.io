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

hibernate.hbm2ddl.auto
| 옵션  | 설명 |
| --- | --- | 
| create | 기존테이블 삭제 후 다시 생성 (DROP + CREATE)  |
| create-drop | create와 같으나 종료시점에 테이블 DROP |
| update | 변경분만 반영(운영DB에는 사용하면 안됨) |
| validate | 엔티티와 테이블이 정상 매핑되었는지만 확인 |
| none | 사용하지 않음 |