<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/admin/layout/header.jsp"%>

<main>
	<div class="mainTop">
		<div class="title">강의 관리</div>
	</div>
	<div id="lectureList" class="mainColumn">
		<div class="ListColumn">
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>클래스명</th>
						<th>카테고리</th>
						<th>사업자</th>
						<th>위치</th>
						<th>가격</th>
						<th>상태</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="lectureList" items="${listPagingResponseDto.list}">
						<tr>
							<td>${lectureList.lectureId}</td>
							<td><a
								href="/admin/lectureDetail/${lectureList.lectureId}">${lectureList.title}</a></td>
							<td>${lectureList.businessName}</td>
							<td>${lectureList.address}{lectureList.addressDetail}</td>
							<td>${lectureList.price}</td>
							<td>${lectureList.categoryName}</td>
							<td>${lectureList.state}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lectureList.createdAt}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="d-flex justify-content-center">
				<ul class="pagination">
					<c:if test="${!listPagingResponseDto.first}">
						<li class='page-item'><a class="page-link"
							href="?page=${listPagingResponseDto.currentPage-1}${empty listPagingResponseDto.keyword ? "
							": "&keyword="+= listPagingResponseDto.keyword}${empty listPagingResponseDto.type ? "
							": "&type="+= listPagingResponseDto.type}${empty listPagingResponseDto.status ? "
							": "&status="+= listPagingResponseDto.status}">Prev</a></li>
					</c:if>

					<c:forEach var="num" begin="${listPagingResponseDto.startPageNum}"
						end="${listPagingResponseDto.lastPageNum}">
						<c:choose>
							<c:when test="${num==listPagingResponseDto.currentPage}">
								<li class='page-item'><label>${num}</label></li>
							</c:when>
							<c:otherwise>
								<li class='page-item'><a class='page-link'
									href="?page=${num}${empty listPagingResponseDto.keyword ? "
									": "&keyword="+= listPagingResponseDto.keyword}${empty listPagingResponseDto.type ? "
									": "&type="+= listPagingResponseDto.type}${empty listPagingResponseDto.status ? "
									": "&status="+= listPagingResponseDto.status}">${num}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${!listPagingResponseDto.last}">
						<li class='page-item'><a class="page-link"
							href="?page=${listPagingResponseDto.currentPage+1}${empty listPagingResponseDto.keyword ? "
							": "&keyword="+= listPagingResponseDto.keyword}${empty listPagingResponseDto.type ? "
							": "&type="+= listPagingResponseDto.type}${empty listPagingResponseDto.status ? "
							": "&status="+= listPagingResponseDto.status}">Next</a></li>
					</c:if>
				</ul>
			</div>
		</div>

	</div>
</main>
<%@ include file="/WEB-INF/view/admin/layout/footer.jsp"%>