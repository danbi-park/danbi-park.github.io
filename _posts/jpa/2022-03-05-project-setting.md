---
title:  "프로젝트 생성"
excerpt: ""
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - jpa
tags:
  - jpa
  - basic
last_modified_at: 2022-03-05
---

## 프로젝트 생성

Maven선택 후 이름 지정 

- **프로젝트 이름** : jpa-basic1
- **위치**: D:\study\inflearnJPA
- **GroupId**: org.practice
- **ArtifactId**: jpa-basic1
- **Version**: 1.0-SNAPSHOT

#### groupId, ArtifactId, version 설명
    
**groupId**
    
> groupId uniquely identifies your project across all projects. A group ID should follow Java's package name rules. This means it starts with a reversed domain name you control. For example, org.apache.maven, org.apache.commons Maven does not enforce this rule. There are many legacy projects that do not follow this convention and instead use single word group IDs. However, it will be difficult to get a new single word group ID approved for inclusion in the Maven Central repository. You can create as many subgroups as you want. A good way to determine the granularity of the groupId is to use the project structure. That is, if the current project is a multiple module project, it should append a new identifier to the parent's groupId. For example, org.apache.maven, org.apache.maven.plugins, org.apache.maven.reporting
> 
- 모든 프로젝트 중에서 당신의 프로젝트를 식별하게 해주는 식별자
- groupId는 Java의 패키지 이름 규칙을 따라야 함 즉 제어하는 도메인 이름의 반대로 시작

ex) org.apache.maven, org.apache.commons

- 만약에 프로젝트가 다중 모듈 프로젝트인 경우 부모의 groupId에 새 식별자를 추가해 사용

ex) org.apache.maven, org.apache.maven.plugins, org.apache.maven.reporting

**artifactId**

> artifactId is the name of the jar without version. If you created it, then you can choose whatever name you want with lowercase letters and no strange symbols. If it's a third party jar, you have to take the name of the jar as it's distributed.
> 
- 버전 없는 jar파일의 이름
- 특수 문자를 사용하지 않고 소문자로만 작성
- third party jar 파일인 경우면, 할당된 이름을 사용해야 함

**version**

> if you distribute it, then you can choose any typical version with numbers and dots (1.0, 1.1, 1.0.1, ...). Don't use dates as they are usually associated with SNAPSHOT (nightly) builds. If it's a third party artifact, you have to use their version number whatever it is, and as strange as it can look. For example,
> 
- 배포를 하려면 숫자와 점으로 구성된 일반적인 버전(1.0,1.1,1.0.1,...)인 형태를 사용함
- SNAPSHOT (nightly) 빌드와 관련된 날짜 버전은 사용하지 않음
- third party artifact 라면, 이상하네 보일지 라도 그들이 준 버전 넘버를 사용함

### **※ 정리**

쉽게 말하면 groupId는 프로젝트의 큰 틀, artifactId는 프로젝트의 각 기능들이다. 예를 들어 지하철 관련 프로젝트를 개발한다고 한다면 groupId는 지하철 노선 전체를 뜻하고, artifactId는 1호선, 2호선 등등을 말합니다.

출처: [https://junghn.tistory.com/entry/SPRINGMaven-프로젝트-groupId-artifactId-version-이란](https://junghn.tistory.com/entry/SPRINGMaven-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-groupId-artifactId-version-%EC%9D%B4%EB%9E%80)[코딩 시그널]


## 의존 설정(Dependencies)

---

우선 Hibernate 를 추가해줄 것인데, 제일 최근 버전은 아래 Hibernate 페이지에서 확인할 수 있다.

### Hibernate 개념
    
#### Hibernate ORM

Hibernate ORM은 **자바 언어를 위한 객체 관계 매핑 프레임워크**이다.

간단히 말해 관계형 디비 테이블을 자바 객체로 맵핑 시켜 사용하겠다는 것이다.
    
**키워드**
>OneToOne, OneToMany, ManyToOne, ManyToMany, Primary Key, Composite Primary Key, join table, SubSelect, NamedNativeQuery, Unidirectional, Bidirectional , HQL, Fetch LAZY, Fetch EAGER, orphanRemoval, Cascade, Extends Dialect, Column LAZY Loading ....

    
### ORM 의 장점/단점

**장점**

- 재사용성이 좋음
- 객체로 쿼리 결과가 자동으로 자바객체로 맵핑되므로 편함
- DB 종류 바뀌어도 대응이 쉬움

**단점**

- 원시적으로 다가가고 싶을 때 힘듬, nativeSQL기능이 있긴한데 이거 쓰면 ORM쓰는 이유가 있나 싶다.
- 성능을 위해서는 쿼리를 알긴 해야함.
- hibernate 가 지원하지 않는 sql function은 Dialect를 상속받아 등록해야함, 예를 들어 Bitwise(비트연산자)가 hibernate가 해석을 못해 dialect를 상속받고 function 마냥 만들어서 썼음.

출처: [https://onecellboy.tistory.com/349](https://onecellboy.tistory.com/349)


![[https://hibernate.org/](https://hibernate.org/)  ](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/735329a0-6385-4801-9c3d-6e521d74ecab/Untitled.png)

[https://hibernate.org/](https://hibernate.org/)  

- Hibernate 버전을 선택할 때 참고할 점

결국 Spring과 엮이게 되있어서 Spring 사이트에 들어가서 reference를 보는 게 좋음

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e8fe6f4c-2a88-4d92-bb5a-512c9a3c9423/Untitled.png)

Spring > project > spring Boot 선택

내가 쓸 스프링 부트 버전에 Reference Doc.을 한 번 살펴본다.

![[https://spring.io/projects/spring-boot#learn](https://spring.io/projects/spring-boot#learn)](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/01dd614b-6b4a-479f-9dd2-6d5c06e2a55a/Untitled.png)

[https://spring.io/projects/spring-boot#learn](https://spring.io/projects/spring-boot#learn)

맨 아래 보면 Dependency Versions가 있다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b57e89bf-ffba-4ea3-825f-6a10a7cca8c7/Untitled.png)

예를 들어 2.6.2 버전은 hibernate-entitymanager라이브러리를 쓸 때 라이브러리는 5.6.3.Final이 적합하단 뜻이다.


**pom.xml에 코드 작성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.practice</groupId>
    <artifactId>jpa-basic1</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- JPA Hibernate  -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
            <version>5.3.10.Final</version>
        </dependency>

        <!-- H2 database -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.0.204</version>
        </dependency>

    </dependencies>

</project>
```

entitymanager와 h2 database를 추가해준다. 

이때 다운 받았던 H2 데이터 베이스 버전은 일치해야한다.

![H2-database 다운 받았던 곳](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e35007c4-797c-4654-bd1a-d82248e54a33/Untitled.png)

H2-database 다운 받았던 곳

## JPA 설정하기 - persistence.xml

---

JPA설정 파일인 persistence.xml을 만들어준다.

**persistence파일은 정해진 위치에 있어야한다. →  /META-INF/persistence.xml** 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c146a260-6b32-4ca2-bd13-af46a917d498/Untitled.png)

**persistence.xml 파일 생성**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             version="2.1">
    <persistence-unit name="jpa-practice">
        <properties>
            <!-- 필수 속성 -->
            <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
            <property name="javax.persistence.jdbc.user" value="sa"/>
            <property name="javax.persistence.jdbc.password" value=""/>
            <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
            <!-- 데이터베이스 방언 : JPA는 특정 데이터 베이스에 종속되지 않도록 되어있음-->

            <!-- 옵션 속성 -->
            <!-- 콘솔에 하이버네이트가 실행하는 SQL문 출력 -->
            <property name="hibernate.show_sql" value="true"/>
            <!-- SQL 출력 시 보기 쉽게 정렬 -->
            <property name="hibernate.format_sql" value="true"/>
            <!-- 쿼리 출력 시 주석(comments)도 함께 출력 -->
            <property name="hibernate.use_sql_comments" value="true"/>
            <!-- JPA 표준에 맞춘 새로운 키 생성 전략 사용 -->
            <property name="hibernate.id.new_generator_mappings" value="true"/>
            <!-- 애플리케이션 실행 시점에 데이터베이스 테이블 자동 생성 -->
<!--            <property name="hibernate.hbm2ddl.auto" value="create"/>-->
            <!-- 이름 매핑 전략 설정 - 자바의 카멜 표기법을 테이블의 언더스코어 표기법으로 매핑
             ex) lastModifiedDate -> last_modified_date -->
            <property name="hibernate.ejb.naming_strategy" value="org.hibernate.cfg.ImprovedNamingStrategy"/>
        </properties>
    </persistence-unit>
</persistence>
```

> **필수 속성**
- persistence-unit name으로 이름을 지정
- javax.persistence로 시작 : JPA 표준 속성으로 라이브러리(구현체)에 상관없이 적용됨.
- hibernate로 시작: 다른 라이브러리(구현체)로 바꾸면 적용이 안됨. Hibernate 전용 속성임.
- **데이터베이스 방언** : JPA는 특정 데이터 베이스에 종속되지 않도록 되어있음
    - JPA는 특정 데이터 베이스에 종속되지 않도록 되어있다.
    - 각각의 데이터베이스가 제공하는 SQL 문법과 함수는 조금씩 다르다. (가변 문자: MySQL은 VARCHAR, Oracle은 VARCHAR2...등 특히 페이징이 limit, rownum으로 달라서 굉장히 까다롭다.)
    - 방언: SQL 표준을 지키지 않는 **특정 데이터베이스만의 고유한 기능**으로  hibernate.dialect 속성에 지정한다.
        
        예) H2: org.hibernate.dialect.H2Dialect / Oracle10g : org.hibernate.dialect.Oracle10gDialect 등, hibernate는 40가지 이상의 방언을 지원한다.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1949dd43-565a-4163-9c08-c394449a45da/Untitled.png)
        

**옵션**
[hibernate.hbm2ddl.auto 속성](https://www.notion.so/40cbc1f193ea4dfcb86c78943f81dda8)


> **🗒️ 추천 전략**
개발 초기 단계 : create 또는 update
테스트 서버 : update 또는 validate
운영 서버 : validate 또는 none
