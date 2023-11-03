<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

<link rel="stylesheet" href="/css/index.css">
<script src="/js/index.js"></script>
<main>
	<div id="index">
		<div class="indexContainer">
			<div class="lectureListContainer">
				<div class="lectureListTitle">새로운 클래스</div>
				<div class="lectureList">
					<div class="swiper">
						<div class="swiper-wrapper">
							<c:forEach var="lecture" items="${newLectures}">
									<a class="lectureItem swiper-slide" href="/lecture/detail?id=${lecture.lectureId}">
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
							</c:forEach>
						</div>
						<div class="swiper-pagination"></div>
						<div class="swiper-button-prev"></div>
						<div class="swiper-button-next"></div>
						<div class="swiper-scrollbar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>