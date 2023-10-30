/**
 *
 */

// 달력 그리는 부분 ===============================================
$(document).ready(function () {
    // 웹 페이지가 로드될 때 buildCalendar 함수 실행
    buildCalendar();
});

let nowMonth = new Date(); // 현재 달을 페이지를 로드한 날의 달로 초기화

// 달력 생성 함수: 해당 달에 맞춰 테이블을 만들고, 날짜를 채워 넣음
function buildCalendar() {
    let today = new Date();
    today.setHours(0, 0, 0, 0);

    let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1); // 이번달 1일
    let lastDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, 0); // 이번달 마지막날

    let $tbodyCalendar = $('.Calendar-detail > tbody');
    $('.calYear-detail').text(nowMonth.getFullYear()); // 연도 숫자 갱신
    $('.calMonth-detail').text(leftPad(nowMonth.getMonth() + 1)); // 월 숫자 갱신

    $tbodyCalendar.empty(); // 이전 결과 초기화

    let $nowRow = $('<tr>');

    for (let j = 0; j < firstDate.getDay(); j++) {
        $nowRow.append($('<td>'));
    }

    for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {
        let $nowColumn = $('<td>');
        let $newDIV = $('<p>').html(leftPad(nowDay.getDate()));
        $nowColumn.append($newDIV);

        if (nowDay < today) {
            $newDIV.addClass('pastDay'); // 지난 날인 경우
        } else if (nowDay.getTime() === today.getTime()) {
            $newDIV.addClass('today'); // 오늘인 경우
            $newDIV.click(function () {
                choiceDate(this);
            });
        } else {
            $newDIV.addClass('futureDay'); // 미래인 경우
            $newDIV.click(function () {
                choiceDate(this);
            });
        }

        $nowRow.append($nowColumn);

        if (nowDay.getDay() === 6 || nowDay.getTime() === lastDate.getTime()) {
            $tbodyCalendar.append($nowRow);
            $nowRow = $('<tr>'); // 새로운 행 추가
        }
    }
}

//<input class="selected-date" type="hidden" name="date" />요소 선택
let date = $('.selected-date');
// 날짜 선택 함수
function choiceDate(newDIV) {
    $('.choiceDay').removeClass('choiceDay'); // 기존에 선택한 날짜가 있으면 해당 날짜의 "choiceDay" class 제거
    $(newDIV).addClass('choiceDay'); // 선택된 날짜에 "choiceDay" class 추가
    let selYear = $('.calYear-detail').text();
    let selMonth = $('.calMonth-detail').text();
    let setDate = $('.futureDay.choiceDay').text();

    //selected-date 인풋에 값 집어넣기
    date.val(`${selYear}-${selMonth}-${setDate}`);
    console.log(date.val());
    $('.reserve-area').show();
    $('.date-reserve').text(date.val() + ' 개설된 클래스');
}

// 이전달 버튼 클릭
function prevCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() - 1, nowMonth.getDate()); // 현재 달을 1 감소
    buildCalendar(); // 달력 다시 생성
}

// 다음달 버튼 클릭
function nextCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, nowMonth.getDate()); // 현재 달을 1 증가
    buildCalendar(); // 달력 다시 생성
}

// input값이 한자리 숫자인 경우 앞에 '0' 붙여주는 함수
function leftPad(value) {
    if (value < 10) {
        value = '0' + value;
    }
    return value;
}

$('.futureDay, .today').click(function () {
    console.log(1);
    date.value($(this).text());
});
//===================================================

function showInfomation(lecture, photos) {
    $('.detail-info.content').append(lecture.content);
    $('.detail-lecture-address').text(lecture.address + ', ' + lecture.addressDetail);
    $('.detail-lecture-title').text(lecture.title);
    $('.maximum-students').text(`최대 수용가능 인원 ${lecture.maximum}명`);
    $('.detail-price').text(lecture.price.toLocaleString('ko-KR') + '원');

    console.log(photos.length);
    // 지도 ==========================
    let latitude = lecture.latitude;
    let longitude = lecture.longitude;
    $(document).ready(function () {
        let container = $('.detail-info.location')[0]; // '#map' 요소를 선택하고 [0]을 사용하여 DOM 요소로 변환
        let options = {
            center: new kakao.maps.LatLng(latitude, longitude),
            level: 3,
        };

        let map = new kakao.maps.Map(container, options);

        // 지도 타입 컨트롤을 생성합니다
        let mapTypeControl = new kakao.maps.MapTypeControl();

        // 지도에 컨트롤을 추가해야 지도 위에 표시됩니다
        // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
        map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

        // 지도 확대 축소를 제어할 수 있는 줌 컨트롤을 생성합니다
        let zoomControl = new kakao.maps.ZoomControl();
        map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

        // 마커가 표시될 위치입니다
        var markerPosition = new kakao.maps.LatLng(latitude, longitude);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: markerPosition,
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);
    });
    // ==========================

    // 사진 ==========================
    $('.detail-main-photo>img').attr('src', photos[0].img).attr('height', '500px').attr('width', '100%');
    let subPhotoArea = $('.detail-sub-photo');

    if (photos.length > 1) {
        for (var i = 0; i < photos.length; i++) {
            let photo = photos[i];
            // 새로운 <div> 요소를 생성하고 클래스를 추가
            let newDiv = $('<div>').addClass('sub-photo');

            // <img> 요소를 생성하고 src와 alt 속성을 설정
            let newImg = $('<img>').attr('src', photo.img).attr('height', '92px');

            // <img> 요소를 <div> 요소 내에 추가
            newDiv.append(newImg);

            // 생성한 요소를 문서에 추가
            $('.detail-sub-photo').append(newDiv);
        }
    }
}

// 스크롤 이벤트 리스너 : 아래로 내리면 오른쪽 결제창이 따라옴
window.addEventListener('scroll', function () {
    let scrollPosition = $(window).scrollTop();

    // 스크롤 위치가 0에서 60 범위에 있을 때
    if (scrollPosition >= 0 && scrollPosition <= 60) {
        $('.column2-container').css({
            position: 'static',
            top: 'auto',
        });
    }
    // 스크롤 위치가 61에서 72 범위에 있을 때
    else if (scrollPosition >= 61 && scrollPosition <= 72) {
        var topValue = scrollPosition - 60;
        $('.column2-container').css({
            position: 'fixed',
            top: topValue + 'px',
        });
    }
    // 스크롤 위치가 73 이상일 때
    else if (scrollPosition >= 73) {
        $('.column2-container').css({
            position: 'fixed',
            top: '12px',
        });
    }
});
// =======================

$(document).ready(function () {
    $('.sub-photo > img').on('click', function () {
        $('.detail-main-photo > img').attr('src', this.src);
    });
});
