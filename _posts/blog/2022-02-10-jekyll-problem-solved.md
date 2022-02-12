---
title: "jekyll오류 해결"
excerpt: "블로그를 개설하면서 마주친 다양한 오류 해결방법"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - blog
tags:
  - jekyll
  - github
  - blog
last_modified_at: 2022-02-10
---




우선 github블로그를 로컬환경에서 실행시키려면
실행할 폴더에서 오른쪽 마우스 클릭 후 Git Bash Here로 명령창을 켜주고   

<img width="328" alt="image" src="https://user-images.githubusercontent.com/86641773/153336602-84af3a07-5be7-4f81-9403-2eabb6e05243.png">

탐색창 주소창에 cmd하고 엔터 치면 해당 폴더경로로 커맨드 창이 실행된다.
![cmd](https://user-images.githubusercontent.com/86641773/153548801-7b551952-8162-4072-87ac-9c77aeedb44c.gif)

이후 `jekyll serve` 를 입력한다. 

## ✅ 정상 가동시!

`D:\폴더>bundle exec jekyll serve`  
또는  
`D:\폴더>jekyll serve`  
입력했을 때
```
Configuration file: D:/githubBlog/danbi-park.github.io/_config.yml
            Source: D:/githubBlog/danbi-park.github.io
       Destination: D:/githubBlog/danbi-park.github.io/_site
 Incremental build: disabled. Enable with --incremental
      Generating...
       Jekyll Feed: Generating feed for posts
                    done in 4.491 seconds.
  Please add the following to your Gemfile to avoid polling for changes:
    gem 'wdm', '>= 0.1.0' if Gem.win_platform?
 Auto-regeneration: enabled for 'D:/githubBlog/danbi-park.github.io'
    Server address: http://127.0.0.1:4000
  Server running... press ctrl-c to stop.
```

위처럼 끝에 Server address : ~~ :4000포트가 보인다면 잘 실행되는 것이다.

이렇게 로컬 환경으로 작업하다 보면 저장할 때마다 바로바로 적용이되어 보여진다. 



## ❌ 로컬 서버가 정상적으로 실행되지 않을 때 체크

### ❕해결 방법
- **명령어로 jekyll serve만 입력한 경우 (Gem:LoadError)**   
▷ 설치되어 있는 ruby 플러그인이 이 페이지를 불러오기 위한 플러그인 버전과 맞지 않아 실행에 실패하는 경우가 있다. 명령어 앞에 bundle exec를 추가해 주자.

- **불러오려는 파일과 터미널 인코딩이 맞지 않는 경우**  
▷ 윈도우 cmd의 경우, chcp 65001 명령어를 입력하여 명령 프롬프트를 utf-8 인코딩으로 바꿔 주자.

- **파일 경로에 한글이 포함되어 있는 경우 (SyntaxError)**  
▷ 한글이 포함되어 있지 않은 경로로 파일을 이동시켜 주자.

- **“Liquid Exception: Incompatible character encoding” 에러가 발생하는 경우**  
▷ 콘솔창의 코드 페이지를 UTF-8 로 바꿔준다.  
`D:\폴더> chcp 65001`  
콘솔창 화면이 Active code page: 65001로 넘어가면서
다시 jekyll을 구동시키면 정상적으로 동작함!

### `bundle exec`을 붙이는 이유
혹`jekyll serve`로 해도 똑같은 결과가 나오기도 합니다. 하지만 명령어 앞에 `bundle exec` 를 붙여 Bundler 가 <u>**당신의 프로젝트 폴더에 설치된 버전의 Jekyll 을 실행하도록**</u> 해야합니다.

여기서 bundle은 일종의 package manager인데, 간단하게는 Gemfile에 등록되어 있는 Gem에 맞춰서 패키지를 사용해 **의존성을 해결**해주는 것입니다.
즉, bundle exec를 사용한다면 Gemfile에 따라 jekyll을 실행하게 되죠.

*Jekyll은 Ruby로 개발되었으며 따라서 설치하기 위해서는 Gem을 사용해야 함.

정리하자면 Jekyll의 package manager는 bundle이고 이 bundle에 대한 의존성은 Gemfile에 정리되어 있다. 그렇기 때문에 내 프로젝트 폴더에 설치된 버전의 Jekyll을 실행하도록 하려면 bundle exec를 앞에 붙여 로컬환경을 실행한다.
 *이 bundle이 어떤 라이브러리에 의존하는지를 확인하고 싶다면, Gemfile을 참고하면 됩니다.



## ❌ Bundler could not find compatible versions for gem "bundler"

```text
D:\폴더>bundle exec jekyll serve
Bundler could not find compatible versions for gem "bundler":
  In Gemfile:
    bundler (~> 2.1.4)

  Current Bundler version:
    bundler (2.2.26)

Your bundle requires a different version of Bundler than the one you're running, and that version could not be found.
```

### ❕ 해결 방법
적절한 버전으로 설치하거나 업데이트 해준다. 
```
D:\폴더>gem install bundler:2.1.4  
D:\폴더>bundle _2.1.4_ update
```

## ❌ Jekyll 4.2.0 Please append --trace to the serve command
```
------------------------------------------------
Jekyll 4.2.0   Please append `--trace` to the `serve` command
    for any additional information or backtrace.
------------------------------------------------
```
### ❕ 해결 방법
해결 방법이라기 보단 추적하는 것임
```
D:\폴더>bundle exec jekyll serve --trace
```

## ❌ cannot load such file -- webrick
```
C:/Ruby30-x64/lib/ruby/gems/3.0.0/gems/jekyll-4.2.0/lib/jekyll/commands/serve/servlet.rb:3:in `require': cannot load such file -- webrick (LoadError)
```
### ❕해결 방법
```
D:\폴더> bundle add webrick
```

### ❕ gem 버전 업데이트
`D:\폴더>bundle exec jekyll serve`
다시 `build`

```
D:\폴더>jekyll build
D:\폴더>bundle add webrick
Fetching gem metadata from https://rubygems.org/..........
Fetching gem metadata from https://rubygems.org/.
Resolving dependencies...
Fetching gem metadata from https://rubygems.org/..........
Fetching gem metadata from https://rubygems.org/.
...생략
```



### 출처 
--- 
https://velog.io/@minji-o-j/jekyll-%EC%98%A4%EB%A5%98-%ED%95%B4%EA%B2%B0

https://jekyllrb-ko.github.io/tutorials/using-jekyll-with-bundler/

https://frhyme.github.io/blog/install_jekyll_again/