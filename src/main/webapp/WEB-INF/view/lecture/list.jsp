<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include
file="/WEB-INF/view/layout/header.jsp"%>

<div id="lectureList">
    <form action="list" method="get" id="listSearchForm">       
    <input type="hidden" name="page" value="1" />
        <div class="lectureListSearchArea">
            <div class="lectureListRow">
                <div class="inputDiv">
	                <div class="inputTitle">제목</div>
					<div class="textDiv">
                    	<i class="fa-solid fa-tablet-screen-button"></i>
                        <input type="text" name="title" class="formTitle" placeholder="제목으로 검색" />
                    </div>
            	</div>
                <div class="inputDiv">
	                <div class="inputTitle">지역</div>
                    <div class="textDiv">
	                    <input type="text" class="formLocation" name="location" readonly="readonly" value="전체" />
                    	<button class="dropBtn" type="button"><i class="fa-solid fa-chevron-down"></i></button>
                    </div>
                </div>
                <div class="dropdownContent">
                    <div class="column m1"></div>
                    <div class="column m2"></div>
                </div>
                <div class="inputDiv">
	                <div class="inputTitle">카테고리</div>
                    <div class="textDiv">
	                	<input type="text" class="formCategory" name="category" readonly="readonly" value="전체" />
	                	<button class="dropCategoryBtn" type="button"><i class="fa-solid fa-chevron-down"></i></button>
	                </div>
	            </div>
	            
                <div class="dropdownCategory">
                    <div class="column ca"></div>
                </div>
	                
                <div class="inputDiv">
                 	<div class="inputTitle">최솟값</div>
                 	<div class ="textDiv">
						<i class="fa-solid fa-won-sign"></i>
                  		<input type="text" name="minPrice" class="formMinPrice" placeholder="최솟값" />
                 	</div>
            	</div>
                <div class="inputDiv">
                 	<div class="inputTitle">최댓값</div>
                  	<div class="textDiv">
						<i class="fa-solid fa-won-sign"></i>
                  		<input type="text" name="maxPrice" class="formMaxPrice" placeholder="최댓값" />
                 	</div>
            	</div>
            </div>
            <div class="lectureListRow">
                <input class="selectedDate" type="hidden" name="date" />
                <table class="calendarList">
                    <thead>
                        <tr>
                            <td onClick="prevCalendar();" style="cursor: pointer">&#60;</td>
                            <td colspan="5">
                                <span class="calYearList"></span>년 <span class="calMonthList"></span>월
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
            </div>
        </div>
        <div class="searchFormSubmitArea">
            <button type="button" class="searchFormReset" id="searchFormReset"><i class="fa-solid fa-rotate-right"></i> 초기화</button>
            <button type="button" class="searchFormSubmit" id="searchFormSubmit"><i class="fa-solid fa-magnifying-glass"></i> 검색하기</button>
        </div>
    </form>

    <hr class="dividerX" />

    <div class="lectureListList"></div>
    <div class="lectureListPaginationArea">
        <div class="lectureListPagination"></div>
    </div>
</div>

<script src="/js/lecturelist.js"></script>
<script type="text/javascript">
	let currentPage = ${page};
	let lectureListData = ${lectures};
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
