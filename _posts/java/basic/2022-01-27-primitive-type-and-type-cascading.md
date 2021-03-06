---
title:  "기본형 타입, 기본형 타입(형)변환"
excerpt: "자바의 기본형 타입에 대해서 알아보자"
toc: true
toc_sticky: true
toc_label: "기본형 타입"
categories:
  - Java
tags:
  - Java 입문
last_modified_at: 2022-01-26
---

# 기본형 타입
## 기본형
기본형 타입은 가장 기본이 되는 데이터 타입으로써 정수형, 실수형, 문자형, 불린형을 의미한다.


<table>
<caption style ="text-align:left">기본형 타입 (Primitive Data Types) 한 눈에 보기</caption>
<tbody style="text-align:center">
<tr>
    <th>자료형</th>
    <th>키워드</th>
    <th>크기</th>
    <th>표현 범위</th>
    <th>사용예</th>
</tr>

<tr>
    <td>논리형</td>
    <td>boolean</td>
    <td>1byte</td>
    <td>true of false(1과 0이 아니다)</td>
    <td>boolean isFun = true;</td>
</tr>

<tr>
  <td>문자형</td>
  <td>char</td>
  <td>2byte</td>
  <td>0~65, 535</td>
  <td>char c = "f";</td>
</tr>

<tr>
  <td rowspan="5">정수형</td>
  <td>byte</td>
  <td>1byte</td>
  <td>-128 ~ 127</td>
  <td>byte b = 89;</td>
</tr>

<tr>
  <td>short</td>
  <td>2byte</td>
  <td>-32,768 ~ 32,767</td>
  <td>short s = 32760</td>
</tr>

<tr>
  <td>char</td>
  <td>2byte</td>
  <td>0 ~ 65,535</td>
  <td>char c = 64</td>
</tr>

<tr>
  <td>int</td>
  <td>4byte</td>
  <td>-21474836 ~ 2147483647</td>
  <td>int x = 59; int z = x</td>
</tr>

<tr>
  <td>long</td>
  <td>8byte</td>
  <td>...</td>
  <td>long big = 3456789L;</td>
</tr>


<tr>
  <td rowspan="2">실수형</td>
  <td>float</td>
  <td>4byte</td>
  <td>-3.4E038 ~ 3.4E038</td>
  <td>float f = 32.5f;</td>
</tr>

<tr>
  <td>double</td>
  <td>8byte</td>
  <td>-1.7E308 ~ 1.7E308</td>
  <td>double d = 23.34;</td>
</tr>

</tbody>
</table>



###  논리형
- 논리형은 크기가 1byte이고 true와 false 중에 한 가지 값을 가질 수 있다.
### 문자형
- 문자형은 char로 2byte크기를 가집니다. 문자는 작은따옴표를 이용하여 한 글자를 표현할 수 있다.
### 정수형
- 정수형은 정수를 표현하는 데이터 타입인데 표현할 수 있는 정수의 범위에 따라서 다양하게 제공된다.

- 정수형에서 가장 자주 사용하는 int는 4바이트이다.

- long형은 8byte크기를 가진다. int 보다 더 큰 범위의 정수를 표현하고 싶을때 사용한다.

### 실수형
- 실수형은 float과 double형이 있다.

- float은 4바이트, double은 8바이트로 double은 float보다 더 큰 범위의 실수를 표현할 수 있다.

### 리터럴
- 컴퓨터 과학 분야에서 리터럴이란, 소스 코드의 고정된 값을 대표하는 용어다.

- 리터럴은 일종의 값이다. true, false, 10, 11.1, a 등 이런 **값 자체**를 **리터럴**이라고 한다.

<img width="100%" alt="image" src="https://user-images.githubusercontent.com/86641773/151388987-3588c521-3068-4460-8544-f92b06ab7741.png">

이 리터럴을 특정 타입의 변수에 대입할 수 있다.


#### * **기본형 타입의 사용 방법**
```java
public class test {
    public static void main(String[] args){
        boolean isFun = true;

        char c ='f'; // 홑따옴표를 사용해야 한다. 문자 하나만 표현 !

        int x = 59;

        long big = 3456789L; //값을 적을때는 뒤에 소문자 l이나 대문자 L을 적어야 합니다.

        float f = 32.5f //float에 값을 대입할 때는 실수 뒤에 소문자 f나 대문자 F를 붙여야 합니다.

        double d = 23.34;
    }

}

```

# 기본형 타입(형)변환
**형변환이란, 변수 또는 리터럴의 타입을 다른 타입으로 변환하는 것이다.**

자바의 기본형의 종류를 보면 타입마다 크기가 다르지만 8byte의 long보다도 실수의 4byte인 float이 더 큰 타입으로 인식이 된다. 
실제 데이터를 담을 수 있는 크기는 long이 더 큰 것 같지만 실수는 소수점 뒤를 담을 수 있는 공간이 있다.
<div style="text-align:center">
<img width="70%" alt="image" src="https://user-images.githubusercontent.com/86641773/151390572-dc4ad52f-8167-4e8e-ad0f-4c167506124e.png">
</div>

자 아래 long과 int의 타입이 있다. 둘 다 물을 담을 수 있지만 얼마나 담을 수 있는지에 따라 다르다. 
<img width="239" alt="image" src="https://user-images.githubusercontent.com/86641773/151392070-7eb8ecc8-065a-4b4e-b14b-cef08dc55163.png">

만약 long컵에 있는 물을 int컵에 담는다고 할 때 long컵에 얼마나 많은 물이 있는지 모르기에 넘칠 수도 있다.
하지만 반대로 int 컵 안에 있는 물을 long 컵에 담는다고 할 땐 int안에 얼만큼 있는지 고려하지 않고도 담아도 된다고 생각된다.

이처럼 큰 데이터 타입에서 작은 데이터로 변환하려고 하면 컴파일러는 오류를 발생시키지만 반대로 작은 데이터 타입에서 큰 데이터 타입으로 변환시에는 오류를 발생시키지 않는다.

- 작은 -> 큰 : 묵시적 형변환
- 큰 -> 작은 : 명시적 형변환

### 묵시적 형변환
- 크기가 작은 타입을 크기가 더 큰 타입으로 바꿀 때에는 묵시적으로 형을 바꾸어 준다.
```java
int x = 50000;
long y = x;
```
- 이를 암묵적 형변환 이라고도 한다.

### 명시적 형변환
- 크기가 더 큰 타입을 작은 타입으로 바꿀 때에는 명시적으로 변환 해주어야 한다.
```java
long x = 50000;
//int y = x;  이렇게 묵시적으로 수행하면 컴파일러는 오류를 발생 시킨다.
int y = (int) x;  //반드시 (타입) 으로 명시적으로 형을 바꾸어 주어야 한다.
```
- 이를 강제 형변환 이라고도 한다.