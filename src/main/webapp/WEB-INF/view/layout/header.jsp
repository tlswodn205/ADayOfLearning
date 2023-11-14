<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://kit.fontawesome.com/e8f010a863.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="/css/style.css">
  <link rel="stylesheet" href="/css/lecture.css" type="text/css" />
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet" />
  <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
        
</head>
<body>
	<header>
		<div class="headerWrap">
			<a href="/">
				<img src="/images/logo/logo.png" class="logo">
			</a>
			<ul class='topMenuRight'>
				<li>
					<a href="/lecture/list">수업보기</a>
				</li>
				<li>
					<a href="/map">지도</a>
				</li>
				<c:if test="${principal ne null}"> 
					<li>
						<a href="/chat/room">채팅</a>
					</li>
					
					<li>
						<a href="/reserve/list">구매내역</a>
					</li>
					<li>
						<a href="/user/businessRequest">판매자 신청</a>
					</li>
				</c:if>
			</ul>			
			<ul class='topMenuLeft'>
			
	        <c:choose>
	            <c:when test="${principal ne null}"> 
					<li>
						<a class="yellowButton" href="/user/userMyPage">마이페이지</a>
					</li>
					<li>
						<a class="redButton" href="/user/signOut">로그아웃</a>
					</li>
				</c:when>
				<c:otherwise> 
					<li>
						<a class="yellowButton" href="/user/signIn">로그인</a>
					</li>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
	</header>