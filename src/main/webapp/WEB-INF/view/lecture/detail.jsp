<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<script src="https://cdn.tiny.cloud/1/dtdpftxislwkjsqt3kyjkmgpi0r0p0cw5zmchcg1hwbvz9w9/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
<div id="lectureDetail">
    <div class="detailMainInfo">
        <div class="detailColumn1">
            <div class="detailMainPhoto">
                <img />
            </div>

            <div class="detailSubPhoto"></div>

            <div class="detailASet">
                <a href="#detailContent">클래스 소개</a> <a href="#detailLocation">위치</a>
                <a href="#detailReview">후기</a>
            </div>

            <div id="detailContent" class="detailArea content">
                <div class="detailHead content">클래스 소개</div>
                <div class="detailInfo content"></div>
            </div>

            <div id="detailLocation" class="detailArea location">
                <div class="detailHead location">위치</div>
                <div class="detailInfo location"></div>
            </div>

            <div id="detailReview" class="detailArea review">
                <div class="detailHead review">후기</div>
                <div class="reviewInputContainer">
                	<div class="reviewInputTitle">
                		후기 작성
                		<div class="star-rating input">
							<input type="radio" id="5-starsInput" name="scoreInput" value="5"/>
							<label for="5-starsInput" class="star">★</label>
							<input type="radio" id="4-starsInput" name="scoreInput" value="4"/>
							<label for="4-starsInput" class="star">★</label>
							<input type="radio" id="3-starsInput" name="scoreInput" value="3" checked="checked" />
							<label for="3-starsInput" class="star">★</label>
							<input type="radio" id="2-starsInput" name="scoreInput" value="2"/>
							<label for="2-starsInput" class="star">★</label>
							<input type="radio" id="1-starsInput" name="scoreInput" value="1"/>
							<label for="1-starsInput" class="star">★</label>
						</div>
                	</div>
                	<div class="reviewInputBody">
		                <textarea class="reviewInput" id="reviewInput" name="content" placeholder="후기 내용"></textarea>
		                <input type="button" id="reviewInputBtn" value="후기 등록"/>
                	</div>
                </div>
                <div class="detailInfo review"></div>
            </div>
        </div>

        <div class="detailColumn2">
            <div class="column2Container">
                <div class="detailLectureTitle"></div>
                <div class="detailReviewInfo">
                    <div class="detailReviewScore">★★★★★</div>
                    <div class="detailReviewCount">(321)</div>
                </div>
                <div class="detailLectureAddress"></div>
                <div class="maximumStudents"></div>

                <div>
                    <input class="selectedDate" type="hidden" name="date" />
                    <table class="calendarDetail">
                        <thead>
                            <tr>
                                <td id="prevCalendar" style="cursor: pointer">&#60;</td>
                                <td colspan="5"><span class="calYearDetail"></span>년 <span class="calMonthDetail"></span>월</td>
                                <td id="nextCalendar" style="cursor: pointer">&#62;</td>
                            </tr>
                            <tr>
                                <td>일</td>
                                <td>월</td>
                                <td>화</td>
                                <td>수</td>
                                <td>목</td>
                                <td>금</td>
                                <td>토</td>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                    <div class="reserveArea">
                        <div class="dateReserve">개설된 클래스</div>
                        <div class="reserveList"></div>
                    </div>
                    <div class="detailPrice">35,000원</div>
                    <div class="paymentButtonSet">
                        <%--
                        <button id="inquiryBtn" onclick="location.href = '/chat/room/${lecture.userId}'">문의하기</button>
                        --%>
                        <button id="inquiryBtn">문의하기</button>
                        <button id="enroll">클래스 신청</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=af13f32c842ba98c342a15aeef40f5e4&libraries=services"></script>
<script src="/js/lecturedetail.js"></script>
<script type="text/javascript">
    let lectureData = ${lecture};
    let photoList = ${lecturePhotos};
    let reviewList = ${reviews};
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
