---
title:  "MySQL(MariaDB)과 Oracle 쿼리 문법 비교"
excerpt: "조금씩 다른 DataBase 문법들 정리"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - DataBase
tags:
  - DataBase
  - Oracle
  - MySQL
  - MariaDB
last_modified_at: 2022-02-09T08:06:00-05:00
--- 

<style media="screen">
    table{
      border-collapse: collapse;
      font-family: '' ;
    }
    tr,td,th{
      border:1px solid gray;
    }
  </style>
    <table>
      <tbody>
        <tr>
          <th>기능</th>
          <th>MySQL(MariaDB)</th>
          <th>Oracle</th>
        </tr>
        <tr>
          <td>null값일 경우 값 처리</td>
          <td>IFNULL('컬럼 값','대체 값')</td>
          <td> NVL('컬럼 값', '대체 값')</td>
        </tr>
        <tr>
          <td>문자열 합치기</td>
          <td> CONCAT('합칠 값1', '합칠 값2', '합칠 값3' )</td>
          <td>CONCAT('합칠 값1', '합칠 값2')<br>
- 오라클의 경우 값 2개까지만 병합가능
 또는<br>
 '합칠 값1'||'합칠 값2'||'합칠 값3'</td>
        </tr>
        <tr>
          <td> 데이터 1개만 보기</td>
          <td>LIMIT 1</td>
          <td>WHERE ROWNUM = 1 (WHERE 조건 안에서)</td>
        </tr>
        <tr>
          <td>시스템 현재 시간</td>
          <td>NOW()</td>
          <td>SYSDATE</td>
        </tr>
        <tr>
          <td>alias 사용법</td>
          <td>as 'alias명' / alias명 / as alias명</td>
          <td>as alias명 또는 alias 명(자동 대문자 전환)
             대소문자 구분한 값을 보고 싶을 경우,
             as "alias 명" 또는 "alias 명"</td>
        </tr>
        <tr>
          <td> 날짜형식 변환</td>
          <td> DATE_FORMAT(NOW(), '%Y%m%d')</td>
          <td> TO_DATE(SYSDATE, 'YYYYMMDD')</td>
        </tr>
        <tr>
          <td> 날짜 표기</td>
          <td> '%Y-%m-%d %H:%s'(년도-월-날 시간:분)</td>
          <td> 'YYYY-MM-DD HH24:MI'</td>
        </tr>
        <tr>
          <td> IF문 활용</td>
          <td> IF(조건식 demoTable.empSeq >= 1234,
 참이면 반환할 값, 거짓이면 반환할 값)</td>
          <td>DECODE(조건식, 일치해야하는 조건값,
 참이면 반환할 값, 거짓이면 반환할 값)<br> DECODE는 equal(즉, 등호)만 지원할 수 있다.</td>
        </tr>  
      </tbody>
    </table>