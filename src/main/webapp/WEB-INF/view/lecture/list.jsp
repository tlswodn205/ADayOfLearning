<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include
file="/WEB-INF/view/layout/header.jsp"%>

<div id="lectureList">
    <form action="list" method="get" id="listSearchForm">
        <input type="hidden" name="page" value="1" />
        <div class="lectureListSearchArea">
            <div class="lectureListRow">
                <div>
                    <div>
                        <input type="text" name="title" class="formTitle" placeholder="제목으로 검색" />
                    </div>
                    <label>지역</label>
                    <input type="text" class="formLocation" name="location" readonly="readonly" value="전체" />
                    <button class="dropBtn" type="button">↓</button>
                    <div class="dropdownContent">
                        <div class="column m1"></div>
                        <div class="column m2"></div>
                    </div>
                </div>
                <div>
                    <label>카테고리</label>
                    <select name="category" class="formCategory"></select>
                </div>
                <div>
                    가격 <input type="text" name="minPrice" class="formMinPrice" /> ~
                    <input type="text" name="maxPrice" class="formMaxPrice" />
                </div>
            </div>
            <div class="lectureListRow">
                <input class="selectedDate" type="hidden" name="date" />
                <table class="calendarList">
                    <thead>
                        <tr>
                            <td id="prevCalendar" style="cursor: pointer">&#60;</td>
                            <td colspan="5">
                                <span class="calYearList"></span>년 <span class="calMonthList"></span>월
                            </td>
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
            </div>
        </div>
        <div class="searchFormSubmitArea">
            <button type="button" id="searchFormReset">초기화</button>
            <button type="button" id="searchFormSubmit">검색하기</button>
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
