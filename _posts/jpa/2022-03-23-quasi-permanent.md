---
title:  "준영속상태"
excerpt: ""
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - jpa
tags:
  - jpa
  - basic
last_modified_at: 2022-03-23
---

`em.persist();` 영속성 컨텍스트에 집어넣은 영속상태  
`em.find(~,~)` 영속성 컨텍스트로 가져온 영속상태  
*1차캐시(영속성 컨텍스트)에 없어도 DB에서 불러옴

## 그럼 준영속상태는!?

영속 상태의 엔티티가 영속성 컨텍스트에서 **분리(detached)된 상태**이다.  JPA에서 관리를 안하게 되어 영속성 컨텍스트가 제공하는 기능을 사용 못하게 된다.  

준영속 상태로 만드는 방법
- `em.detach(entity)` :
특정 엔티티만 준영속 상태로 전환
- `em.clear()` : 
영속성 컨텍스트를 완전히 초기화 
- `em.close()` :
영속성 컨텍스트를 종료

```java
Member member = new Member(200L, 'member200');
em.persist(member); //저장

em.detach(member); // 준영속화

tx.commit();
```

커밋 이후에도 아무일도 일어나지 않게 된다.



