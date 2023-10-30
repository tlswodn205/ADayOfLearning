<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include
file="/WEB-INF/view/layout/header.jsp"%>

<div id="lecture-list">
    <form action="list" method="get" id="list-search-form">
        <input type="hidden" name="page" value="1" />
        <div class="lecturelist-search-area">
            <div class="lecturelist-row">
                <div>
                    <div>
                        <input type="text" name="title" class="form_title" placeholder="제목으로 검색" />
                    </div>
                    <label>지역</label>
                    <input type="text" class="form_location" name="location" readonly="readonly" value="전체" />
                    <button class="dropbtn" type="button">↓</button>
                    <div class="dropdown-content">
                        <div class="column m1"></div>
                        <div class="column m2"></div>
                    </div>
                </div>
                <div>
                    <label>카테고리</label
                    ><select name="category" class="form_category"></select>
                </div>
                <div>
                    가격 <input type="text" name="min_price" class="form_min_price" /> ~
                    <input type="text" name="max_price" class="form_max_price" />
                </div>
            </div>
            <div class="lecturelist-row">
                <input class="selected-date" type="hidden" name="date" />
                <table class="Calendar-list">
                    <thead>
                        <tr>
                            <td onClick="prevCalendar();" style="cursor: pointer">&#60;</td>
                            <td colspan="5">
                                <span class="calYear-list"></span>년 <span class="calMonth-list"></span>월
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
        <div class="search_form_submit_area">
            <button type="button" id="search-form-reset">초기화</button>
            <button type="button" id="search-form-submit">검색하기</button>
        </div>
    </form>

    <hr class="divider-x" />

    <div class="lecturelist-list"></div>
    <div class="lecturelist-pagenation-area">
        <div class="lecturelist-pagenation"></div>
    </div>
</div>
<script src="/js/lecture.js"></script>
<script type="text/javascript">
    pagenation(${page},${lectures});
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
