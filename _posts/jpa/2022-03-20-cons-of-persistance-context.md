---
title:  "영속성 컨텍스트의 이점"
excerpt: ""
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - jpa
tags:
  - jpa
  - basic
last_modified_at: 2022-03-20
---


이 영속성 컨텍스트는 application과 db 사이의 중간 계층으로 볼 수 있는데 중간에 이게 있으면 왜 좋을까? 

## 영속성 컨텍스트의 이점
- 1차 캐시
- 동일성(identity) 보장
- 트랜잭션을 지원하는 쓰기 지연
(transactional write-behind)
- 변경 감지(Dirty Checking)
- 지연 로딩(Lazy Loading)

