---
title:  "Url에 따라 바뀌는 Header만들기"
excerpt: "react 조건부 렌더링 enum 패턴 사용"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - react
tags:
  - react
  - basic
last_modified_at: 2022-03-23
---

## 목적 
Header 컴포넌트에서 요청된 페이지에 따라 이름을 바꾸고 싶었다.


## 처음 작성한 코드
```jsx
class Header extends Component {

    constructor(props) {
        super(props);
        
        this.state = {
            header : ''
        }

        switch (window.location.pathname) {
            case '/': this.state.header = 'DashBoard'; break;
            case '/aaa': this.state.header = 'A 페이지'; break;
            case '/bbb': this.state.header = 'B 페이지'; break;
            case '/ccc': this.state.header = 'C 페이지'; break;
        }

    }


    render() {
        return (
            <>
            <h4>{this.state.header}</h4>
            </>
        );
    }
}

export default Header;
```

switch case문으로 현재 머물러있는 페이지의 url을 받아 조건부 형식으로 header state를 세팅하는 것이었다. 
하지만 코드도 너무 중복..? 되보이기도 하고 뭔가 더 효율적인 방법을 찾아보다가 react에서 enum 처럼 쓸 수 있는 것을 발견함


## 수정된 코드
```jsx
import React, { Component } from 'react';
import $ from 'jquery';


const Header = () => {

        return (
            <>  
                <h4>{pages[url]}</h4>
            </>
        );
}

export default Header;

const url = window.location.pathname;

const pages = {
    '/' : 'DashBoard',
    '/aaa': 'A 페이지',
    '/bbb': 'B 페이지',
    '/ccc': 'C 페이지'
};

```

pages라는 객체를 만들어 그 안에 키, 값 형식으로 하고 값 부분에 원하는 페이지의 제목을 넣었다. 그리고 현재 페이지의 url을 키로 설정한 후 해당 제목을 렌더한다. 

이후에는 또 저 pages를 카테고리 별로 묶어서 header 제목 옆에 breadcrumb 처럼 구현해볼 예정이다. 

예를 들면 A~C 페이지 까지는 기본정보 로 묶어서 `HOME / 기본정보 / A 페이지` 처럼