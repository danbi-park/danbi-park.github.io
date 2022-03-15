---
title:  "컴포넌트"
excerpt: "클래스형 컴포넌트 vs 함수형 컴포넌트"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - react
tags:
  - react
  - basic
last_modified_at: 2022-03-15
---



리액트를 사용하여 프론트 개발을 할 때 두 가지 방법으로 컴포넌트를 선언할 수가 있다. 과거에는 클래스형 컴포넌트를 주로 사용했지만, 2019년 v16.8 부터 함수형 컴포넌트에 리액트 훅(hook)을 지원해 주어서 <span style="background:skyblue;">**현재는 공식 문서에서 함수형 컴포넌트와 훅을 함께 사용할 것을 권장**</span>하고 있다. 두 가지 방법에 대해 모두 다 잘 알고 있으며, 필요한 상황에 맞게 사용하는 것이 중요하므로 각각의 방법에 대해서 좀 더 깊이있게 살펴보도록 한다.

### 컴포넌트의 기능
컴포넌트는 단순한 템플릿 이상의 기능을 수행한다.  
<span style="background:skyblue;">**데이터가 주어졌을 때 이에 맞추어 UI를 만들어 주는 기능을 하는 것은 물론, 라이프 사이클 API를 통해 컴포넌트가 화면에 나타날 때, 사라질 때, 변할 때 작업들을 수행할 수도 있다.**</span>  

간단한 방식의 함수형 컴포넌트와 클래스형 컴포넌트를 선언하는 코드를 한 번 보도록 하자. 먼저 함수형 컴포넌트이다.

```jsx
import React from 'react';

function App() {
  const name = '리액트';
  return <div>{name}</div>;
}

export default App;
```

그 다음은 클래스형 컴포넌트이다.

```jsx
import React, { Component } from 'react';

class App extends Component {
  render() {
    const name = '리액트';
    return <div>{name}</div>;
  }
}

export default App;
```

클래스형 컴포넌트와 함수형 컴포넌트의 역할은 동일하다. 차이점이 있다면 클래스형 컴포넌트는
- state 기능 및 라이프 사이클 기능을 사용할 수 있으며 임의 메서드를 정의할 수 있다.
- render 함수가 꼭 있어야 하고, 그 안에서 보여 주어야 할 JSX를 반환해야 한다. 

```jsx
class Dog {
  constructor(name) {
    this.name = name;
  }
  
  say() {
    console.log(this.name + ': 멍멍');
  }
}
const dog = new Dog('강아지');
dog.say(); // 강아지: 
```

반면 함수형 컴포넌트는 
- 클래스형 컴포넌트보다 선언하기가 좀 더 편하고, 메모리 자원을 덜 사용한다.
- 과거에는 함수형 컴포넌트에서 state와 라이프사이클 API를 사용할 수 없다는 단점이 있었는데, 이러한 단점은 앞서 언급한 것처럼 **리액트 훅**이 도입되면서 해결되었다. 

함수형 컴포넌트를 선언할 때 사용하는 방법으로 기존의 일반적인 함수 선언 방식이 있고, ES6의 화살표 함수(arrow function) 방식도 있다. 화살표 함수의 경우 **함수를 파라미터로 전달할 때 유용**하다. 비슷한 점도 많지만, 두 문법이 확실하게 다르다는 점은 다음 예제를 통해서 알 수가 있다.

```jsx
function BlackDog() {
  this.name = '흰둥이';
  return {
    name: '검둥이',
    bark: function() {
      console.log(this.name + ': 멍멍!');
    }
  }
}

const blackDog = new Blackdog();
blackDog.bark(); // 검둥이: 멍멍!

function WhiteDog() {
  this.name = '흰둥이';
  return {
    name: '검둥이',
    bark: () => {
      console.log(this.name + ': 멍멍!');
    }
  }
}

const whiteDog = new Whitedog();
whiteDog.bark(); // 흰둥이: 멍멍!
```

function()을 사용하면 검둥이가 나타나고, () => {} 를 사용하면 흰둥이가 나타난다. 일반 함수는 자신이 종속된 객체를 this로 가리키며, 화살표 함수는 **자신이 종속된 인스턴스**를 가리킨다.

props는 프로퍼티(properties)를 줄인 표현으로 컴포넌트 속성을 설정할 때 사용하는 표현이다. props 값은 해당 컴포넌트를 불러와 사용하는 부모 컴포넌트에서 설정할 수 있다. 그리고 경우에 따라서 propTypes 컴포넌트 속성을 통해 props의 타입을 지정해 줄 수도 있다. 타입스크립트를 사용한다면 굳이 propTypes를 사용하지 않아도 타입 체크를 할 수가 있다. 클래스형 컴포넌트의 경우 render 함수에서 this.props를 조회해서 사용할 수 있다.

```jsx
import React from 'react';
import MyComponent from './MyComponent';

const App = () => {
  return <MyComponent name="리액트"></MyComponent>;
}

export default App;


// MyComponent.js
import React from 'react';
import PropTypes from 'prop-types';

const MyComponent = ({name}) => {
  return (
    <div>제 이름은 {name}입니다.</div>
  );
};

MyComponent.propTypes = {
  name: PropTypes.string
};

export default MyComponent;
```
state는 컴포넌트 내부에서 바뀔 수 있는 값을 의미한다. props의 경우 부모 컴포넌트가 설정해서 자식 컴포넌트는 읽기만 할 수 있는 값이며 바꾸기 위해서는 부모 컴포넌트에서 직접 변경을 해야 한다. 자식 컴포넌트 내에서 값을 변화하여야 하는 경우 state를 사용한다.

클래스형 컴포넌트에서는 클래스 내의 constructor 메서드에서 state의 초기값을 생성해 주어야 한다. 그리고 constructor를 작성할 때 super(props)를 반드시 호출해 주어야 한다. state를 조회할 때는 this.state로 조회하며, state의 값을 변경하고 싶을 때는 this.setState 함수를 통해서 바꾸어 준다. 예제 코드는 다음과 같다.

```jsx
import React, { Component } from 'react';

class Counter extends Component {
  constructor(props) {
    super(props);
    this.state = {
      number: 0
    };
  }
  render() {
    const { number } = this.state; // state 를 조회할 때에는 this.state 로 조회합니다.
    return (
      <div>
        <h1>{number}</h1>
        <button
          // onClick 을 통하여 버튼이 클릭됐을 때 호출 할 함수를 지정합니다.
          onClick={() => {
            // this.setState를 사용하여 state에 새로운 값을 넣을 수 있습니다.
            this.setState({ number: number + 1 });
          }}
        >
          +1
        </button>
      </div>
    );
  }
}

export default Counter;
```
state는 constructor 밖으로 빼서 생성해 줄 수도 있다. 그럴 경우에는 클래스 안에 state = { } 와 같이 선언해 주면 된다. 그리고 어떤 이벤트에서 state의 값을 동시에 여러 차례 바꾸어 주어야 할 수도 있는데 이 때 올바르게 연산이 일어나게 하려면 prevState를 인자로 받아서 매번 state를 바꿀 때 마다 직전까지 업데이트 된 state를 받아오고 연산을 처리한다.  

함수형 컴포넌트에서는 리액트 훅을 사용하여 useState 함수를 통해 state 값을 불러오고 변경할 수 있는데 리액트 훅에 대해서는 별도의 블로그 포스팅을 통해 설명해 보려고 한다.  

state를 적절하게 사용한다면 해당 컴포넌트 내에서 데이터가 변경될 때 마다 그때그때 변경된 데이터를 UI에서 불러올 수 있기 때문에 적절하게 사용하면 매우 유용하다. 함수형 컴포넌트든 클래스형 컴포넌트든 state를 직접 조작하는 것이 아닌, setState나 useState와 같은 세터 함수를 반드시 사용하여 조작해야 한다.

**클래스형 컴포넌트**는 다른 말로 **Stateful 컴포넌트**, 
**함수형 컴포넌트**는 **Stateless 컴포넌트**라고 하기도 한다.   
클래스형 컴포넌트는 로직과 상태를 컴포넌트 내에서 구현하기 때문에 stateful로 불리는 것이며 상대적으로 복잡한 UI 로직을 가지고 있다. 반면 함수형 컴포넌트는 state를 사용하지 않고 단순하게 데이터를 받아서(props) UI에 뿌려주기 때문에 stateless라고 불리는 것이다. 리액트 훅이 등장하면서 함수형 컴포넌트를 더 많이 사용하는 추세가 되었지만 오래된 리액트 코드의 경우 클래스형 컴포넌트로 이루어진 경우가 더 많으므로 두 가지 모두 다 잘 알고 있어야 리액트로 개발을 할 때 어려움을 겪지 않을 것이라고 생각한다.

 

### 참고자료
리액트를 다루는 기술, 김민준 저
https://medium.com/wesionary-team/react-functional-components-vs-class-components-86a2d2821a22

퍼옴 : https://devowen.com/298