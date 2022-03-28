---
title:  "axios로 form 데이터 보내기, + 로그인 시 입력 받을 때 마다 저장시키는 함수"
excerpt: "axios로 스프링 시큐리티 formLogin()에 입력값 보내기"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - react
tags:
  - react
  - basic
last_modified_at: 2022-03-28
---


## Spring Security 설정
```java
    .formLogin()
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/")
        .failureUrl("/login?error=true")
```
로그인을 위해 spring security를 사용하고 있는데, 얘는 데이터를 가져올 때 `formLogin()`으로 정보를 들고와 찾기 때문에 form, 그리고 post 방식으로 보내주면 된다.
리액트에서는 axios를 사용해 form데이터를 보내보자.

```javascript
import React, { Component, useState } from 'react';
import TestService from '../service/TestService';
import axios from 'axios';


const LoginPage = () => {

    //inputId, inputPw 선언, 초기화
    let [inputId, setInputId] = useState('');
    let [inputPw, setInputPw] = useState('');
 
	// input data 의 변화가 있을 때마다 value 값을 변경해서 useState 해준다
    let handleInputId = (e) => {
        setInputId(e.target.value)
    }
 
    let handleInputPw = (e) => {
        setInputPw(e.target.value)
    }

    let axios_post = () => {
        let form = new FormData();
        form.append('username', inputId);
        form.append('password', inputPw);

        axios.post("/login", form, Credential=true)
        .then((response)=> {
            console.log('response : ', JSON.stringify(response, null, 2))
        })
        .catch((error)=> {
            console.log("failed : " + error)
        })
    }

    return(
            <>
            <div>
                <h2>Login</h2>
                    <div>
                        <label htmlFor='username'>ID : </label>
                        <input type='text' name='username' value={inputId} onChange={handleInputId}/>
                    </div>
                    <div>
                        <label htmlFor='password'>PW : </label>
                        <input
                            type='password'
                            name='password'
                            value={inputPw}
                            onChange={handleInputPw}/>
                    </div>
                    <div>
                        <button type='button' onClick={axios_post} >Login</button>
                    </div>
                </div>
            </>
        );
}

export default LoginPage;
```


`FormData()`를 사용하여 `form.append('name', 저장된 변수);`

new FormData()로 새로운 객체를 생성해주고 append 메소드로 키-값 형식으로 하나씩 추가해준다. 같은 키를 가진 값을 여러 개 넣을 수도 있다. 덮어씌워지지 않고 추가된다. 참고로 값은 문자열로 자동 변환된다. 숫자를 넣어도 문자열이 되고, 배열을 넣어도 콤마로 구분한 문자열이 됩니다. 객체는 넣으면 무시됩니다. 이 점을 유의하세요!