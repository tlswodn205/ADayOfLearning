/**
 * 
 */

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
    let selYear = $('#calYear').text();
    let selMonth = $('#calMonth').text();
    let setDate = $('.futureDay.choiceDay').text();

    //selected-date 인풋에 값 집어넣기 성공함
    $('.selected-date').val(`${selYear}-${selMonth}-${setDate}`);
    console.log($('.selected-date').val());
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