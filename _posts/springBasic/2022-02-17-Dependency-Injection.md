---
title:  "의존성 주입(Dependency Injection)"
excerpt: "필요한 의존성을 어떻게 받아올 것인가"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - spring
tags:
  - framework
  - spring
  - basic
  - DI
  - Dependency Injection
last_modified_at: 2022-02-17
---

## 의존성 주입(Dependency Injection)이란?
한 객체를 직접 생성하는 것이 아닌 외부로 부터 필요한 객체를 받아서 사용하는 것이다.
이를 통해 객체간의 결합도를 줄이고 코드의 재활용성을 높여준다.


의존성 주입은 주로 @Autowired / @Inject 를 통해 주입을 받아 사용합니다.

> @Autowired가 없더라도 기본적으로 어떠한 빈의 생성자가 오직 하나만 있고 그 생성자에 파라미터로 받는 타입의 빈이 존재한다면 그 빈은 주입이 됩니다.
```java
class OwnerController{

    // @Autowired를 붙일 수도 있음
    private final OwnerRepository owners;

    // @Autowired를 붙일 수도 있음
    public OwnerController(OwnerRepository clientService){
        this.owners = clientService; 
    }
}
```

@Autowired / @Inject 를 어디에 붙여야 할까?
- 생성자
- 필드
- Setter

DI, 의존성 주입은 필요여기서 Spring이 DI 컨테이너를 필요로 하는 이유를 알 수 있는데, 우선 Store에서 Product 객체를 주입하기 위해서는 애플리케이션 실행 시점에 필요한 객체(빈)를 생성해야 하며, 의존성이 있는 두 객체를 연결하기 위해 한 객체를 다른 객체로 주입시켜야 하기 때문이다.


한 객체가 어떤 객체(구체 클래스)에 의존할 것인지는 별도의 관심사이다. Spring에서는 DI 컨테이너를 통해 서로 강하게 결합되어 있는 두 클래스를 분리하고, 두 객체 간의 관계를 결정해 줌으로써 결합도를 낮추고 유연성을 확보하고자 하였다. 의존성 주입으로 애플리케이션 실행 시점에 객체를 생성하고 관계를 결정해 줌으로써 다른 구체 클래스에 의존하는 코드를 제거하며 서로 다른 두 객체의 결합을 약하게 만들어 주었다. 또한 이러한 방법은 상속보다 훨씬 유연하다. 단, 여기서 주의해야 하는 것은 다른 빈을 주입받으려면 자기 자신이 반드시 컨테이너의 빈이여야 한다는 것이다.


- 두 객체 간의 관계라는 관심사의 분리
- 두 객체 간의 결합도를 낮춤
- 객체의 유연성을 높임
- 테스트 작성을 용이하게 함
 

하지만 의존 관계를 주입할 객체를 계속해서 생성하고 소멸한다면, 아무리 GC가 성능이 좋아졌다고 하더라도 부담이 된다. 그래서 Spring에서는 Bean들을 기본적으로 싱글톤(Singleton)으로 관리하는데, 우리는 이에 대해 자세히 알 필요가 있다.

 

출처
https://mangkyu.tistory.com/150 [MangKyu's Diary]
https://velog.io/@wlsdud2194/what-is-di