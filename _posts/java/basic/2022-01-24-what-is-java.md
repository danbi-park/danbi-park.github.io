---
title:  "자바란?"
excerpt: "자바의 개념과 특징"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - Java
tags:
  - Java 입문
last_modified_at: 2022-01-24T08:06:00-05:00
---
<style>
  th,td {border: 1px solid gray;}
</style>

<div style="text-align:center;">
    <img style="width:80px;" src="https://w.namu.la/s/95f3898eb4996f6ba5a3930b212b295da56e062e9427da87331a510d3d868bd81f24d10d242ca0d93f4ad94053b9321549cb4590ea815a8d39ba92cde1a7da4499ad6d93e2767112f9d0c60ef4cffdcfe6e248936ca50a4dc5bd3ccf3d544345">
</div>
<div style="text-align:center;margin-left:30%;margin-top:15px;">
   <table>
      <tr>
        <th>운영 및 개발</th>
        <td>오라클</td>
      </tr>
      <tr>
        <th>개발자</th>
        <td>제임스 고슬링</td>
      </tr>
      <tr>
        <th>최초 버전</th>
        <td>1.0 (1995.01.23)</td>
      </tr>
      <tr>
        <th>링크</th>
        <td><a href="https://www.oracle.com/java/">https://www.oracle.com/java/</a></td>
      </tr>
    </table>
</div>

# 자바란?
자바는 썬 마이크로시스템즈에서 개발하여 1995년 1월에 공식적으로 발표한 객체지향 프로그래밍 언어입니다. 
- 자바는 운영체제에 독립적으로 자바로 작성된 프로그램은 운영체제(윈도우, 리눅스, 맥 등)의 종류에 관계없이 실행이 가능하다는 큰 장점이 있습니다.  
- 또한 객체지향개념과 기존의 다른 프로그래밍 언어, 특히 C++의 장점을 채택하고 잘 사용되지 않는 부분은 제외시켜 비교적 배우기 쉽고 이해하기 쉽게 표현이 가능하도록 했습니다. 

_*현재 썬 마이크로시스템즈는 오라클에 인수가 된 상태_
  
## 자바의 특징
1. 운영체제에 독립적이다. 
   -  자바는 JVM() 만 있으면 윈도우, 리눅스, 맥등 어떤 플랫폼에서도 실행이 가능하다.
   - 프로그램 실행의 주체가 운영체제가 아닌 JVM이기 때문!
   - Write once, run anywhere!
2. 객체지향언어이다.
   - 상속, 캡슐화, 다형성이 잘 적용되었다.
3. 자동 메모리 관리 (Garbage Collection)
    - 자바로 작성된 프로그램이 실행되면 GC가 자동으로 메모리 관리를 해준다.
4. 네트워크와 분산처리를 지원한다.
   - 다양한 네트워크 프로그래밍 라이브러리(Java API)를 통해 비교적 짧은 시간에 네트워크 관련 프로그램을 개발할 수 있도록 지원한다.
5. 멀티쓰레드를 지원한다.
   - 일반적으로 멀티쓰레드의 지원은 운영체제에 따라 다른데 자바에서 개발되는 멀티쓰레드 프로그램은 시스템과 관계없이 구현가능하고 관련 라이브러리가 제공되어 구현이 쉽다. 
   - 여러 쓰레드에 대한 스케줄링을 자바 인터프리터가 담당하게 된다. 
6. 동적로딩(Dynamic Loading)을 지원한다.
   - 실행 시에 모든 클래스가 로딩되지 않고 필요한 시점에 클래스를 로딩하여 사용할 수 있다. 
   - 일부 클래스가 변경되어도 전체 애플리케이션을 다시 컴파일 하지 않아도 되며, 변경사항이 발생해도 비교적 쉽게 처리할 수 있는 유연한 애플리케이션을 작성할 수 있다. 


## Java Platform
- Java SE(Java Standard Edition)  
   - 기본적인 자바개발환경 플랫폼  
   - JVM + API 개발환경
- Java EE(Java Enterprise Edition) - J2EE
   - Java SE + Web Server 개발 환경
   - JSP, Servlet, EJB, JMS 등의 기능
   - WAS 서버라고 부르며 Jeus Glass fish, JBoss, Apache, Jetty 등의 제품이 있다.
- Java ME(Java Micro Edition) - J2ME
   - 임베디드 장비를 위한 개발 환경
   - Java SE 의 기능을 축소


### 자바 개발 환경
- JDK(Java Software Developer's Kit)
  -  소스코드를 자바 바이트 코드로 변경하고 실행할 수 있는 환경을 제공
  -  JRE와 각종 개발툴로 구성되어 있다. 개발툴은 설치폴더\bin폴더안에 위치한다.
  -  개발툴들 : javac.exe, javadoc.exe, ...  
<br>
- JRE(Java Runtime Environment)
  - 자바의 바이트 코드를 실행할 수 있는 환경을 제공
  - JVM(Java Virtual Machine) 과 각종 API(Application Programming Interface)로 구성되어 있다.
  - API : Core API, Integration API, GUI API, ...
  - 어플리케이션 배포기술(Java Web Start, Java Plug-in)  
<br>
- JVM(Java Virtual Machine)
  - Java Virtual Machine이란 이름에서 알 수 있듯 JVM은 가상의 컴퓨터이다. 이는 실제 컴퓨터의 플랫폼에 상관없이 자체적으로 instruction set을 가지고 있으며, 메모리 지역을 관리할 수 있다는 것이다.



### ※ JDK, JRE, JVM관계
자바프로그램을 만들기 위해서는 소스코드를 컴파일 시키는 프로그램(유틸)들이 필요하고 이파일을 실행시키기 위해서는 JVM(Java Virtual Machine)과 자바 클래스 라이브러리(Java API)를 필요로 한다.
JVM(Java Virtual Machine)과 자바클래스 라이브러리(Java API)를 합쳐 JRE(Java Runtime Environment)라 부른다.  

- JRE = JVM + Java API
- JDK = JRE + 개발에 필요한 실행파일(javac.exe 등)
  
  ![image](https://user-images.githubusercontent.com/86641773/152208646-df221987-84cb-46b9-b603-a01f63655a25.png)



💡 참고  
일반적으로 자바로 작성된 클래스파일을 실행만 할것이라면 JRE까지만 설치하면 될 것이고, 개발까지 한다면 JDK를 설치하면 된다.  
개발 계획이 없더라도 JDK를 설치해야하는 경우가 있다. 예를 들어 JSP를 사용하여 웹 애플리케이션을 배포하는 경우 기술적으로 애플리케이션 서버 내에서 Java 프로그램을 실행하기 때문에 JDK가 필요하다. 
애플리케이션 서버는 JSP를 Java 서블릿으로 변환하고 JDK를 사용해서 Servlet을 컴파일 하기 때문이다.
<br><br>

### 출처 
- [JAVA언어란? (자바 교실)](https://cafe.naver.com/jjdev/227)
- [Java 플랫폼 종류 요약](https://leechwin.tistory.com/entry/Java-Java-플랫폼-종류-요약)