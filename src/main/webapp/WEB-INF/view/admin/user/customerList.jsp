<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/admin/layout/header.jsp"%>
<main>
	<div class="mainTop">
		<div class="title">구매자 리스트</div>
	</div>
	<div id="userDetail" class="mainColumn">
		<div class="ListColumn">
			<div class="top-menu">
				<div>
					<input type="hidden" id="status"
						value="${listPagingResponseDto.status}"> <select id="type"
						name="type">
						<option value="name"
							${listPagingResponseDto.type eq "name" ? "selected":""}>이름</option>
					</select> <input type="text" id="keyword" placeholder="검색"
						value="${listPagingResponseDto.keyword}"> <input
						type="button" id="search-btn" value="검색하기">
				</div>
			</div>
			<table class="list-table">
				<thead>
					<tr>
						<th>아이디</th>
						<th>이름</th>
						<th>주소</th>
						<th>이메일</th>
						<th>전화번호</th>
						<th>생일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="customer" items="${listPagingResponseDto.list}">
							<td><a href="/admin/customerDetail/${customer.userId}">${customer.username}</a></td>
							<td>${customer.name}</td>
							<td>${customer.address}${customer.addressDetail}</td>
							<td>${customer.email}</td>
							<td>${customer.phoneNumber}</td>
							<td>${customer.birthday}</td>
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

  <script src="/js/business/search.js"></script>
<%@ include file="/WEB-INF/view/admin/layout/footer.jsp"%>