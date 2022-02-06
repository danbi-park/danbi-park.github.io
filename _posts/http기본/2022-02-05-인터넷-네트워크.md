---
title:  "인터넷 네트워크"
excerpt: "인터넷이 어떻게 통신하는지 알아보자(IP, TCP/UDP, PORT, DNS)"
toc: true
toc_sticky: true
toc_label: "목차"
categories:
  - HTTP
tags:
  - HTTP
  - 웹브라우저
  - 네트워크
  - 웹기본
last_modified_at: 2022-02-05T08:06:00-05:00
---


# 인터넷 통신 
인터넷 통신은 어떻게 할까?  
간단히  예를들어 여기 아래 클라이언트, 서버 두 개의 컴퓨터가 가까이 케이블에 연결되어 있다. 클라이언트에서 Hello, world라고 메세지를 보내니 서버에서 OK라고 연결된 케이블로 받게 된다. 
![image](https://user-images.githubusercontent.com/86641773/152686468-6391c927-badb-40d1-ac79-e82c3e1a9103.png)

만약에 메세지를 보내고 싶은 컴퓨터가 멀리~~ 있다면? "인터넷 망"을 통해 메세지를 보내야한다. 
![image](https://user-images.githubusercontent.com/86641773/152688415-533204d2-99c3-417d-acd0-808f6dd29cec.png)

하지만 인터넷은 굉장히 복잡하다. 어떻게 아래와 같이 수많은 중간 노드 서버를 거쳐서 안전하게 전달을 할 수 있을까?  
![image](https://user-images.githubusercontent.com/86641773/152686494-5085eb9f-3a6b-45e1-bcb4-98c7b1f562d1.png)


# IP(Internet Protocol, IP address)
먼저 우리집에서 친구집으로 택배를 보내듯이 나의 집주소, 친구의 집 주소가 필요하다.
네트워크 상에서 이 주소를 인터넷규약주소, IP 라고한다.

> IP는 컴퓨터 네트워크에서 장치들이 서로를 인식하고 통신을 하기 위해서 사용하는 특수한 번호이다.  


![image](https://user-images.githubusercontent.com/86641773/152686517-14be4c33-5da9-445f-8f34-89e1d493c738.png)


인터넷 프로토콜 역할
- 지정한 IP 주소(IP Address)에 데이터 전달
- 패킷(Packet)이라는 통신 단위로 데이터 전달
![image](https://user-images.githubusercontent.com/86641773/152686558-92fe11f1-671f-4757-a31e-7ee103c53ecb.png)

자 이제 클라이언트에서 출발지와 목적지를 입력한 패킷을 인터넷 망에 보낸다. 
- 클라이언트 패킷 전달
(요청)
![image](https://user-images.githubusercontent.com/86641773/152686573-39f6a1e8-48c9-45a3-851e-0463409589a4.png)

그럼 IP에 의해서 규약을 따르는 서버들이 이해를하며 노드끼리 통신을 하며 목적 주소로 도달하게 된다. 

전달 받은 서버는 똑같이 출발, 목적지 IP주소를 가지고 전달 받았다는 OK를 되돌려준다.
- 서버 패킷 전달(응답)
![image](https://user-images.githubusercontent.com/86641773/152688848-35599fa6-49ed-496c-9c7f-b540afc251b5.png)

하지만 IP만 가지고 통신을 하기엔 한계가 있다.
  
## IP 프로토콜의 한계
1. 비연결성
   - 패킷을 받을 대상이 없거나 서비스 불능 상태여도 패킷 전송
   ![image](https://user-images.githubusercontent.com/86641773/152687493-1b454dfd-88d6-40d8-b44e-e158f05a92b7.png)
2. 비신뢰성
     - 중간에 패킷이 사라질 경우
     ![image](https://user-images.githubusercontent.com/86641773/152687520-1d923a0c-84c5-47b1-acce-dd6fa943fd2a.png)
     -  패킷이 순서대로 오지 않을 경우
     ![image](https://user-images.githubusercontent.com/86641773/152687528-f4407602-b761-410f-8cac-c87f3d809e9f.png)
3. 프로그램 구분 ?
     - 같은 IP를 사용하는 서버에서 통신하는 애플리케이션이 둘 이상일 경우?

<br><br>

# TCP와 UDP

먼저 인터넷 프로토콜 스택의 4계층을 보면 IP 위에서 전송 계층인 TCP가 보완해준다고 생각하면 된다.  

![image](https://user-images.githubusercontent.com/86641773/152688996-8bc4b127-e50e-4d3c-b8ba-76826a9b38f3.png)    
<span style="font-size:15px;color:gray">인터넷 프로토콜 스택의 4계층</span>

아래 Hello, world라는 메세지를 전달하는 과정을 보자.  

![image](https://user-images.githubusercontent.com/86641773/152687595-aed3a342-a186-4906-803e-b16c7e3e9d6b.png)  
<span style="font-size:15px;color:gray">프로토콜 계층</span>

3번을 보면 운영체제에서 Hello,world 메세지에 TCP 정보를 하나 씌운다. 
그리고 3번처럼 TCP정보위에 또 IP패킷을 정보를 하나씌운다.  

그럼 3,4번은 아래와 같이 패킷이 생성이 된다.
![image](https://user-images.githubusercontent.com/86641773/152687633-97243a71-8ed3-446e-b85c-84b28fdebd1c.png)   
<span style="font-size:15px;color:gray">IP 패킷 정보와 TCP/IP 패킷 정보 </span>

## TCP 특징 
TCP는 Transmission Control Protocol로 **전송 제어 프로토콜**를 말합니다. 
- 연결지향 -> TCP 3 way handshake(가상연결)
 ![image](https://user-images.githubusercontent.com/86641773/152687647-6391221c-9465-44b9-a781-cae8f89dc509.png)  
  
- 데이터 전달 보증
![image](https://user-images.githubusercontent.com/86641773/152687662-2369c09a-d147-421b-839a-5a91c9b8f6d5.png)  
  
- 순서보장
![image](https://user-images.githubusercontent.com/86641773/152687668-ca3afba0-b2b1-47dc-a25b-d1d6714be242.png)    

위와 같은 특징으로 신뢰할 수 있는 프로토콜로서 현재 대부분 TCP를 사용합니다.

## UDP 특징
UDP는 User Datagram Protocol로 **사용자 데이터그램 프로토콜**를 말합니다. 
- 기능이 거의 없어 하얀 도화지에 비유를 할 수 있습니다. 
- 연결지향 -> TCP 3 way handshake(가상연결) ❌
- 데이터 전달 보증, 순서보장 ❌
- 하지만 단순하고 빠르다.
정리하자면 
- IP와 거의 같고, PORT와 체크섬 정도만 추가된 것입니다.
- 애플리케이션에서 추가 작업 필요

<br><br>

# PORT
우리는 컴퓨터를 할 때 딱 하나의 애플리케이션을 사용하지 않습니다.   
게임, 화상통화, 웹 브라우징 등 한 번에 둘 이상 연결해야하는 경우가 더 많죠.

![image](https://user-images.githubusercontent.com/86641773/152687697-68ccfd96-a838-44f6-98b5-4ff122526a25.png)


PORT는 같은 IP내에서 프로세스를 구분해주는 역할을 합니다. 

![image](https://user-images.githubusercontent.com/86641773/152687709-a6d0b944-0671-4433-8acb-ed5cb663ab7f.png)

우선 TCP/IP 패킷정보에 출발지, 목적지 IP뿐 만 아니라 출발지, 목적지 PORT에 대한 정보도 같이 전달하게 됩니다. 

![image](https://user-images.githubusercontent.com/86641773/152687806-2115ea05-b195-4fc5-abe3-65f8efc9dc0b.png)

- 0 ~ 65535 할당 가능
- 그 중 0 ~ 1023 범위내에는 잘 알려진 포트가 주로 사용되어 쓰지 않는 것이 좋습니다.
  - FTP : 20. 21
  - TELNET : 23
  - HTTP : 80
  - HTTPS : 443

<br><br>

# DNS(Domain Name System, DNS)
컴퓨터는 IP 주소로 다른 컴퓨터를 찾아가는데 문제가 없습니다. 그러나 우리들은 IP주소를 기억하기 어렵죠. 그래서 우리는 '도메인 이름' 이라고하는 사람이 읽을 수 있는 IP 주소의 이름을 지정할 수 있습니다. 예를 들어 'google.com'은 IP 주소로  '173.194.121.32' 입니다. 따라서 '도메인 이름'은 'IP주소'보다 인터넷을 사용하기에 쉽습니다.

에를 들어 DNS는 마치 전화번호부처럼 역할을 수행합니다. 

![image](https://user-images.githubusercontent.com/86641773/152687775-d46b3086-05b1-4e01-a8dc-c905f0491716.png)

DNS 서버에 도메인 명(google.com)으로 요청한다면 해당 도메인 명으로 등록된 IP주소(200.200.200.2???)로 응답하여 접속하는 것입니다.

<br><br>

출처  
[[하나몬]네트워크-기본-ip-주소와-포트-port](https://hanamon.kr/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EA%B8%B0%EB%B3%B8-ip-%EC%A3%BC%EC%86%8C%EC%99%80-%ED%8F%AC%ED%8A%B8-port/)  
[mozila](https://developer.mozilla.org/ko/docs/Learn/Common_questions/How_does_the_Internet_work)  
[기본기를 쌓는 정아마추어 코딩블로그](https://jeong-pro.tistory.com/178)