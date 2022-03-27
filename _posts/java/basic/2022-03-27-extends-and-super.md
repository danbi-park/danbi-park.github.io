---
title:  "상속과 생성자 그리고 super"
excerpt: "상속의 기본형식과 특징, super로 자식클래스에서 부모의 생성자 이용하기"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - Java
tags:
  - Java 입문
  - 상속
  - 생성자
  - super
last_modified_at: 2022-03-27
---

- 부모의 유산을 물려 받는다.
- 객체 지향 프로그래밍에서 부모클래스에 정의된 멤버를 자식 클래스가 물려받음
- 상속을 통해 기존(부모) 클래스를 이용해 새로운(자식) 클래스를 만들 수 있다.

→ 적은 양의 코드로 새로운 클래스를 만들 수 있다는 장점이 있음

## 기본 형식

기본 형식

`클래스 이름` + `extends` + `부모클래스` 

```java
class A{ // A클래스 생성
    int x;
}
 
class B extends A{ // B클래스 생성 + A클래스를 상속받음
    int y;
}
 
class C extends B{ // C클래스 생성 + A클래스를 상속받은 B클래스를 생성(A+B)
    int z;
}
```

사용할 수 있는 변수

- A class : int x
- B class : int x, int y
- C class : int x, int y, int z

## 상속의 특징

- 다중 상속 x, 2개 이상의 클래스를 동시에 상속 받을 수 없다.
- **부모의 생성자는 상속이 안된다.**
- 부모클래스가 가진 멤버 변수와, 메서드 모두 상속 받는다.
- 부모클래스 내에서 멤버 변수, 또는 메서드가 **private 접근 제한자**를 사용하면 멤버 변수는 상속 받을 수 있으나 바로 접근이 불가능하고, 메서드는 상속이 되지 않게된다.
- static 메서드나 static 변수도 상속 된다.
- **동일한 이름의 변수가 부모클래스와 자식클래스 둘 다 존재할 경우 부모 클래스의 변수는 가려진다.** (밑에서 설명)

## 예제

```java
class ParentClass{ //부모클래스
    public void parentMethod(){
        System.out.println("부모클래스의 parentMethod()가 호출됨");
    }
}
 
class ChildClass extends ParentClass{ //자식클래스 생성 + 부모클래스(ParendClass)를 상속받음
    public void childMethod(){
        System.out.println("자식클래스의 childMethod()가 호출됨");
        System.out.println("부모클래스의 parentMethod()가 호출됨");
        parentMethod(); //부모클래스를 상속받았기때문에 부모의 메서드 사용 가능
    }
}
```

**InheritanceTest 실행** 

```java
public class InheritanceTest {
 
    public static void main(String[] args) {
        
        //객체생성
        ChildClass child = new ChildClass();
        
        child.childMethod(); //자식클래스(ChildClass)의 childMethod()를 호출
    }
}
```

자식클래스의 childMethod() 메서드 호출

**결과**

```
자식클래스의 childMethod()가 호출됨
부모클래스의 parentMethod()가 호출됨
부모클래스의 parentMethod()가 호출됨
```

상속을 받은 자식클래스의 childMethod() 메서드에서 부모클래스의 parentMethod()를 사용할 수 있고, 호출이 됐다는 걸 확인할 수 있습니다.

## 상속과 생성자 그리고 super

위에 써있는 상속의 특징중에서 **"동일한 이름의 변수가 부모클래스와 자식클래스에 둘 다 존재할 경우 부모클래스의 변수는 가려진다."** 라고 했는데, 만약, 부모클래스의 변수를 이용하고 싶을 때는 어떻게 해야할까?

**→** `super`라는 예약어를 사용한다. 
이 예약어를 사용하면 **상위클래스의 생성자에도 접근이 가능**하다.

### 예제

```java
class ParentClass{ //부모클래스
    private int age;
    
    public ParentClass(int age){//생성자
        this.age = age;
    }
    
    public void family(){
        System.out.println("부모입니다. 나이는 "+this.age+"살입니다.");
    }
}
 
class ChildClass extends ParentClass{ 
    private int age;
    
    public ChildClass(int age){ //생성자
        super(age+30);
        this.age = age;
    }
    
    public void family(){
        System.out.println("자식입니다. 나이는 "+this.age+"살입니다.");
    }
    
    public void childMethod(){
        family(); //자식클래스에 있는 family()
        super.family(); //부모클래스에 있는 family()
    }
}
 
public class InheritanceTest {
 
    public static void main(String[] args) {
        
        //객체생성
        ChildClass child = new ChildClass(20);
        
        child.childMethod(); //자식클래스(ChildClass)의 childMethod()를 호출
    }
 
}
```

1. 메인메서드에서 ChildClass 객체 생성하고, ChildClass의 생성자를 호출하여 20을 매개변수로 넘겨줌.
2. ChildClass 생성자내에서 super라는 예약어를 만나 부모클래스를 호출하고, age에 30을 더한 값을 부모클래스에 넘겨줌
→ 부모클래스의 멤버변수인 age는 50으로 초기화가 됨.
3. 자식클래스의 age에 매개변수 age의 값을 넣습니다. 처음에 메인메서드에서 넘겨주었던 매개변수 age의 값은 20이므로 자식클래스의 멤버변수 age에는 20으로 초기화됩니다.

*예제에 사용된 this는 자기 자신의 멤버를 가르킬때 사용된다.  즉, ChildClass 생성자에 있는 this.age는 ChildClass의 멤버변수 age를 의미한다.

```java
public ChildClass(int age){ //ChildClass 생성자
    super(age+30); //super를 사용하여 ParentClass 생성자로 age+30을 넘겨줌 -> 20+30
    this.age = age; //얘는 this!! 지 자신 -> ChildClass 여기론 20을 넘겨줌
}
```

1. 그리고 빠져나와서 메인메서드로 돌아가 ChildClass객체 내의 childMethod()를 호출한다.
2. childMethod() 에는 super 예약어를 사용해서 부모클래스의 family() 메서드를 호출.
 super 예약어를 이용하는 이유는 자식클래스의 family()메서드에 의해 이름이 같은 부모의 family() 메서드가 가려졌기(덮어졌기) 때문.

```java
public void childMethod(){
family(); //자식클래스에 있는 family()
super.family(); //부모클래스에 있는 family()
}
```

**결과**

```java
자식입니다. 나이는 20살 입니다.
부모입니다. 나이는 50살 입니다.
```

## super 예약어 사용법 정리

**super 예약어를 어떻게 사용하는지 정리를 해보자면,**

- 부모클래스의 멤버 변수에 접근 :  `super.멤버변수`
- 부모클래스의 멤버 메서드에 접근 : `super.멤버메서드(매개변수);` 
예) `super.family();`
- 부모클래스의 생성자 호출 : `super(매개변수);` 
예) `super(age+30);`

### **super 예약어 사용 시 주의사항**

- 반드시 자식클래스의 생성자 첫 라인에서 부모의 생성자를 호출해야 합니다.
- 자식클래스의 생성자 내에서 반드시 부모의 생성자를 호출해야 합니다.
- 근데! 명시적으로 자식의 클래스에서 부모의 생성자를 호출하지 않아도 super가 자동 삽입되어 부모클래스의 생성자를 호출합니다.

부모클래스와 자식클래스에서 명시적으로 생성자를 정의하지 않았을 경우에도 가상 머신에 의해서 디폴트 생성자가 알아서 추가된다. 

부모클래스에는 내용이 텅 빈 디폴트 생성자가 추가되고, 자식클래스의 경우에는 super(); 가 자동으로 삽입되기때문에 에러가 나지는 않는다.

출처: [https://private.tistory.com/27](https://private.tistory.com/27) [공부해서 남 주자]