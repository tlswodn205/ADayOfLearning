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
  <link rel="stylesheet" href="/css/business/style.css">
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet" />
        
</head>
<body>
<div id= "businessPage">
	<sideBar>
		<div class="sideBar">
			<a href="/business">
				<img src="/images/logo/logo.png" class="logo">
			</a>
			<ul>
				<li>
					<a href="/business/userDetail"><i class="fa-solid fa-user"></i>내 정보보기</a>
					<a href="/business/chatRoom"><i class="fa-solid fa-user"></i>채팅</a>
					<a href="/business/lectureList"><i class="fa-solid fa-user"></i>강의 관리</a>
					<a href="/business/completedList"><i class="fa-solid fa-user"></i>완료된 강의</a>
				</li>
			</ul>
		</div>
	</sideBar>