---
title:  "React 기초 2"
excerpt: "props와 state"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - react
tags:
  - react
  - basic
last_modified_at: 2022-02-26
---

## Props와 State
Props와 State는 리액트에서 데이터를 다룰 때 사용하는 개념입니다.

# State
State는 하나의 컴포넌트가 가질 수 있는 **변경 가능한 데이터** 입니다.  
컴포넌트를 렌더링 할 때 새로운 데이터를 생성해야 한다던지, 아니면 기존의 데이터를 참고해서 새로운 데이터를 만들어야 할 때 사용할 수 있습니다. 예제를 보여드리도록 하겠습니다. App.js 파일을 열어서 밑에 코드처럼 변경해 주세요.

#### 변수 hello 만들기 

```jsx
class App extends Component{
state = {
    hello : 'hello app js!'
}
//변수 hello 생성
render() {
        return (
            <div className="App">
                <div>{this.state.hello}</div>
            </div>
        );
    }
}
```
<div style="text-align:center" >
<img style="width:200px;" src="https://user-images.githubusercontent.com/86641773/155874397-578d94c4-145e-4c85-bae3-06b53b17e642.png">
</div>

`{this.state.hello}` 부분이 hello app js!라는 문자열이 들어가 있는 State변수가 되는 것입니다.

☝ State 뿐만 아니라 JSX에 변수를 넣을 때에는 중괄호 {}에 담아야 한다!

이제, **this.state.hello 값을 변경**해 보도록 하는 **메소드**를 하나 만들어 보도록 하겠습니다. App.js 파일을 수정합니다.

#### 변수의 상태를 변경하는 메소드 만들기 

```jsx
class App extends Component{
state = {
    hello : 'hello app js!'
}

handleChange = () => {
    this.setState({
        hello: 'bye app js!'
    });
}
//변수 hello 생성
render() {
    return (
        <div className="App">
            <div>{this.state.hello}</div>
            <button onClick={this.handleChange}>click me!</button>
        </div>
        );
    }
}

export default App;
```

<div style="text-align:center" >
<img style="width:200px;" src="https://user-images.githubusercontent.com/86641773/155874626-89377af0-7724-455d-9470-a6265f824e6e.png">
</div>

`this.setState` 메소드를 통해 state인 hello 의 데이터가 bye app js! 라는 문자열로 바뀌게 됩니다.
그렇게 되면 JSX에서 hello를 보여 주는 부분이 bye app js! 로 보여지게 되겠죠.
HTML에서 버튼에 click 이벤트를 줄 때에는, `onClick`이벤트를 추가해 함수를 전달하곤 했었는데 리액트 에서도 기본적인 메소드 이름은 같습니다. 한가지 유의해야 할 점은, 앞으로 우리가 사용하게 될 이벤트들의 이름은 `*camelCase`를 사용합니다.  
*두번째 단어의 첫 시작은 대문자로  

✅ 리액트는 JSX 파일이기 때문에 이벤트를 줄 때는   
`this.handleChange()` --- ❌  
`this.handleChange`   --- ⭕  
-> JSX가 HTML로 바뀌는 과정에서 함수가 실행이 되버리기 때문에

#### 1 더하기

```jsx
class App extends Component{
state = {
    count : 0
}

countup = () => {
    this.setState({
        count : this.state.count + 1
    });
}

render() {
    return (
        <div className="App">
          <div>{this.state.count}</div>
          <button onClick={this.countUp}>count up!</button>
        </div>
        );
    }
}

export default App;
```

<div style="text-align:center" >
<img style="width:200px;" src="https://user-images.githubusercontent.com/86641773/155874928-50a751ab-9eac-4936-9267-e328fbe0354c.png">
</div>

결론은, state는 **현재 컴포넌트 안에서 새로운 데이터를 만들어 낼 때 사용**합니다. 지금 알 수 있듯이 countUp 메소드가 실행될 때 마다 state인count의 숫자는 올라갑니다. 컴포넌트 안에서 count 라는 데이터가 생성되어 countUp 이라는 메소드에 의해 잘 작동이 된 것이죠.

출처 : https://berkbach.com/%EA%B8%B0%EC%B4%88%EB%B6%80%ED%84%B0-%EB%B0%B0%EC%9A%B0%EB%8A%94-react-part-4-64164239179f
 

---

## ☝ State와 Props 
1. State는 현재 컴포넌트 내에서 변경이 가능하다.
2. Props는 현재 컴포넌트 내에서 변경이 불가능하다.
(예전 리액트 버전에서는 가능했으나 현재 버전에서는 사용하지 않는 것을 추천)
3. Props와 State 모두 하위 컴포넌트에 상속이 가능하다


# Props 
일단 Props를 만들어 출력해 보겠습니다. index.js 파일을 변경해주세요.
```jsx
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App'; //뒤에 js가 생략된 것임.

ReactDOM.render(
  <React.StrictMode>
    <App message="Hello Message"/> //message 추가!
  </React.StrictMode>,
  document.getElementById('root')
);
```
index.js 코드를 보면 message라는 변수를 App.js가 Props로 사용할 수 있게 전달해 주고 있습니다. 상속을 시켜준 것이죠. 이렇게 받은 message 라는 문자열을 App.js에서는 이렇게 사용할 수 있습니다.

#### index.js에서 설정한 속성 App.js으로 전달 받기 

```jsx
class App extends Component{
  state = {
    count : 0
  }

  countUp = () => {
    this.setState ({
      count : this.state.count + 1
    });
  }


  render(){
    return(
      <div className='App'>
        <h3>index Props</h3>
        <div className='props'>
          {/* {props가 들어가는 부분} */}
          <span>{this.props.message}</span>
        </div>

        <h3>State</h3>
        <div className='state'>
          {/* {state가 들어가는 부분} */}
          {this.state.count}
          <button onClick={this.countUp}>Click Me!</button>
        </div>
      </div>
    );
  }
}


export default App;
```

this.props.message, App.js에서는 state를 사용하듯이 사용하면 됩니다. 다만, Props는 State 처럼 **변경할 수 없는 점**을 꼭 기억해 주세요.  
하지만 Props를 절대로 변경할 수 없는 것은 아닙니다. App.js에 다른 컴포넌트를 상속시켜서 Props를 변경해 보도록하는 예제를 작성해 보겠습니다. App.js 파일을 다음과 같이 변경해 주세요.

#### App.js에 다른 컴포넌트를 상속시켜서 Props를 변경
```jsx
class App extends Component{
  state = {
    count : 0
  }

  countUp = () => {
    this.setState ({
      count : this.state.count + 1
    });
  }


  render(){
    return(
      <div className='App'>
        <h3>index Props</h3>
        <div className='props'>
          {/* {props가 들어가는 부분} */}
          <span>{this.props.message}</span>
        </div>

        <h3>State</h3>
        <div className='state'>
          {/* {state가 들어가는 부분} */}
          {this.state.count}
          <button onClick={this.countUp}>Click Me!</button>
        </div>

        <h3>App Props</h3>
        <div className="inside-app-props">
            <InsideApp
                count={this.state.count}
                countUp={this.countUp}
            />
        </div>
      </div>
    );
  }
}

class InsideApp extends Component {
    render(){
        return(
            <div>
             {this.props.count}
             <button onClick={this.props.countUp}>click ME!</button>
             </div>
        );
    }
}


export default App;
```

이 코드에 대해 설명해 보자면, 먼저 `InsideApp`이라는 컴포넌트를 하나 생성했습니다. 그리고 App.js에서 InsideApp 컴포넌트를 받아오고, 그 때 App 컴포넌트의 state와 메소드인 count 와 countUp을 상속시켜 주었습니다. 크롬을 실행하면 다음과 같은 html이 보이게 됩니다.

<div style="text-align:center" >
<img style="width:200px;" src="https://user-images.githubusercontent.com/86641773/155888670-f98fd1a1-e510-4b2c-87b8-1e730dd86dfe.png">
</div>

저 두개의 버튼 중 어떤 버튼을 클릭해도 State의 숫자와 AppProps의 숫자 모두 1씩 증가하게 됩니다. 이게 어떻게 가능한 일 일까요?

코드를 보겠습니다. 우리는 InsideApp컴포넌트에 App컴포넌트의 state인 count와 App컴포넌트의 메소드인 countUp 상속시켜 주었습니다. 그렇기 때문에, 두 개의 버튼 중 어떤 버튼을 클릭하던지 App컴포넌트의 state인 count가 증가하게 되어 상속받는 props인 count가 +1 되는 것 입니다.
Props를 직접적으로 변경이 불가능 하지만 이런식으로 상위 컴포넌트에서 state를 변경하는 메소드를 props로 끌어옴으로써 간접적으로 변경이 가능합니다.


출처 : https://berkbach.com/%EA%B8%B0%EC%B4%88%EB%B6%80%ED%84%B0-%EB%B0%B0%EC%9A%B0%EB%8A%94-react-part-5-77e997cf597