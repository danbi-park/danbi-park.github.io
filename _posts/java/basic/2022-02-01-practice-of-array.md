---
title:  "배열 사용하기"
excerpt: "for문을 사용해서 배열 출력해보기"
# toc: true
# toc_sticky: true
# toc_label: ""
categories:
  - Java
tags:
  - Java 입문
last_modified_at: 2022-02-01
---


# 배열 사용하기

## for 반복문을 이용해서 배열 사용하기
- 배열에 접근할때에는 인덱스를 통해서 접근한다.

```java 
    int[] iarray = new int[100];
    iarray[0] = 1; 
    iarray[1] = 2; 
```


- 배열에 0번째 인덱스 부터 시작해서 1부터 100까지 넣기
  - 정수를100개 저장 할 수 있는 배열을 생성

```java 
    int [] iarray = new int[100];
```
- 배열의 길이를 알아내는 방법
```java 
    iarray.length 
    //배열을 참조하는 레퍼런스 변수.length 하면 해당 배열의 길이를 리턴한다. 
```

- 배열에 1부터 100까지 값넣기
```java
    //배열에 값을 반복적으로 넣어야 하므로, for 반복문을 이용한다. 
    for(int i = 0; i < iarray.length; i++){ 
    //배열의 인덱스는 0부터 시작하므로, 0부터 배열의 길이보다 하나 작을때까지 반복하면 배열의 크기만큼 반복할 수 있다. 
            iarray[i] = i + 1;  
    //배열의 인덱스는 0부터인데 넣고 싶은 값은 1부터 사용해야하므로, 인덱스에 1을 더해준 값을 넣어준다. 
    }
```

- 배열에 저장된 값을 모두 더해주기
```java
    int sum = 0; 
    //값을 누적하기위한 변수는 반복문 밖에서 선언한다. 

    for(int i = 0; i< iarray.length; i++){  
    // 배열의 크기만큼 반복한다.         

        sum = sum + iarray[i];
        //반복문 밖에서 선언한 변수에 반복적으로 값을 누적한다. 
    }

    System.out.println(sum);
    //배열에 들어있는 모든 값 누적한 변수를 출력한다. 
```