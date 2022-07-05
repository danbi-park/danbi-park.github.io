---
title:  "필드와 컬럼 매핑"
excerpt: ""
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - jpa
tags:
  - jpa
  - basic
last_modified_at: 2022-05-19
---

```java
@Entity
public class Member {
    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob //VARCHAR를 넘은 큰 데이터를 넣고 싶을 때
    private String description;

    public Member() {

    }
}
```
| 어노테이션 | 설명 |
|---|---|
| @Column | 컬럼 매핑 |
| @Temporal | 날짜 타입 매핑 |
| @Enumerated | enum 타입 매핑|
| @Lob  | BLOB, CLOB 매핑
| @Transient | 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)|