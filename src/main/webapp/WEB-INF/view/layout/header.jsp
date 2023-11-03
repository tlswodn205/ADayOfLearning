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
        
</head>
<body>
	<header>
		<div class="headerWrap">
			<img src="/images/logo/logo.png" class="logo">
			<ul class='topMenuRight'>
				<li>
					<a href="/lecture/list">수업보기</a>
				</li>
				<li>
					<a>새소식</a>
				</li>
				<li>
					<a>서비스소개</a>
				</li>
			</ul>			
			<ul class='topMenuLeft'>
				<li>
					<a class="fa-solid fa-comments" href="/chat/room"></a>
				</li>
				<li>
					<a class="fa-solid fa-user" href="/user/signIn"></a>
				</li>
			</ul>
		</div>
	</header>