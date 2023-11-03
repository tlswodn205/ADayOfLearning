// 검색창
$('.dropBtn').click(function () {
    let dropdownContent = $('.dropdownContent');
    if (dropdownContent.css('display') === 'block') {
        dropdownContent.css('display', 'none');
    } else {
        dropdownContent.css('display', 'block');
    }
});

let list1 = ['전체', '서울', '부산', '대구', '인천', '광주', '대전', '울산', '세종', '경기', '강원', '충북', '충남', '전북', '전남', '경북', '경남', '제주'];

const column1 = $('.column.m1');
const column2 = $('.column.m2');

list1.forEach((item, index) => {
    const ele = $('<div>').attr('href', '#').text(item).addClass('locationElement');

    if (index % 2 === 0) {
        column1.append(ele);
    } else {
        column2.append(ele);
    }
});

const list2 = ['전체', '요리', '수공예', '미술', '플라워', '뷰티', '모임', '음악', '라이프스타일', 'DIY', '키즈'];
const category = $('.formCategory');

list2.forEach((item) => {
    const option = $('<option>').attr('value', item).text(item);
    category.append(option);
});

// 드롭다운 메뉴 변경 이벤트 핸들러
$('.locationElement').click(function () {
    // 선택한 항목의 텍스트 가져오기
    const selectedItemText = $(this).text();

    // 선택한 항목의 값을 input 태그에 설정
    $('.formLocation').val(selectedItemText);

    // 드롭다운 메뉴 숨기기
    $('.dropdownContent').css('display', 'none');
});

// 마우스 커서를 포인터로 변경
$('.locationElement').css('cursor', 'pointer');

// 달력
$(document).ready(function () {
    // 웹 페이지가 로드될 때 buildCalendar 함수 실행
    buildCalendar();
});

let nowMonth = new Date();

function buildCalendar() {
    let today = new Date();
    today.setHours(0, 0, 0, 0);

    let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1);
    let lastDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, 0);

    let $tbodyCalendar = $('.calendarList > tbody');
    $('.calYearList').text(nowMonth.getFullYear());
    $('.calMonthList').text(leftPad(nowMonth.getMonth() + 1));

    $tbodyCalendar.empty();

    let $nowRow = $('<tr>');

    for (let j = 0; j < firstDate.getDay(); j++) {
        $nowRow.append($('<td>'));
    }

    for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {
        let $nowColumn = $('<td>');
        let $newDiv = $('<p>').html(leftPad(nowDay.getDate()));
        $nowColumn.append($newDiv);

        if (nowDay < today) {
            $newDiv.addClass('pastDay');
        } else if (nowDay.getTime() === today.getTime()) {
            $newDiv.addClass('today');
            $newDiv.click(function () {
                choiceDate(this);
            });
        } else {
            $newDiv.addClass('futureDay');
            $newDiv.click(function () {
                choiceDate(this);
            });
        }

        $nowRow.append($nowColumn);

        if (nowDay.getDay() === 6 || nowDay.getTime() === lastDate.getTime()) {
            $tbodyCalendar.append($nowRow);
            $nowRow = $('<tr>');
        }
    }
}

// <input class="selectedDate" type="hidden" name="date" />요소 선택
let date = $('.selectedDate');

// 날짜 선택 함수
function choiceDate(newDiv) {
    $('.choiceDay').removeClass('choiceDay');
    $(newDiv).addClass('choiceDay');
    let selYear = $('.calYearList').text();
    let selMonth = $('.calMonthList').text();
    let setDate = $('.choiceDay').text();

    // selectedDate 인풋에 값 집어넣기
    date.val(`${selYear}-${selMonth}-${setDate}`);
    console.log(date.val());
}

// 이전달 버튼 클릭
function prevCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() - 1, nowMonth.getDate());
    buildCalendar();
}

// 다음달 버튼 클릭
function nextCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, nowMonth.getDate());
    buildCalendar();
}

// input 값이 한자리 숫자인 경우 앞에 '0' 붙여주는 함수
function leftPad(value) {
    if (value < 10) {
        value = '0' + value;
    }
    return value;
}

$('.futureDay, .today').click(function () {
    console.log(1);
    date.val($(this).text());
});

// 검색 버튼
$('#searchFormSubmit').on('click', function () {
    let url = 'list?page=1';
    console.log(1);

    // 타이틀
    if ($('.formTitle').val() !== '') {
        url += '&title=';
        url += $('.formTitle').val();
    }

    // 지역
    if ($('.formLocation').val() !== '') {
        url += '&location=';
        url += $('.formLocation').val();
    }

    // 카테고리
    if ($('.formCategory').val() !== '') {
        url += '&category=';
        url += $('.formCategory').val();
    }

    // 최저 가격
    if ($('.formMinPrice').val() !== '') {
        url += '&min_price=';
        url += $('.formMinPrice').val();
    }

    // 최고 가격
    if ($('.formMaxPrice').val() !== '') {
        url += '&max_price=';
        url += $('.formMaxPrice').val();
    }

    // 날짜
    if ($('.selectedDate').val() !== '') {
        url += '&date=';
        url += $('.selectedDate').val();
    }
    window.location.href = url;
});

// 초기화 버튼
$('#searchFormReset').on('click', function () {
    let url = 'list?page=1&location=전체&category=전체';
    window.location.href = url;
});

// 강의 리스트
let lectureList = $('.lectureListList');

// 페이징
let paginationArea = $('.lectureListPagination');

function pagination(page, list) {
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
        if (list[i] !== null) {
            let lectureItemA = $('<a>')
                .addClass('lectureItemA')
                .attr('href', 'detail?id=' + list[i].lectureId);
            let lectureItem = $('<div>').addClass('lectureItem');
            let pictureDiv = $('<div>').addClass('lectureItemPictureArea');
            let pictureImg = $('<img>').addClass('lectureItemPicture').attr('src', list[i].img);
            let addressDiv = $('<div>').addClass('lectureItemAddress').text(list[i].address);
            let categoryDiv = $('<div>').addClass('lectureItemCategory').text(list[i].categoryName);
            let providerDiv = $('<div>').addClass('lectureItemProvider').text(list[i].username);
            let titleDiv = $('<div>').addClass('lectureItemTitle').text(list[i].title);
            let priceDiv = $('<div>')
                .addClass('lectureItemPrice')
                .text(list[i].price.toLocaleString('ko-KR') + '원');

            pictureDiv.append(pictureImg, addressDiv);
            lectureItem.append(pictureDiv, categoryDiv, providerDiv, titleDiv, priceDiv);

            lectureItemA.append(lectureItem);
            lectureList.append(lectureItemA);
        } else {
            break;
        }
    }
    var currentURL = window.location.href;
    var url = new URL(currentURL);

    url.searchParams.set('page', '1');

    console.log('현재페이지' + page);

    if (lectureCount !== 0) {
        url.searchParams.set('page', page - 1);
        let prev = $('<a>').addClass('paginationElement prev').attr('href', url.href).text('<');
        if (page <= 3) {
            console.log('경우의 수 1번');
            url.searchParams.set('page', '1');
            page1st = $('<a>').addClass('paginationElement').attr('href', url.href).text('1');
            url.searchParams.set('page', '2');
            page2nd = $('<a>').addClass('paginationElement').attr('href', url.href).text('2');
            url.searchParams.set('page', '3');
            page3rd = $('<a>').addClass('paginationElement').attr('href', url.href).text('3');
            url.searchParams.set('page', '4');
            page4th = $('<a>').addClass('paginationElement').attr('href', url.href).text('4');
            url.searchParams.set('page', '5');
            page5th = $('<a>').addClass('paginationElement').attr('href', url.href).text('5');
        } else if (page + 2 >= lastPage) {
            console.log('경우의 수 2번');
            url.searchParams.set('page', lastPage - 4);
            page1st = $('<a>')
                .addClass('paginationElement')
                .attr('href', url.href)
                .text(lastPage - 4);
            url.searchParams.set('page', lastPage - 3);
            page2nd = $('<a>')
                .addClass('paginationElement')
                .attr('href', url.href)
                .text(lastPage - 3);
            url.searchParams.set('page', lastPage - 2);
            page3rd = $('<a>')
                .addClass('paginationElement')
                .attr('href', url.href)
                .text(lastPage - 2);
            url.searchParams.set('page', lastPage - 1);
            page4th = $('<a>')
                .addClass('paginationElement')
                .attr('href', url.href)
                .text(lastPage - 1);
            url.searchParams.set('page', lastPage);
            page5th = $('<a>').addClass('paginationElement').attr('href', url.href).text(lastPage);
        } else {
            console.log('경우의 수 3번');
            url.searchParams.set('page', page - 2);
            page1st = $('<a>')
                .addClass('paginationElement')
                .attr('href', url.href)
                .text(page - 2);
            url.searchParams.set('page', page - 1);
            page2nd = $('<a>')
                .addClass('paginationElement')
                .attr('href', url.href)
                .text(page - 1);
            url.searchParams.set('page', page);
            page3rd = $('<a>').addClass('paginationElement').attr('href', url.href).text(page);
            url.searchParams.set('page', page + 1);
            page4th = $('<a>')
                .addClass('paginationElement')
                .attr('href', url.href)
                .text(page + 1);
            url.searchParams.set('page', page + 2);
            page5th = $('<a>')
                .addClass('paginationElement')
                .attr('href', url.href)
                .text(page + 2);
        }
        url.searchParams.set('page', page + 1);
        let next = $('<a>').addClass('paginationElement next').attr('href', url.href).text('>');

        paginationArea.append(prev, page1st, page2nd, page3rd, page4th, page5th, next);
        if (page == 1) {
            $(document).ready(function () {
                $('.paginationElement.prev').attr('href', 'javascript:void(0);'); // href 속성 비우기
                $('.paginationElement.prev').on('click', function (event) {
                    event.preventDefault(); // 클릭 이벤트 차단
                });
            });
        }

        if (page == lastPage) {
            $('.paginationElement.next').attr('href', 'javascript:void(0);'); // href 속성 비우기
            $('.paginationElement.next').on('click', function (event) {
                event.preventDefault(); // 클릭 이벤트 차단
            });
        }
    }
}
