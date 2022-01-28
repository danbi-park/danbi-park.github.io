---
permalink: /category/
title:  "[제어문] switch문과 do while문"
excerpt: "제어문 중에서 잘 활용안해본 switch와 do while 연습"
# toc: true
# toc_sticky: true
# toc_label: ""
categories:
  - Java
tags:
  - Java
last_modified_at: 2022-01-28
---


# switch문
- switch문은 어떤 변수의 값에 따라서 문장을 실행할 수 있도록 하는 제어문이다.
- switch문에서 사용하는 키워드는 switch, case, default, break 이다.

<caption>switch문</caption>
```java
    switch(변수){
        case 값1 : 
            실행문; 
            break;
        case 값2 : 
            실행문; 
            break;  
        default;    
    }
```
```java
    int value = 1;

    switch(value){
        case 1: 
            System.out.println("1");
            break;
        case 2:
            System.out.println("2");
            break;
        case 3 :
            System.out.println("3");
            break;
        default :
            System.out.println("그 외의 숫자");
    }
```
value의 값이 1일 경우 1을 출력하고, 2일 경우는 2를 출력하고, 3일 경우는 3을 출력하고, 그 외에는 그 외의 숫자가 출력된다.

```java
    case 1: 
        System.out.println("1");
    case 2:
        System.out.println("2");
    case 3 :
        System.out.println("3");
    default :
        System.out.println("그 외의 숫자");
```

break를 제거하면 value가 1일 경우 1일 출력되고 switch문장을 빠져나가는 것이 아니라  
1,2,3, 그외의숫자가 연속해서 실행된다. break문장이 있을 경우와 없을 경우를 확실하게 구분할 수 있어야 합니다.

### 참고
JDK7이전에는 switch다음 괄호안에 정수타입의 변수만 올 수 있었습니다. 그런데 JDK7부터는 switch다음 괄호안에 문자열 타입의 변수도 올 수 있습니다.

```java
    String str = "A";
    switch(str){
        case "A": 
            System.out.println("1");
        case "B":
            System.out.println("2");
        case "C" :
            System.out.println("3");
        default :
            System.out.println("그 외의 숫자");
    }
```

문자열의 값에 따라서 case블록의 내용이 출력되는 것을 알 수 있습니다.