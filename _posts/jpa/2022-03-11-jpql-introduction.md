---
title:  "JPQL 소개"
excerpt: ""
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - jpa
tags:
  - jpa
  - basic
last_modified_at: 2022-03-11
---

### **JPQL 소개**

---

- 가장 단순한 조회 방법
- EntityManager.find()
- 객체 그래프 탐색(`a.getB().getC()`)
- 나이가 18살 이상인 회원을 모두 검색하고 싶다면?

수많은 테이블을 어떻게 조인하고 정렬해서 검색할까? → JPA에선 **JPQL 사용!** 

## JPQL 실습

---

### 전체 회원 검색

![image](https://user-images.githubusercontent.com/86641773/157885715-aedeed80-3805-4d3a-b714-5018806e9d1a.png)


ctrl + p 하면 파라미터의 속성을 알 수 있음 createQuery로 먼저 검색을 하는데 (“쿼리문”, 가져올 클래스) 인 듯.

```java
List<Member> result = em.createQuery("select m from Member as m", Member.class)
                            .getResultList();

for (Member member:result){
                System.out.println("NAME = " + member.getName());
            }
```

JPA 입장에서는 코드를 생성할 때 테이블을 대상으로 하지 않고 **Member 객체를 대상으로 쿼리를 생성**한다.  

멤버 객체를 다 가져온다. select m 에서 m이 테이블이 아니고 객체임  

```sql
Hibernate: 
    /* select
        m 
    from
        Member as m ->위에서 만든 jPQL쿼리*/
/* 실제 SQL ↓ */ 
			select
            member0_.id as id1_0_,
            member0_.name as name2_0_  //필드 나열
        from
            Member member0_
NAME = HelloJPA
NAME = HelloB
```
  
엄청난 메리트 중 하나는 페이징이다!   

```java
List<Member> result = em.createQuery("select m from Member as m", Member.class)
                            .setFirstResult(1)
                            .setMaxResults(2)
                            .getResultList();
            for (Member member:result){
                System.out.println("NAME = " + member.getName());
            }
```
  
결과:  

```sql
Hibernate: 
    /* select
        m 
    from
        Member as m */ select
            member0_.id as id1_0_,
            member0_.name as name2_0_ 
        from
            Member member0_ limit ? offset ?
NAME = HelloB
```
  
limit과 offset이 자동으로 반영이 된다.

만약 Hibernate의 dialect를 oracle로 바꾼다면 limit은 rownum으로 검색된다.

## JQPL 정리
---

> 💡 JPA를 사용하면 **엔티티 객체를 중심**으로 개발한다.

문제는 **검색 쿼리**인데,  

- 모든 DB데이터를 객체로 변환해서 검색하는 것은 불가능 하다.
- 결국 애플리케이션이 **필요한 데이터만 DB에서 불러오려면 검색 조건이 포함된 SQL이 필요하다.**
- 그래서 JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어를 제공한다.

즉, JPQL은 **테이블이 아닌 엔티티 객체**를 대상으로 검색하는 객체 지향 쿼리이다. 

### 특징

- SQL과 문법이 유사하고 SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원
- JPQL을 한마디로 하면 객체지향 SQL이다.
- SQL을 추상화해서 특정 데이터 베이스 SQL에 의존하지 않음

***SQL은 데이터베이스 테이블**을 대상으로 쿼리를 생성하고 DB에 종속적이다.