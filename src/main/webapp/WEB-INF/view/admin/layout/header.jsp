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
  <link rel="stylesheet" href="/css/admin/style.css">
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet" />    
  <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
</head>
<body>
<div id= "adminPage">
	<sideBar>
		<div class="sideBar">
			<a href="/admin/"><img src="/images/logo/logoAdmin.png" class="logo"></a>
			<ul>
				<li>
					<a href="/admin/customerList"><i class="fa-solid fa-user"></i>구매자 리스트</a>
				</li>
				<li>
					<a href="/admin/businessList"><i class="fa-solid fa-user-tie"></i>판매자 리스트</a>
				</li>
				<li>
					<a href="/admin/requestBusinessList"><i class="fa-solid fa-address-card"></i>판매자 요청</a>
				</li>
				<li>
					<a href="/admin/lectureList"><i class="fa-solid fa-chalkboard-user"></i>수업 리스트</a>
				</li>
			</ul>
		</div>
	</sideBar>