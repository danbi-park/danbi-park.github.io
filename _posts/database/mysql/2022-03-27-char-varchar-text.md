---
title:  "CHAR, VARCHAR, text의 차이"
excerpt: ""
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - dataBase
tags:
  - dataBase
  - MySQL
last_modified_at: 2022-03-27
--- 

MySQL 4.1 이후 버전부터는 CHAR(n), VARCHAR(n)에서 n은 바이트가 아니라 글자 수를 의미한다.  
ex) CHAR(30)은 30 글자까지 보관할 수 있다.

<br>

| 자료형 type  | 길이 | byte | description | index | 필요한 영역 | 특징 |
| --- | --- | --- | --- | --- | --- | --- |
| CHAR(n) | 0~255 | n <= 255 | 고정형 | O | 지정한 데이터 크기 | - |
| VARCHAR(n) | 0~255 <br> (MySQL 5.0.3이후부터 65,535까지 가능) | n <= 65,535 | 가변형 | O | 실제 데이터 크기 | memory에 저장해서 빠름 |
| TEXT(길이지정 못함) | 0~65535 | - | 가변형 | X | - | disk에 저장해서 느림 |

<br>

## CHAR

- **CHAR**(8)로 선언시 글자를 한 개를 넣던 두 개를 넣던 **고정 길이의 공간을 차지**한다.
    - 예) CHAR(10)에 ‘abcd’를 저장하면, ‘abcd’와 함께 빈 6자리를 채우기 위해 6개의 space(padding)가 뒤에 채워진다.
- 해당 row를 가져올 땐 채워진 스페이스는 빼고 가져온다.
    - PAD_CHAR_TO_FULL_LENGTH 설정이 켜져 있으면 스페이스도 가져옴
- index가 가능하다.
- CAH는 경우에 따라 데이터가 낭비될 수 있지만 추후에 연산이 필요 없기 때문에 검색속도가 VARCHAR에 비해 빠르다.
    - 글자수가 고정되는 ex) 주민번호, 전화번호 등등.. 곳에 사용하기 좋음

<br><br>

## VARCHAR

- 데이터를 삽입하면 데이터값외에 삽입된 문자열의 길이를 저장하는데 255글자 이하에는 1바이트, 그 이상은 2바이트의 추가 공간을 필요로 한다.
- 즉 실질적인 데이터와 **길이 정보**도 같이 저장된다.
    - 예) VARCHAR(10)에 ‘test’라는 4자짜리 문자열을 삽입하면4byte + **1byte(길이를 저장하기 위한 메모리)** = 5byte가 소모된다.
- **저장된 문자열 만큼**의 공간을 차지한다.
- **VARCHAR**(8)로 선언시 글자를 한개를 넣으면 1바이트, 2개를 넣으면 2바이트의 공간을 유동적으로 차지한다.
- CHAR와 다르게 prefix byte를 사용한다. (1~2 byte 남짓을 추가적으로 사용)
- 최대 65535 byte 를 저장. 어떤 Character set을 사용하느냐에 따라 max 길이가 달라짐 (약16000자(길이), mysql 8.0.25 utfmbr Character set사용 시)
- 저정할 수 있는 길이는 0부터 255까지이며 MySQL 5.0.3 이후에는 0부터 65,535까지 지정할 수 있다.
- 기존 데이터 길이보다 더 큰 데이터로 **업데이트**할 경우 **새로운 저장 영역에 새로 할당**해야하므로 데이터 파편화가 발생한다.

<br><br>

## TEXT

- VARCHAR 보다 더 큰 값을 가질 수 있다.
- index 불가.
- default 값을 가질 수 없다. 지정값 x
- 65535자를 (길이) 저장할 수 있다. (mysql 8.0.25에서 utfmb4 Character set 사용)
- stack overflow 에서 65535 byte 를 저장한다는 글을 많이 볼 수 있었는데, 사실은 byte 가 아니라 글자 수가 65535자 이다.
- text는 disk에 저장되기 때문에 자주 불리는 query에서는 performance 차이가 크다고 하니 사용에 주의

<br><br>

---


<br>

## CHAR와 VARCHAR를 동시에 쓰면 안되는 이유

- 테이블 ROW 중에 CHAR, VARCHAR 타입이 **섞여 있으면 데이터 파편화**는 발생할 수 밖에 없다.
- 그러나 MySQL에서는 다음의 쿼리를 적용하면 파편화를 막을 수 있다.

```sql
ALTER TABLE tblname ROW_FORMAT = FIXED;
```

- VARCHAR 타입을 CHAR 타입처럼 동작하도록 강제로 지정하는 것이다.
- CHAR처럼 동작하기 때문에 데이터 용량은 증가하지만 VARCHAR로 인한 파편화로 성능 저하는 막을 수 있다.
- 특이한 점으로는 4자 이하의 VARCHAR는 CHAR로 자동 변환된다.
- 만약 테이블안에 VARCHAR나 text, blob 같은 가변길이 데이터가 하나라도 있을 경우3자 이상의 CHAR컬럼이 자동으로 VARCHAR로 바뀌게 된다.
- TABLE을 만든 후 실제로 무슨 형으로 변환되었는지 DESC table_name으로 확인 할 수 있다.
- 이렇게 하는 이유는 물론 **성능과 속도** 때문이다.

<br>

위의 글을 읽고 이때까지 VARCHAR만 써왔어서 혹시 성능이 많이 차이가 나는가 하고 찾아보니 정확한 답은 아니지만 실무자들이 많은 okky 사이트에서 아래와 같은 답변이 있었다.

<br>

## VARCHAR를 주로 사용하는 이유

- CHAR와의 성능차이가 크게 느껴지지 않을 뿐더러 에러날 확률 및 국가별 언어 데이터 크기 등을 고려해 확장성 있는 VARCHAR가 더 많이 쓰인다.
- 개발 당시엔 고정이라고 확정했더라도 나중에 데이터 타입 늘어나고 하면 다 바꿔줘야하고 등등.. 복합 이유가 있음 
- 문자 수를 체크 하는 로직이 VARCHAR에 있어 성능을 우려하지만 실제 건수가 아무리 많아도 사람이 느낄 수 없는 속도라서 대부분 업체에서도 VARCHAR를 권장한다함


<br><br>

### 출처
---

[개발하는 꼬바리](https://byul91oh.tistory.com/212)  
[코딩맛집](https://coding-restaurant.tistory.com/156)  
[Okky 질문](https://okky.kr/article/217655)