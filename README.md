# 하루의 배움 프로젝트 A Day Of Learning Project
![logo2](https://github.com/tlswodn205/ADayOfLearning/assets/80434390/f304efe9-4f61-4aad-8a44-0381389be5f1)



</br>
</br>
</br>

## 1️⃣ 프로젝트 개요
우리의 아이디어는 하루만에 새로운 기술과 예술을 배울 수 있는 원데이 클래스 “하루의 배움”입니다.  하루라는 짧은 시간 동안에도 참여자들이 다양한 분야에서 전문적인 강의를 통해 새로운 기술과 예술을 체험하고 습득할 수 있는 특별한 경험을 제공하고자 합니다.

### 개발 기간
- 2023.10.23 - 2023.11.10 (15일)

### 팀 소개
- 모든 팀원이 풀스택으로 개발에 참여했으며, 주 포지션은 아래와 같습니다.

|Name|신재우|김민훈|박용세|최예정|
|------|---|---|---|---|
|Position|다음 주소 API<br>카카오 로그인<br>이메일 연동|강의 목록, 상세<br>강의 등록, 수정<br>강의 예약 개설|웹 소켓<br>실시간 1:1채 팅<br>채팅 알림<br>리뷰 작성|예약 및 결제 요청<br>결제 취소<br>매출 차트|

</br>
</br>
</br>

## 2️⃣ 기술 스택
### ✨ Front-End
- HTML
- CSS3
- JavaScript
- JQuery : 3.5.1
- chart.js

### 💻 Back-End
- spring boot : 2.7.15
- MySQL : 8.0.21
- jdk : Oracle 11
- Tomcat : 9
- lombok
- MyBatis
- JSP

### ⚙ API
- 카카오 소셜 로그인 API (OAuth 2.0 프로토콜)
- 카카오 지도 API
- DAUM 우편번호 찾기 API
- 나이스페이 API


</br>
</br>
</br>

## 3️⃣ ERD & 테이블 명세서
![Untitled](https://github.com/tlswodn205/ADayOfLearning/assets/80434390/8d5400ab-5e91-40bc-9c8f-fc16e3608707)


</br>
</br>
</br>

## 4️⃣ SiteMap
|사용자|판매자|관리자|
|---|---|---|
|![image](https://github.com/tlswodn205/ADayOfLearning/assets/113487440/b121cddf-27ab-487e-afbd-0605b8c9994a)|![image](https://github.com/tlswodn205/ADayOfLearning/assets/113487440/a26a7561-57fa-497e-a2bb-c1aede97e1c5)|![image](https://github.com/tlswodn205/ADayOfLearning/assets/113487440/ed819c3f-e8c1-4dc9-a0f4-e8cfaeebfd89)

</br>
</br>
</br>

## 5️⃣ 주요 기능 - 사용자
### 회원가입
- 아이디 중복 확인
- 비밀번호 확인
- Validation 처리
- Email 인증
- 주소 찾기 (DAUM 우편번호 찾기 API)
  
</br>

### 로그인
- 일반 로그인
- 아이디/비밀번호 찾기
- 카카오 로그인

### 판매자 등록 요청
- 이미지 업로드

</br>

|회원가입|로그인|
|---|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/5ea78427-2bd4-42fb-ab34-0ab35657edf3" width="475" height="300"/>|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/4e1d3020-da7d-4e44-8f5c-8d3571a5afcf" width="475" height="300"/>

|카카오로그인|판매자 등록 요청|
|---|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/bb45798d-49de-46b8-83f5-ffd96b98f234" width="475" height="300"/>|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/58e1f113-3e88-4503-8db1-fec66c4faa3e" width="475" height="300"/>

</br>
</br>

### 강의 리스트 검색
- 제목, 지역, 가격, 카테고리, 강의 일정으로 검색

</br>

### 강의 상세 정보 확인
- 강의 정보 확인
- KAKAO 지도 API
  
</br>

|강의 리스트, 검색| 강의 상세 정보 확인|
|---|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/4b3cca07-5e82-473f-9bb3-106780bf51e2" width="475" height="300"/>|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/92591ab9-2b05-4c30-84b8-c6674b1e7848" width="475" height="300"/>

</br>
</br>


### 선택한 강의 예약
- 예약 정보 확인
- 나이스 페이 API를 이용한 결제

</br>

### 예약 내역
- 예약 내용 조회 및 검색
- 예약 내용 상세보기
- 환불 요청

|강의 예약|환불 요청|
|---|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/ee06750c-0f48-43e7-9606-5829c89ac141" width="475" height="300"/>|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/19010fc2-ca95-4eaa-a8d2-b2d434d137e7" width="475" height="300"/>|


</br>
</br>

### 채팅
- 신규 채팅방 생성
- 실시간 채팅

### 우리지역 조회
- 지도에서 강의 조회
- 마커로 강의 상세보기 이동

</br>

|채팅|우리지역 조회|
|---|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/4d733036-7724-44d0-9ed4-0ebea98b3498" width="475" height="300"/>|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/997ca4c5-496d-4af7-8b9d-539ede06d904" width="475" height="300"/>|

</br>
</br>
</br>

## 6️⃣ 주요 기능 - 판매자


### 대시보드
- 차트 출력
- 판매자가 관리하는 데이터들 출력

</br>

|대시보드|
|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/500a3cf9-4ee4-4820-bf0b-46d27e92479a" width="475" height="300"/>|

</br>
</br>

### 판매자 상세보기
- 판매자 정보 보기
- 판매자 정보 수정
  
</br>

|판매자 상세보기|
|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/9b117282-9add-4fa7-a5fd-e4f9a1a140da" width="475" height="300"/>|


</br>
</br>

### 강의 등록
- 강의 정보 입력
- 강의 소개 (TinyEditor API)
- 주소 찾기 (DAUM 우편번호 찾기 API)
- 썸네일 사진과 일반 사진 등록

</br>

### 강의 수정
- 강의 정보 수정

</br>

### 강의 일정 등록
- 시간 지정

</br>

|강의 등록|강의 수정|
|---|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/d9e925ad-e813-4fa4-b295-bb44c75369fc" width="475" height="300"/>|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/2e54ddc5-7191-4d51-994b-f638db220193" width="475" height="300"/>|

|강의 일정 등록|
|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/41b26008-10f6-4ac5-8f8c-6d0834fa16ff" width="475" height="300"/>|

</br>
</br>

### 결제 상세보기
- 결제 상세보기
- 결제 환불 처리

</br>

|결제 상세보기|
|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/a9b77270-f123-4cdf-ae0f-da7441295632" width="475" height="300"/>|


</br>
</br>
</br>

## 7️⃣ 주요 기능 - 관리자

### 대시보드 조회  
- 그래프를 통한 동향 파악
- 판매자 권한 부여

</br>

|대시보드|
|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/001f033b-2e9e-48a1-bc47-c3d6be8959e3" width="475" height="300"/>|

</br>
</br>

### 구매자 리스트
- 구매자 리스트 조회 및 검색
- 구매자 리스트 페이징


</br>

### 구매자 상세보기
- 구매자 정보 보기
- 구매자 정보 수정 및 삭제

</br>
  
|구매자 리스트|구매자 상세보기|
|---|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/9785b1b1-459e-4607-abd0-072bf6bd8736" width="475" height="300"/>|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/aafecb10-f201-4e58-83b1-1938e8673b52" width="475" height="300"/>|

</br>
</br>


### 판매자 리스트
- 판매자 리스트 조회 및 검색
- 판매자 리스트 페이징

</br>

### 판매자 상세보기
- 판매자 정보 보기
- 판매자 정보 수정 및 삭제

</br>

|판매자 리스트|판매자 상세보기|
|---|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/95a309d8-4dff-4bd4-a147-9c29facc2292" width="475" height="300"/>|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/45a2ba5a-461d-4df7-b716-9531107f2ac3" width="475" height="300"/>|

</br>
</br>

### 판매자 요청 리스트
- 판매자 리스트 조회 및 검색
- 판매자 리스트 페이징

</br>

### 판매자 요청 상세보기
- 판매자 정보 보기
- 판매자 정보 수정 및 삭제

</br>

|판매자 요청 리스트|판매자 요청 상세보기|
|---|---|
|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/c58ebd66-8602-4d8e-a979-dc61393d6f2d" width="475" height="300"/>|<img src="https://github.com/tlswodn205/ADayOfLearning/assets/80434390/e05703df-c722-435d-a255-af293a887a12" width="475" height="300"/>|

</br>
</br>
