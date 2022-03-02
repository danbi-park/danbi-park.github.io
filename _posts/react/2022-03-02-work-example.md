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

간단한 흐름

먼저 TestService에서 axios로 들고올 Url을 설정한다.

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
```java
    @GetMapping("/demo/test")
    public List<Demo> getAllBoards() {
        return demoService.getAllBoard();
    }
```
Demo의 정보들을 JPA를 통해 가져온다. JSON방식으로 들고옴 

App.js
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