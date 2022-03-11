---
title:  "어플리케이션 개발"
excerpt: ""
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - jpa
tags:
  - jpa
  - basic
last_modified_at: 2022-03-06
---

## JPA 구동 방식

<img alt="image" src="https://user-images.githubusercontent.com/86641773/156928786-e967c865-4959-4491-aa5a-31e4426cb1b8.png">

제일 처음 persistence.xml에서 설정 정보를 조회 한 후 persistence에서 먼저 persistence unit 이름을 전달 받은 후 엔티티 매니저 팩토리를 반환한다. 

## JPA 동작 확인

**JpaMain 클래스 생성**

![image](https://user-images.githubusercontent.com/86641773/157886217-33c354e3-2742-45ee-ac51-639ff0c95c4e.png)
  

```java
package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //데이터 베이스와 연결이 됨                         persistence.xml에 설정한 unit name과 이름 맞춤
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("jpa-practice");
        //그 다음 메니저를 꺼내야함
        EntityManager em = enf.createEntityManager();

        //여기서 실제 동작하는 코드를 작성하게 됨

        //애플리케이션이 끝나면 managerfactory를 닫아줘야함
        em.close();
        enf.close();
    }
}
```
  
## 객체와 테이블을 생성하고 매핑하기

![image](https://user-images.githubusercontent.com/86641773/157886357-3acd5307-da27-4374-9cb5-bdbf255c96d0.png)
  
유의할 점은 JDBC URL은 앞서 persistence.xml에서 설정한 url 경로와 맞춰줘야한다.  
  
```xml
<property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/>
```

연결 후 멤버 테이블을 하나 만들어 준다.

```sql
create table Member (
  id bigint not null,
  name varchar(255),
  primary key (id)
)
```

![image](https://user-images.githubusercontent.com/86641773/157886500-ab5db4c6-77c4-4d3e-8f3d-c3c1197a7e27.png)
   
클래스로도 하나 만들어준다.

```java
package hellojpa;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

**@Entity:** JPA가 관리할 객체
**@Id:** 데이터 베이스 PK와 매핑

Alt+insert 눌러서 Getter와 Setter를 만들어준다.

다시 main메소드로 들어와서

```java
package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //데이터 베이스와 연결이 됨                                          persistence.xml적은 unit과 이름 맞춤
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("jpa-practice");
        //그다음 메니저를 꺼내야함
        EntityManager em = enf.createEntityManager();

				//코드 작성
        Member member = new Member();
        em.persist(member);

        //애플리케이션이 끝나면 managerfactory를 닫아줘야함
        em.close();
        enf.close();
    }
}
```

Member를 저장해준다.

💡 참고로 EntityManagerFactory는 로딩 시점에 딱 하나만 만들어놔야한다.
예를들어 “고객이 어떤 행위를 하고 나간다”,”고객이 장바구니에 뭘 담았다” 등 실제 DB에 저장하거나 변경하는 것들을 **트랙잭션 단위**라고 하는데 이때 구동 방식이 DB 커넥션을 얻어서 쿼리를 나린다음에 종료되는 일관적인 일을 할 때마다 **엔티티 매니저를 하나 만들어줘야한다.**

위처럼만 실행해 버리면 아래와 같은 에러가 난다.

```java
Exception in thread "main" javax.persistence.PersistenceException: org.hibernate.id.IdentifierGenerationException: ids for this class must be manually assigned before calling save(): hellojpa.Member
	at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:154)
```

→ Id 가 필요하단 뜻, 아이디를 추가해주자.

```java
//코드 작성
  Member member = new Member();
  member.setId(1L);
  member.setName("HelloA");
  em.persist(member);
```

위처럼 set을 해주어도 오류가 난다.

그 이유는 JPA에서는 트렌젝션이라는 단위가 엄청 중요한데, **모든 데이터를 변경하는 모든 작업은 JPA에서 꼭 트랜잭션 안에서 작업을 해야한다**.

아래처럼 **EntityTransaction**을 추가해준다.

```java
public class JpaMain {
    public static void main(String[] args) {
        //데이터 베이스와 연결이 됨                                          persistence.xml적은 unit과 이름 맞춤
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("jpa-practice");
        //그다음 메니저를 꺼내야함
        EntityManager em = enf.createEntityManager();
				//매니저에서 transaction을 꺼낸다.
        EntityTransaction tx = em.getTransaction();
        tx.begin(); //트랙잭션 시작

        //코드 작성
        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");
        em.persist(member);

        tx.commit(); //커밋

        //애플리케이션이 끝나면 managerfactory를 닫아줘야함
        em.close();
        enf.close();
    }
}
```

쉽게 말해서 데이터 베이스 컬렉션을 하나 받는다고 생각하면 된다. 
실행 후 아래와 같은 로그가 출력된다. 

```
Hibernate: 
    /* insert hellojpa.Member 
        */ insert 
        into
            Member
            (name, id) 
        values
            (?, ?)
```

이제 로그에 위처럼 Hibernate가 나오는 설정은 persistence.xml에서 했었음!

- persistence 설정 내용 설명
  - 콘솔에 하이버네이트가 실행하는 SQL문 출력  (`hibernate.show_sql`)
  - 출력 시 보기 쉽게 정렬 (`hibernate.format_sql`)
  - 주석처리는 왜 이 쿼리문이 나왔는지 보여줌.(`hibernate.use_sql_comments`)


**H2-db에서 결과 확인:** 

![image](https://user-images.githubusercontent.com/86641773/157886610-73932b5e-bf9b-46f0-9552-03937f07b347.png)   

이처럼 직접 쿼리문을 직접 쓰지않고 JPA가 매핑 정보를 보고 쿼리를 생성해준다.  

어떻게 알아서 Member테이블에 insert 됐을까?  

관례적인 부분으로 같은 클래스 이름이 되어있어 저장된 것이고 만약 Member클래스에 @Table(name = "USER") 어노테이션이 있으면 쿼리가 나갈 때 User테이블에 저장된다.

마찬가지로 Colume도 만약 db에는 UserName이라고 되어있으면@Colume(name = “UserName”)이라고 맵핑해주면 된다.

더 나아가서 위의 코드는 좋지 않다. 만약 setId, setName에서 문제가 생기면 em.close(), emf.close()가 실행되지 않는다. 

그래서 

```java
try {
      Member member = new Member();
      member.setId(2L);
      member.setName("HelloB");

      em.persist(member);

			tx.commit();
  } catch (Exception e) {
			tx.rollback(); //예외가 생길 시 롤백해주고
  } finally {
			em.close(); //작업이 끝나면 엔티티 매니저를 닫아줌.
}
emf.close(); //전체 애플리케이션이 끝나면 팩토리도 닫아줌.
```

try catch문으로 감싸주는 것이 중요한데, 엔티티 매니저가 결국 내부적으로 데이터 베이스 커넥션을 물고 동작하기 때문에 사용을 다하고 나면 꼭 닫아주어야한다.

엔티티 매니저를 마치 자바 컬렉션 처럼 객체를 대신 저장해주는 일을 한다. 라고 이해를 하면된다.

여기까지가 정석이고 실무에서는 스프링이 위의 작업을 다해주기 때문에 걱정하지 않아도 된다.

### 멤버 수정 해보기

우선 멤버를 먼저 검색해본다.

```java
//<T> T find(Class<T> var1, Object var2); 클래스와 ID를 통해 멤버를 조회한다. 
Member findMember = em.find(Member.class, 1L);

System.out.println("ID: "+findMember.getId());
System.out.println("NAME: "+findMember.getName());
```
결과 :  
```
Hibernate: 
    select
        member0_.id as id1_0_0_,
        member0_.name as name2_0_0_ 
    from
        Member member0_ 
    where
        member0_.id=?
ID: 1
NAME: HelloA
```

삭제는 em.remove() 로 `em.remove(findMember);` 처럼 찾은 애를 넣어주면 된다.

이제 수정할 때는 setter를 이용하는데 em.persistence에 따로 저장을 안해도 수정이 된다.

```java
findMember.setName("HelloJPA");
//em.persist(findMember);
```

왜냐면 마치 자바 컬렉션을 다루는 것처럼 설계되서 그렇다.

```
Hibernate: 
    select
        member0_.id as id1_0_0_,
        member0_.name as name2_0_0_ 
    from
        Member member0_ 
    where
        member0_.id=?
Hibernate: 
    /* update
        hellojpa.Member */ update
            Member 
        set
            name=? 
        where
            id=?
```

update쿼리가 나갔다.

결과:   
![image](https://user-images.githubusercontent.com/86641773/157887456-dd1dadea-c5c5-4eb2-a111-6fc18ae2f2f3.png)

➡️ JPA를 통해서 엔티티를 가져오면 JPA가 관리를 한다. 트랜잭션을 커밋하는 시점에 변경이 되었는지 안되었는지 체크를 해서 변경사항이 있으면? 업데이트 쿼리를 생성한다. 

트랜잭션 직전에 업데이트 쿼리를 날리고 트랜잭션이 딱 커밋이 된다.

## 주의할 점 정리

- 엔티티 매니저 팩토리는 웹 서버가 실행되는 시점에 딱 **하나만 생성해서 애플리케이션 전체에서 공유**한다. (DB당 하나)
- 팩토리 안에서 엔티티 매니저는 **쓰레드 간에 공유를 하지 않는다**(사용하고 버려야한다.) →  고객의 요청이 올 대마다 계속 쓰고 버렸다 한다.
- **JPA의 모든 데이터 변경은 트랜잭션 안**에서 실행

*DB는 내부적으로 트랙잭션 개념을 가진다.