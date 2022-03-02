---
title:  "실전예제"
excerpt: "simple flow"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - react
tags:
  - react
  - basic
last_modified_at: 2022-03-02
---

#### 요약
- 서버단에서 GetMapping으로 Url을 요청 받을 시 보여줄 정보를 연결.
- 리액트에서 Service 파일에 axios로 서버단에서 만든 Url 연결 및 메소드 생성
- 보여줄 컴포넌트 만들고 Service import해서 정보 꺼내기
- App.js에 Url과 컴포넌트 라우팅 연결


먼저 TestService에서 axios로 들고올 Url을 설정한다.

#### `TestService.js`
```jsx
import axios from "axios";

const API_BASE_URL = "http://localhost:8080/demo"

class TestService{
    getTests(){
        return axios.get(API_BASE_URL+"/test");
    }
    getApiStatus(){
        return axios.get(API_BASE_URL+"/api");
    }
}

export default new TestService();
```

그럼 axios가 "http://localhost:8080/demo" 을 통해 정보를 get해올 수 있다 그 중에 getTests라는 메소드에 "http://localhost:8080/demo/test"에서 정보를 들고오는 axios를 정의해준다. 

아래는 8080서버단의 controller에서 해당 매핑으로 들고오는 정보들이다. 
#### `DemoController.java`
```java
    @GetMapping("/demo/test")
    public List<Demo> getAllBoards() {
        return demoService.getAllBoard();
    }
```

Demo의 정보들을 JPA를 통해 가져온다. *JSON방식으로 들고옴 
이후 TestComponent를 만들어준다.

#### `TestComponent.jsx`
```jsx
import React, { Component } from 'react';
import TestService from '../service/TestService'; //1. 작성한 TestService를 import해준다. 

class TestComponent extends Component {
  //2. 먼저 생성자를 만들어준다. 
    constructor(props) {
        super(props);

  //3. boards라는 배열 변수를 만들어준다. 
        this.state = {
            boards : []
        }
    }

  //4. 컴포넌트가 렌더링 되기 전 3에서 만든 board변수에 getTests메소드로 들고 온 8080/demo/test정보들을 boards에 넣어준다.
    componentDidMount() {
        TestService.getTests().then((res) => {
            this.setState({ boards: res.data});
        });
    }
    
    render() {
        return (
            <div id="page-content-wrapper">
                <div className="container-fluid xyz">
                    <div className="row">
                        <div className="col-lg-12">
                            <h2 className="text-center">TestPage</h2>
                            <div className ="row">
                                <table className="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>Type </th>
                                            <th>% </th>
                                            <th>작성일 </th>
                                            <th>갱신일 </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    //표 안에 정보들을 map으로 꺼내준다. 
                                        { 
                                            this.state.boards.map(
                                                board => 
                                                <tr key = {board.id}>
                                                    <td> {board.id} </td>
                                                    <td> {board.type} </td>
                                                    <td> {board.storage} </td>
                                                    <td> {board.createdAt} </td>
                                                    <td> {board.updatedAt} </td>
                                                </tr>
                                            )
                                        }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default TestComponent;
```

이제 컴포넌트를 만들었으니 실제로 보여줄 곳에 이 컴포넌트를 넣어줄 것이다. 
#### `App.js`
```jsx
import './App.css';
import './js/main.js';
import {BrowserRouter as BrowserRouter, Routes, Route, Link} from 'react-router-dom'; 
import SideNavBarComponent from './components/SideNavBarComponent';
import ContentsComponent from './components/ContentsComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import TestComponent from './components/TestComponent';

function App() {

  return (
      <div> 
      <BrowserRouter>
        <HeaderComponent/> 
          <div>
            <div id="wrapper">
              <SideNavBarComponent/>
              <Routes>
                <Route path = "/testpage" element = {<TestComponent/>}/> 
                              //testPage라는 요청이 들어왔을 때 TestComponent를 보여줌.
              </Routes>
            </div>
          </div>
        <FooterComponent/>
      </BrowserRouter>
    </div>
  );
}

export default App;
```