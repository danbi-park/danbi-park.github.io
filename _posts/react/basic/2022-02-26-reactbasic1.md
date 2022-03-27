---
title:  "React 기초 1"
excerpt: "Create-React-App 폴더의 파일들"
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

# Create-React-App 폴더의 파일들
CRA 의 폴더 구조 
가장 중요한 폴더인 public과 src 폴더를 살펴보겠습니다.
## public
가상 DOM을 사용하는 리액트는 실제 DOM이 필요합니다. 즉, 가상 DOM이 들어갈 빈 껍데기 html이 필요하다는 것인데, 바로 그 빈 껍데기가 존재하는 폴더입니다.
- favicon.ico: 페이지 아이콘 이미지 파일입니다.
- index.html: 가상 DOM이 들어가기 위한 빈 껍데기 html 파일입니다.
- manifest.json: 웹 앱 매니페스트는 사용자가 앱을 볼 것으로 예상되는 영역(예: 휴대기기 홈 화면)에 웹 앱이나 사이트를 나타내는 방식을 개발자가 제어하고, 사용자가 시작할 수 있는 항목을 지시하고, 시작 시의 모습을 정의할 수 있는 JSON 파일 입니다.
## src
리액트 개발이 이루어지는 메인 폴더입니다. 앞으로 우리는 src폴더를 주로 수정하고 src 폴더에 있는 파일들을 작성하는데 많은 시간을 보내게 될 것입니다.
- index.js 
```jsx
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

ReactDOM.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>,
    document.getElementById('root')
);

reportWebVitals();
```
이 코드는 App.js 에서 생성된 리액트 코드를 index.js에서 불러온 후, public에 있는 index.html 의 id가 root인 곳에다가 넣는 코드입니다.
- App.js
```jsx
import { Component } from 'react';
import './App.css';

class App extends Component{
    render(){
        return(
        <div className="App">
            <Subject title="WEB" sub="world wide web!!"></Subject>
            <Toc></Toc>
            <Content title="HTML" content="Html is a HyperText Markup Language."></Content>
        </div>
        );
        }
    }

    export default App;
```
이 코드는 리액트 코드를 생성하는 부분인데요.  
먼저 App이라는 클래스를 생성한 후, **리액트 컴포넌트**를 상속받습니다. 그렇게 되면 리액트 컴포넌트 메소드를 사용할 수가 있게 됩니다.  
**render() 메소드**는 리액트 컴포넌트인데, <u>화면에 html 뷰를 생성해주는 역할</u>을 합니다.  
return 으로 받는 값들은, 나중에 html 코드로 바뀌게 됩니다. 그렇게 생성된 App 클래스를 export 문법을 이용해서 내보냅니다.  
더 간단하게 말해보자면, 
1. 리액트, 리액트 컴포넌트를 불러옵니다.  
2. App 클래스를 만드는데, 그 클래스는 **리액트 컴포넌트를 상속**받습니다.
3. 상속받은 리액트 컴포넌트 메소드 중, **render() 메소드를 활용해서 html 코드를 작성해 return** 시켜줍니다.
4. 이렇게 작성된 리액트 코드를 `export` 시켜줍니다.

그런데 가만히 보면 index.js에서 App을 불러와 사용할 때에 html 코드를 사용하는 걸 (<App />) 볼 수 있는데, 모든 리액트 파일들은 전부 html 코드 처럼 사용할 수 있습니다. 이를 통해서 알 수 있는 것은 html 코드를 여러 개의 리액트 파일로 분할해서 작업을 할 수가 있다는 것 입니다. 또한 그렇게 되면 여러 사람이 협업을 하는 것도 쉬워지고, 코드의 수정도 특정 부분만 하면 되기 때문에 이점이 많아지게 됩니다.


출처 : https://berkbach.com/%EA%B8%B0%EC%B4%88%EB%B6%80%ED%84%B0-%EB%B0%B0%EC%9A%B0%EB%8A%94-react-part-3-a76a727447d3