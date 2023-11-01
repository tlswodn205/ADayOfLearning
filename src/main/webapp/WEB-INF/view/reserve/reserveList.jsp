<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<link rel="stylesheet" href="/css/reserveList.css">

<main>
	<div id="reserveList">
		<div class="title">
			<h1>예약 내역</h1>
		</div>
		<div class="content">
			<div class="search">
				<select id="type" class="searchSelect" aria-label="Default select example">
					<option ${reserveList.type == 'title' ? 'selected':''}	value="title">클래스명</option>
					<option ${reserveList.type == 'content' ? 'selected':''} value="content">판매자명</option>
				</select>
				<input id="keyword" value="${reserveList.keyword}" type="text" placeholder="검색">
			</div>

			<c:choose>
				<c:when test="${reserveList != null}">
					<c:forEach var="list" items="${reserveList.list}">
						<div class="listBox">
							<a href="/reserve/detail/${list.reserveId}">
								<div>
									<h3>${list.state}</h3>
								</div>
								<div class="listItem">
									<img alt="" src="https://picsum.photos/120/120">
									<div class="listContent">
										<span>${list.paymentDate}</span>
										<h4>${list.title}</h4>
										<p>${list.sessionDate}</p>
										<strong>${list.amount}</strong>
									</div>
								</div>
							</a>
							<div class="listBtnBox">
								<button type="button" class="listBtn" id="reviewBtn">후기 작성하기</button>
								<button type="button" class="listBtn" id="chatBtn">문의하기</button>
							</div>
						</div>
					</c:forEach>
					<div class="d-flex justify-content-center">
						<ul class="pagination">
							<c:if test="${!reserveList.first}">
								<li class='page-item'>
									<a class="page-link" href="?page=${reserveList.currentPage-1}${empty reserveList.keyword ? "": "&keyword="+= reserveList.keyword}${empty reserveList.type ? "": "&type="+= reserveList.type}${empty reserveList.status ? "": "&status="+= reserveList.status}">Prev</a>
								</li>
							</c:if> 

							<c:forEach var ="num" begin = "${reserveList.startPageNum}" end="${reserveList.lastPageNum}">
									<c:choose>
										<c:when test="${num==reserveList.currentPage}">
											<li class='page-item'><label>${num}</label></li>
										</c:when>
										<c:otherwise>	
											<li class='page-item'><a class='page-link' href="?page=${num}${empty reserveList.keyword ? "": "&keyword="+= reserveList.keyword}${empty reserveList.type ? "": "&type="+= reserveList.type}${empty reserveList.status ? "": "&status="+= reserveList.status}">${num}</a></li>
										</c:otherwise> 
									</c:choose>
							</c:forEach>
							
							<c:if test="${!reserveList.last}">	
								<li class='page-item'>
									<a class="page-link" href="?page=${reserveList.currentPage+1}${empty reserveList.keyword ? "": "&keyword="+= reserveList.keyword}${empty reserveList.type ? "": "&type="+= reserveList.type}${empty reserveList.status ? "": "&status="+= reserveList.status}">Next</a>
								</li>
							</c:if>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col">
						<p>예약 내역이 없습니다.</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>