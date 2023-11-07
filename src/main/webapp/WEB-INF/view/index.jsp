<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

<link rel="stylesheet" href="/css/index.css">
<script src="/js/index.js"></script>
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
			<br>
			<div class="lectureListContainer">
				<!-- 메인뷰 끝 -->
				<!-- 새로운 클래스 시작 -->
				<div class="lectureListTitle">새로운 클래스</div>
				<div class="lectureList">
					<div class="swiper lectureSwiper">
						<div class="swiper-wrapper">
							<c:forEach var="lecture" items="${newLectures}">
								<div class="swiper-slide">
									<a class="lectureItem" href="/lecture/detail?id=${lecture.lectureId}">
										<div class="lectureItemContainer">
											<div class="lectureItemPictureArea">
												<img class="lectureItemPicture">
												<div class="lectureItemAddress">${lecture.address}</div>
											</div>
											<div class="lectureItemCategory">${lecture.categoryName}</div>
											<div class="lectureItemProvider">${lecture.username}</div>
											<div class="lectureItemTitle">${lecture.title}</div>
											<div class="lectureItemPrice">${lecture.price}원</div>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
						<div class="swiper-pagination"></div>	
					</div>
				</div>
				<!-- 새로운 클래스 끝 -->
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>