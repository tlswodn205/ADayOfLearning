<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include
file="/WEB-INF/view/layout/header.jsp"%>
<div id="lecture-detail">
    <div class="detail-main-info">
        <div class="detail-column1">
            <div class="detail-main-photo">
                <img />
            </div>

            <div class="detail-sub-photo"></div>

            <div class="detail-a-set">
                <a href="#deatail-content">클래스 소개</a> <a href="#deatail-location">위치</a>
                <a href="#deatail-review">후기</a>
            </div>

            <div id="deatail-content" class="detail-area content">
                <div class="detail-head content">클래스 소개</div>
                <div class="detail-info content"></div>
            </div>

            <div id="deatail-location" class="detail-area location">
                <div class="detail-head location">위치</div>
                <div class="detail-info location"></div>
            </div>

            <div id="deatail-review" class="detail-area review">
                <div class="detail-head review">후기</div>
                <div class="detail-info review">후기들 모음</div>
            </div>
        </div>

        <div class="detail-column2">
            <div class="column2-container">
                <div class="detail-lecture-title">집콕 메밀 경추베개 일자목 목디스크 거북목베개</div>
                <div class="datail-review-info">
                    <div class="detail-review-score">★★★★★</div>
                    <div class="detail-review-count">(321)</div>
                </div>
                <div class="detail-lecture-address">서울특별시 강서구 마곡동 735 상가동 102호</div>
                <div>총 1시간 30분</div>
                <div class="maximum-students">최대 수용가능 인원 8명</div>

                <div>
                    <input class="selected-date" type="hidden" name="date" />
                    <table class="Calendar-detail">
                        <thead>
                            <tr>
                                <td onClick="prevCalendar();" style="cursor: pointer">&#60;</td>
                                <td colspan="5">
                                    <span class="calYear-detail"></span>년 <span class="calMonth-detail"></span>월
                                </td>
                                <td onClick="nextCalendar();" style="cursor: pointer">&#62;</td>
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
                    <div class="reserve-area">
                        <div class="date-reserve">개설된 클래스</div>
                        <div class="reserve-list">
                            <div class="reserve-session"></div>
                            <div class="reserve-session"></div>
                            <div class="reserve-session"></div>
                        </div>
                    </div>
                    <div class="detail-price">35,000원</div>
                    <div class="payment-button-set">
                        <button>문의하기</button>
                        <button class="enroll">클래스 신청</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=af13f32c842ba98c342a15aeef40f5e4"></script>
<script src="/js/lecturedetail.js"></script>
<script type="text/javascript">
    showInfomation(${lecture}, ${lecturePhotos});
</script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
