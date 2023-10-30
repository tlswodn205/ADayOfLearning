// ======================================= 검색창 =======================================
// ======================== 드롭다운 ===============================
$('.dropbtn').click(function () {
    let dropdownContent = $('.dropdown-content');
    if (dropdownContent.css('display') === 'block') {
        dropdownContent.css('display', 'none');
    } else {
        dropdownContent.css('display', 'block');
    }
});

let list1 = [
    '전체',
    '서울',
    '부산',
    '대구',
    '인천',
    '광주',
    '대전',
    '울산',
    '세종',
    '경기',
    '강원',
    '충북',
    '충남',
    '전북',
    '전남',
    '경북',
    '경남',
    '제주',
];

const column1 = $('.column.m1');
const column2 = $('.column.m2');

list1.forEach((item, index) => {
    const ele = $('<div>').attr('href', '#').text(item).addClass('location-element');

    if (index % 2 == 0) {
        column1.append(ele);
    } else {
        column2.append(ele);
    }
});

const list2 = ['전체', '요리', '수공예', '미술', '플라워', '뷰티', '모임', '음악', '라이프스타일', 'DIY', '키즈'];
const category = $('.form_category');

list2.forEach((item) => {
    const option = $('<option>').attr('value', item).text(item);
    category.append(option);
});

// 드롭다운 메뉴 변경 이벤트 핸들러
$('.location-element').click(function () {
    // 선택한 항목의 텍스트 가져오기
    const selectedItemText = $(this).text();

    console.log(selectedItemText);
    // 선택한 항목의 값을 input 태그에 설정
    $('.form_location').val(selectedItemText);

    // 드롭다운 메뉴 숨기기
    $('.dropdown-content').css('display', 'none');
});

// 마우스 커서를 포인터로 변경
$('.location-element').css('cursor', 'pointer');
// ======================== 드롭다운 ===============================

// ============================ 달력 ===============================
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

    let $tbodyCalendar = $('.Calendar-list > tbody');
    $('.calYear-list').text(nowMonth.getFullYear()); // 연도 숫자 갱신
    $('.calMonth-list').text(leftPad(nowMonth.getMonth() + 1)); // 월 숫자 갱신

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
    let selYear = $('.calYear-list').text();
    let selMonth = $('.calMonth-list').text();
    let setDate = $('.futureDay.choiceDay').text();

    //selected-date 인풋에 값 집어넣기 성공함
    date.val(`${selYear}-${selMonth}-${setDate}`);
    console.log(date.val());
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

// ============================ 달력 ===============================

// ====================== 검색 버튼 ========================
$('#search-form-submit').on('click', function () {
    let url = 'list?page=1';
    console.log(1);
    // 타이틀
    if ($('.form_title').val() != '') {
        url += '&title=';
        url += $('.form_title').val();
    }

    // 지역
    if ($('.form_location').val() != '') {
        url += '&location=';
        url += $('.form_location').val();
    }

    // 카테고리
    if ($('.form_category').val() != '') {
        url += '&category=';
        url += $('.form_category').val();
    }

    // 최저가격
    if ($('.form_min_price').val() != '') {
        url += '&min_price=';
        url += $('.form_min_price').val();
    }

    // 최고가격
    if ($('.form_max_price').val() != '') {
        url += '&max_price=';
        url += $('.form_max_price').val();
    }

    // 날짜
    if ($('.selected-date').val() != '') {
        url += '&date=';
        url += $('.selected-date').val();
    }
    window.location.href = url;
});
// ====================== 검색 버튼 ========================

// ====================== 초기화 버튼 ========================
$('#search-form-reset').on('click', function () {
    let url = 'list?page=1&location=전체&category=전체';
    window.location.href = url;
});
// ====================== 초기화 버튼 ========================
// ======================================= 검색창 =======================================

// ============================ 강의 리스트 ===============================

let lecturelistList = $('.lecturelist-list');

// ============== 페이징 ==============
let pagenationArea = $('.lecturelist-pagenation');

function pagenation(page, list) {
    let lectureCount = list.length;
    let pageCount = Math.floor(lectureCount / 12);
    let remain = lectureCount % 12;

    let lastPage = pageCount;
    if (remain > 0) {
        lastPage = pageCount + 1;
    }

    let startLecture = 12 * (page - 1);
    let lastLecture = page * 12 - 1;

    let page1st;
    let page2nd;
    let page3rd;
    let page4th;
    let page5th;

    pageCount * 12;

    for (let i = startLecture; i <= lastLecture; i++) {
        if (list[i] != null) {
            let lectureItemA = $('<a>')
                .addClass('lecture-item-a')
                .attr('href', 'detail?id=' + list[i].lectureId);
            let lectureItem = $('<div>').addClass('lecture-item');
            let pictureDiv = $('<div>').addClass('lecture-item-picture-area');
            let pictureImg = $('<img>').addClass('lecture-item-picture').attr('src', list[i].img);
            let addressDiv = $('<div>').addClass('lecture-item-address').text(list[i].address);
            let categoryDiv = $('<div>').addClass('lecture-item-category').text(list[i].categoryName);
            let providerDiv = $('<div>').addClass('lecture-item-provider').text(list[i].username);
            let titleDiv = $('<div>').addClass('lecture-item-title').text(list[i].title);
            let priceDiv = $('<div>')
                .addClass('lecture-item-price')
                .text(list[i].price.toLocaleString('ko-KR') + '원');

            pictureDiv.append(pictureImg, addressDiv);
            lectureItem.append(pictureDiv, categoryDiv, providerDiv, titleDiv, priceDiv);

            lectureItemA.append(lectureItem);
            lecturelistList.append(lectureItemA);
        } else {
            break;
        }
    }
    var currentURL = window.location.href;
    var url = new URL(currentURL);

    url.searchParams.set('page', '1');

    console.log('현재페이지' + page);

    if (lectureCount != 0) {
        url.searchParams.set('page', page - 1);
        let prev = $('<a>').addClass('pagenation-element prev').attr('href', url.href).text('<');
        if (page <= 3) {
            console.log('경우의수 1번');
            url.searchParams.set('page', '1');
            page1st = $('<a>').addClass('pagenation-element').attr('href', url.href).text('1');
            url.searchParams.set('page', '2');
            page2nd = $('<a>').addClass('pagenation-element').attr('href', url.href).text('2');
            url.searchParams.set('page', '3');
            page3rd = $('<a>').addClass('pagenation-element').attr('href', url.href).text('3');
            url.searchParams.set('page', '4');
            page4th = $('<a>').addClass('pagenation-element').attr('href', url.href).text('4');
            url.searchParams.set('page', '5');
            page5th = $('<a>').addClass('pagenation-element').attr('href', url.href).text('5');
        } else if (page + 2 >= lastPage) {
            console.log('경우의수 2번');
            url.searchParams.set('page', lastPage - 4);
            page1st = $('<a>')
                .addClass('pagenation-element')
                .attr('href', url.href)
                .text(lastPage - 4);
            url.searchParams.set('page', lastPage - 3);
            page2nd = $('<a>')
                .addClass('pagenation-element')
                .attr('href', url.href)
                .text(lastPage - 3);
            url.searchParams.set('page', lastPage - 2);
            page3rd = $('<a>')
                .addClass('pagenation-element')
                .attr('href', url.href)
                .text(lastPage - 2);
            url.searchParams.set('page', lastPage - 1);
            page4th = $('<a>')
                .addClass('pagenation-element')
                .attr('href', url.href)
                .text(lastPage - 1);
            url.searchParams.set('page', lastPage);
            page5th = $('<a>').addClass('pagenation-element').attr('href', url.href).text(lastPage);
        } else {
            console.log('경우의수 3번');
            url.searchParams.set('page', page - 2);
            page1st = $('<a>')
                .addClass('pagenation-element')
                .attr('href', url.href)
                .text(page - 2);
            url.searchParams.set('page', page - 1);
            page2nd = $('<a>')
                .addClass('pagenation-element')
                .attr('href', url.href)
                .text(page - 1);
            url.searchParams.set('page', page);
            page3rd = $('<a>').addClass('pagenation-element').attr('href', url.href).text(page);
            url.searchParams.set('page', page + 1);
            page4th = $('<a>')
                .addClass('pagenation-element')
                .attr('href', url.href)
                .text(page + 1);
            url.searchParams.set('page', page + 2);
            page5th = $('<a>')
                .addClass('pagenation-element')
                .attr('href', url.href)
                .text(page + 2);
        }
        url.searchParams.set('page', page + 1);
        let next = $('<a>').addClass('pagenation-element next').attr('href', url.href).text('>');

        pagenationArea.append(prev, page1st, page2nd, page3rd, page4th, page5th, next);
        if (page == 1) {
            $(document).ready(function () {
                $('.pagenation-element.prev').attr('href', 'javascript:void(0);'); // href 속성 비우기
                $('.pagenation-element.prev').on('click', function (event) {
                    event.preventDefault(); // 클릭 이벤트 차단
                });
            });
        }

        if (page == lastPage) {
            $('.pagenation-element.next').attr('href', 'javascript:void(0);'); // href 속성 비우기
            $('.pagenation-element.next').on('click', function (event) {
                event.preventDefault(); // 클릭 이벤트 차단
            });
        }
    }
}

// ============== 페이징 ==============

// ============================ 강의 리스트 ===============================
