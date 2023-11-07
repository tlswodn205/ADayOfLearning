<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/business/layout/header.jsp"%>
<link rel="stylesheet" href="/css/business/lectureList.css">

<main>
	<div class="mainTop">
		<div class="title">강의 관리</div>
	</div>
	<div id="lectureList" class="mainColumn">

		<div class="lectureTable">
			<c:choose>
				<c:when test="${lectureList != null}">
					<table>
						<tr>
							<th>강의일</th>
							<th>클래스명</th>
							<th>가격</th>
							<th>카테고리</th>
							<th>신청 인원</th>
							<th>상태</th>
							<th>등록일</th>
						</tr>
						<c:forEach var="list" items="${lectureList}">
							<tr>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${list.sessionDate}" /></td>
								<td><a
									href="/business/lectureDetail/${list.lectureSessionId}">${list.title}</a></td>
								<td>${list.price}</td>
								<td>${list.categoryName}</td>
								<td>${list.student}/${list.maximum}</td>
								<td>${list.state}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${list.createdAt}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div class="col">
						<p>생성된 클래스가 없습니다.</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

	</div>
</main>
<%@ include file="/WEB-INF/view/business/layout/footer.jsp"%>