---
title:  "깃 캐시 삭제하기"
excerpt: "git cache clear~!"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - git
tags:
  - git
  - cache
last_modified_at: 2022-04-06T08:06:00-05:00
---

git ignore 파일을 추가하기 전에 커밋해서 필요없는 파일들, 다른 파일에 영향을 줄 수 있는 gradle 설정파일 등이 stage에 표시되었다. 

이렇게 깃을 사용하다보면, .gitignore에 분명 무시할 파일들을 추가했는데도 해당 파일을 추적하는 경우가 있는데, 그 이유는 .gitignore에 파일을 추가하기 전에 stage에 올라간 파일들은 캐시처리가 되어 기록이 남아있기 때문에다. 

## git cache 삭제 실행
아래의 명령어로 캐시 삭제를 수행할 수 있다.
```
//캐시 삭제
git rm -r --cached .  

//디렉터리 내 모든 파일을 추가
git add .

//메세지와 함께 커밋
git commit -m "cache clear"
```


### 출처
[[Git] Git 캐시(Cache) 삭제](https://tychejin.tistory.com/231?category=890368)