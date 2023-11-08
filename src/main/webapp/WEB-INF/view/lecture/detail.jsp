<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="/WEB-INF/view/layout/header.jsp"%>
<div id="lectureDetail">
    <div class="detailMainInfo">
        <div class="detailColumn1">
            <%-- 메인사진 --%>
            <div class="detailMainPhoto">
                <img />
            </div>
            <%-- 서브사진 --%>
            <div class="detailSubPhoto"></div>
            <%-- 강의 소개 머리 --%>
            <div class="detailASet"><a href="#detailContent">클래스 소개</a> <a href="#detailLocation">위치</a> <a href="#detailReview">후기</a></div>

            <div id="detailContent" class="detailArea content">
                <div class="detailHead content">
                    클래스 소개
                    <div class="detailHeadBottomLine"></div>
                </div>
                <div class="detailInfo content"></div>
            </div>

            <div id="detailLocation" class="detailArea location">
                <div class="detailHead location">
                    위치
                    <div class="detailHeadBottomLine"></div>
                </div>
                <div class="detailLectureAddress"></div>
                <div class="detailInfo location"></div>
            </div>

            <div id="detailReview" class="detailArea review">
                <div class="detailHead review">
                    후기
                    <div class="detailHeadBottomLine"></div>
                </div>
                <div class="detailInfo review">후기들 모음</div>
            </div>
        </div>

        <div class="detailColumn2">
            <div class="column2Container">
                <div class="detailLectureTitle"></div>
                <div class="detailReviewInfo">
                    <div class="detailReviewScore">
                        <i class="fa-solid fa-star" style="color: #f5d042"></i>
                    </div>
                    <div class="detailReviewAvr">(3.4)</div>
                </div>

                <%-- 강의 주소 --%>
                <div>
                    <i class="fa-solid fa-location-dot fa-sm"></i>
                    <div class="lectureDetatilRight address"></div>
                </div>

                <%-- 최대 강의 인원 --%>
                <div>
                    <i class="fa-solid fa-user fa-sm"></i>
                    <div class="lectureDetatilRight maximum"></div>
                </div>

                <%-- 강의 담당자 휴대폰번호 --%>
                <div>
                    <i class="fa-solid fa-phone fa-sm"></i>
                    <div class="lectureDetatilRight phone"></div>
                </div>

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
                    <div class="lectureDetatilRight price"></div>
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
    
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
