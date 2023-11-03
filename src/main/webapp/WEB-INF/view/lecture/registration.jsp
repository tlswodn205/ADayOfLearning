<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include
file="/WEB-INF/view/layout/header.jsp"%>
<div id="lectureRegistration">
    <form action="registration" method="post" id="registrationForm" enctype="multipart/form-data">
        <input type="text" id="registrationFormTitle" name="title" placeholder="강의 제목" />
        <textarea id="registrationFormContent" name="content">강의 내용</textarea>
        <select name="categoryId" required="required">
            <option value="1">요리</option>
            <option value="2">수공예</option>
            <option value="3">미술</option>
            <option value="4">플라워</option>
            <option value="5">뷰티</option>
            <option value="6">모임</option>
            <option value="7">음악</option>
            <option value="8">라이프스타일</option>
            <option value="9">DIY</option>
            <option value="10">키즈</option>
        </select>
        <input type="text" id="registrationFormDuration" name="duration" placeholder="수업 진행 시간(분)" />
        <input type="hidden" id="registrationFormLongitude" name="longitude" />
        <input type="hidden" id="registrationFormLatitude" name="latitude" />
        <div>
            <input type="text" id="registrationFormAddress" name="address" placeholder="주소" readonly="readonly"/>
            <button type="button" onclick="execDaumPostcode()">주소검색</button>
        </div>
        <input type="text" id="registrationFormAddrDetail" name="addressDetail" placeholder="상세 주소" />
        <input type="text" id="registrationFormMaximum" name="maximum" placeholder="최대 수강 인원" />
        <input type="text" id="registrationFormPrice" name="price" placeholder="수강료" />
        <input type="text" id="registrationFormPhoneNumber" name="phoneNumber" placeholder="연락처" />

        <div class="serviceCheckbox">
            <input type="checkbox" id="checkbox1" name="parkingSpaceAvailable" />
            <label for="checkbox1">주차공간 준비됨</label>
        </div>
        <div class="serviceCheckbox">
            <input type="checkbox" id="checkbox2" name="recordingProvided" />
            <label for="checkbox2">녹화본 제공</label>
        </div>
        <div class="serviceCheckbox">
            <input type="checkbox" id="checkbox3" name="materialsProvided" />
            <label for="checkbox3">준비물 제공</label>
        </div>
        <div class="serviceCheckbox">
            <input type="checkbox" id="checkbox4" name="kidsPlayAreaAvailable" />
            <label for="checkbox4">어린이 놀이구역 있음</label>
        </div>
        <div class="serviceCheckbox">
            <input type="checkbox" id="checkbox5" name="womenOnly" />
            <label for="checkbox5">여성회원들만</label>
        </div>
        <div class="serviceCheckbox">
            <input type="checkbox" id="checkbox6" name="menOnly" />
            <label for="checkbox6">남성회원들만</label>
        </div>
        <div class="serviceCheckbox">
            <input type="checkbox" id="checkbox7" name="noKidsZone" />
            <label for="checkbox7">노키즈존</label>
        </div>
        <input type="file" id="registrationFormThumbnail" name="file" accept=".jpg" multiple required="required"/> ※썸네일 이미지
        <input type="file" id="registrationFormFiles" name="files" accept=".jpg" multiple />

        <input type="submit" id="registrationFormSubmit" />
    </form>
</div>

<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>


<script src="https://cdn.tiny.cloud/1/dtdpftxislwkjsqt3kyjkmgpi0r0p0cw5zmchcg1hwbvz9w9/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=af13f32c842ba98c342a15aeef40f5e4&libraries=services"></script>
<script src="/js/registration.js"></script>

<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
