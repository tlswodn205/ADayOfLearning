<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

<link rel="stylesheet" href="/css/index.css">
<main>
	<div id="index">
		<div class="indexContainer">
			<!-- 메인뷰 시작 -->
			<div class="mainViewContainer">
				<div class="swiper bannerSwiper">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<img class="mainBanner" src="/images/bannerImages/banner-1018815_1280.jpg">
						</div>
						<div class="swiper-slide">
							<img class="mainBanner" src="/images/bannerImages/banner-1018816_1280.jpg">
						</div>
					</div>
					<div class="swiper-pagination"></div>	
				</div>
			</div>
			<!-- 메인뷰 끝 -->
			<br>
			<!-- 새로운 클래스 시작 -->
			<div class="lectureListContainer">
				<div class="lectureListTitle">새로운 클래스</div>
				<div class="lectureList">
					<div class="swiper lectureSwiper">
						<div class="swiper-wrapper" id="swiper-wrapper"></div>
						<div class="swiper-pagination"></div>	
					</div>
				</div>
				<!-- 새로운 클래스 끝 -->
			</div>
		</div>
	</div>
</main>
<script src="/js/lectureItem.js"></script>
<script src="/js/index.js"></script>
<script>
	let lectureList = ${newLectures};
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>