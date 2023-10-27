<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>
<div id="lecture-detail">
	<div class="detail-main-info">
		<div class="detail-column1">
			<div class="detail-main-photo">
				<img alt="" src="">
			</div>
			<div class="detail-sub-photo">
				<div class="temp-photo"></div>
				<div class="temp-photo"></div>
				<div class="temp-photo"></div>
				<div class="temp-photo"></div>
				<div class="temp-photo"></div>
			</div>

			<div class="detail-button-set">
				<button>클래스 소개</button>
				<button>위치</button>
				<button>후기</button>
				<button>문의</button>
			</div>

			<div>
				<div>클래스 소개</div>
				<div>클래스 소개 내용</div>
			</div>

			<div>
				<div>위치</div>
				<div>지도랑 위치정보 나타냄</div>
			</div>

			<div>
				<div>후기</div>
				<div>후기들 모음</div>
			</div>

			<div>
				<div>문의</div>
				<div>문의들 모음</div>
			</div>

		</div>

		<div class="detail-column2">
			<div class="detail-lecture-title">집콕 메밀 경추베개 일자목 목디스크 거북목베개</div>
			<div class="datail-review-info">
				<div class="detail-review-score">★★★★★</div>
				<div class="detail-review-count">(321)</div>
			</div>
			<div class="detail-lecture-address">서울특별시 강서구 마곡동 735 상가동 102호</div>
			<div>총 1시간 30분</div>
			<div>최대 수용가능 인원 8명</div>
			<div>35,000원</div>
			<div>
				<input class="selected-date" type="hidden" name="date" />
				<table class="Calendar-detail">
					<thead>
						<tr>
							<td onClick="prevCalendar();" style="cursor: pointer">&#60;</td>
							<td colspan="5"><span class="calYear-detail"></span>년 <span class="calMonth-detail"></span>월</td>
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
			</div>
		</div>
	</div>
</div>
<script src="/js/lecturedetail.js"></script>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>