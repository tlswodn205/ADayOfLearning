<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include file="/WEB-INF/view/business/layout/header.jsp"%>
<link rel="stylesheet" href="/css/business/lectureRevise.css" />
<main>
    <div class="mainTop">
        <div class="title">강의 수정</div>
    </div>
    <div id="businessDetail" class="mainColumn">
        <form action="/business/revise" method="post" id="reviseForm" enctype="multipart/form-data">
            <input type="hidden" name="lectureId" value="${id}" />
            <div class="DetailColumn">
            <input type="hidden" id="reviseFormLongitude" name="longitude" value="${lecture.longitude}"/>
        	<input type="hidden" id="reviseFormLatitude" name="latitude" value="${lecture.latitude}"/>
        	<input type="hidden" name="state" value="${lecture.state}"/>
                <table>
                    <tr>
                        <td>강의 제목</td>
                        <td>
                            <input type="text" id="reviseFormTitle" name="title" placeholder="강의 제목" value="${lecture.title}" />
                        </td>
                    </tr>
                    <tr>
                        <td>강의 내용</td>
                        <td>
                            <textarea id="reviseFormContent" name="content">${lecture.content}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>강의 카테고리</td>
                        <td>
                            <select id="reviseCategory" name="categoryId" required="required" value="${lecture.categoryId}"></select>
                        </td>
                    </tr>
                    <tr>
                        <td>강의 시간(분)</td>
                        <td>
                            <input type="text" id="reviseFormDuration" name="duration" placeholder="강의 시간(분)" value="${lecture.duration}" />
                        </td>
                    </tr>
                    <tr>
                        <td>주소</td>
                        <td>
                            <div>
                                <input type="text" id="reviseFormAddress" name="address" placeholder="주소" readonly="readonly" value="${lecture.address}" />
                                <button type="button" id="reviseSearchAddress">주소검색</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>상세 주소</td>
                        <td>
                            <div>
                                <input type="text" id="reviseFormAddrDetail" name="addressDetail" placeholder="상세 주소" value="${lecture.addressDetail}" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>최대 수강 인원</td>
                        <td>
                            <div>
                                <input type="text" id="reviseFormMaximum" name="maximum" placeholder="최대 수강 인원" value="${lecture.maximum}" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>수강료</td>
                        <td>
                            <div>
                                <input type="text" id="reviseFormPrice" name="price" placeholder="수강료" value="${lecture.price}" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>연락처</td>
                        <td>
                            <div>
                                <input type="text" id="reviseFormPhoneNumber" name="phoneNumber" placeholder="연락처" value="${lecture.phoneNumber}" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>서비스</td>
                        <td>
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
                        </td>
                    </tr>
                    <tr>
                        <td>강의 썸네일 이미지</td>
                        <td>
                            <input type="file" id="reviseFormThumbnail" name="revisePhotoList[0].file" accept="image/*" multiple /> ※썸네일 이미지
                            <div id="thumbnailImageContainer"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>강의 이미지</td>
                        <td>
                            <input type="file" id="reviseFormFiles" name="file" accept="image/*" multiple />
                            <div id="imageContainer"></div>
                        </td>
                    </tr>
                </table>

                <input type="submit" id="reviseFormSubmit" />
            </div>
        </form>
    </div>

    <div id="layer" style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch">
        <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1" alt="닫기 버튼" />
    </div>
</main>

<script src="https://cdn.tiny.cloud/1/dtdpftxislwkjsqt3kyjkmgpi0r0p0cw5zmchcg1hwbvz9w9/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=af13f32c842ba98c342a15aeef40f5e4&libraries=services"></script>
<script src="/js/revise.js"></script>
<script type="text/javascript">
    let optionList = ${optionList};
    let photoList = ${photoList};
</script>

<%@ include file="/WEB-INF/view/business/layout/footer.jsp"%>
