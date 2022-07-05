---
title:  "nfs 연결하기"
excerpt: ""
toc: true
toc_sticky: true
toc_label: ""
categories:
  - linux
tags:
  - linux
last_modified_at: 2022-07-05
---


## 1. NFS 서버 설정(export) 

1) NFS관련 패키지 설치

```
# yum install nfs-utils
```
 

2) NFS서버 서비스 가동 및 재부팅 시 활성화

nfs서버 데몬을 시작하고, 재부팅시 **자동 실행** 될수 있도록 enable 명령을 통해 활성화 시킨다.
```
# systemctl start nfs-server
# systemctl enable nfs-server
```

3) exports 파일 설정

export할 디렉토리를 먼저 생성해준다.
```
# mkdir /nfsdir
```
그리고 exports 설정 파일을 연다.
```
# vi /etc/exports
```

안의 내용은 아래와 같이 적는다.
```
/nfsdir 192.168.0.000(rw,sync,no_subtree_check,sec=sys,secure,root_squash,no_all_squash)
```
/공유할 디렉토리 [허가할 호스트 대역] [디렉토리 권한]



4) 수정내용 반영
수정한 내용을 exportsfs 명령으로 반영한다.  
```
 # exportfs -r
```
###  bad option list error
```
exportfs: /etc/exports:1: syntax error: bad option list
```
디렉토리 권한에 띄어쓰기를 다 제거한다.

반영된 내용을 확인하려면? 
```
# exportfs -v
```


5) 서비스에 대해 방화벽 허가

firewalld가 실행중인 경우 nfs서비스가 사용될수 있도록 방화벽의 디폴트 존에 등록 해준다.
```
# firewall-cmd --permanent --add-service=nfs

# firewall-cmd --reload

# firewall-cmd --list-all
```
 

6) NFS공유 확인

아래 명령으로 NFS설정이 정상적으로 되었는지 확인한다.
```
# showmount -e
# exportfs -v
```
 


## 2. 클라이언트 마운트 설정 (import)

1) 패키지 설치

yum명령을 통해 nfs 패키지를 설치한다.
```
# yum -y install nfs-utils
```
 

2) export 지점확인

NFS서버에서 export 되고있는 지점을 확인한다.
```
# showmount -e 192.168.0.1
```
192.168.0.1 : nfs 서버 주소

 

3) 마운트할 디렉토리 생성

NFS클라이언트에 마운트 할위치의 디렉토리를 생성 한다.
```
# mkdir /nfsdir
```
 

4) 마운트

생성된 디렉토리에 NFS디렉토리를 마운트 한다.
```
# mount -t nfs -o sync 192.168.0.1:/nfsdir /nfsdir
```

mount type은 nfs [연결할 서버 IP와 폴더] [내 서버에서 연결해줄 폴더]
 

5) 마운트 확인

정상적으로 마운트 되었는지 확인한다.
```
# df -h | grep nfs
```

6) fstab 등록

fstab 파일에 마운트 정보를 등록하여 리부팅시 마운트 될 수 있도록 합니다.  
```
#vim /etc/fstab
```

