// <input class="selectedDate" type="hidden" name="date" />요소 선택
let date = $('.selectedDate');
let nowMonth = new Date();

let listInit = {
	version: 1,
	init: function() {
		$(document).ready(() => {
			this.postData();
			this.addLocationAllOption();
			this.setLocation();
			this.buildCalendar();
			this.printListPagination(currentPage, lectureListData);
		});

		// 드롭다운 버튼 클릭
		$('.dropBtn').click(() => this.setDropdownContentVisibility());

		// 초기화 버튼 클릭
		$('#searchFormReset').on('click', () => this.resetBtn());

		// 이전 달력 버튼 클릭
		$('#prevCalendar').on('click', () => this.prevCalendar());

		// 다음 달력 버튼 클릭
		$('#nextCalendar').on('click', () => this.nextCalendar());

		// 지역 선택 항목 클릭
		$(document).on('click', '.locationElement', (event) => {
			// 클릭한 지역을 가져와서 설정
			let location = $(event.currentTarget).text();
			this.setLocationVal(location);
		});

		// 검색 버튼 클릭
		$('#searchFormSubmit').on('click', () => this.makeSearchUrl());
	},
	printListPagination: function(page, list) {
		// 강의 리스트
		let lectureList = $('.lectureListList');

		// 페이징
		let paginationArea = $('.lectureListPagination');
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

		if (lectureCount !== 0) {
			url.searchParams.set('page', page - 1);
			let prev = $('<a>').addClass('paginationElement prev').attr('href', url.href).text('<');
			if (page <= 3) {
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
				$(document).ready(function() {
					$('.paginationElement.prev').attr('href', 'javascript:void(0);'); // href 속성 비우기
					$('.paginationElement.prev').on('click', function(event) {
						event.preventDefault(); // 클릭 이벤트 차단
					});
				});
			}

			if (page == lastPage) {
				$('.paginationElement.next').attr('href', 'javascript:void(0);'); // href 속성 비우기
				$('.paginationElement.next').on('click', function(event) {
					event.preventDefault(); // 클릭 이벤트 차단
				});
			}
		}
	},

	addLocationAllOption: function() {
		const category = $('.formCategory');
		const option = $('<option>').attr('value', '전체').text('전체');
		category.append(option);
	},

	postData: async function() {
		let url = 'http://localhost:8080/category/getCategory';
		fetch(url)
			.then((response) => response.json())
			.then((data) =>
				data.forEach((item) => {
					const category = $('.formCategory');
					const option = $('<option>').attr('value', item).text(item);
					category.append(option);
				})
			);
	},

	setDropdownContentVisibility: function() {
		let dropdownContent = $('.dropdownContent');
		if (dropdownContent.css('display') === 'block') {
			dropdownContent.css('display', 'none');
		} else {
			dropdownContent.css('display', 'block');
		}
	},

	// 지역 보기 생성
	setLocation: function() {
		$('.locationElement').css('cursor', 'pointer');

		let list1 = ['전체', '서울', '부산', '대구', '인천', '광주', '대전', '울산', '세종', '경기', '강원', '충북', '충남', '전북', '전남', '경북', '경남', '제주'];

		list1.forEach((item, index) => {
			const column1 = $('.column.m1');
			const column2 = $('.column.m2');
			const ele = $('<div>').text(item).addClass('locationElement');

			if (index % 2 === 0) {
				column1.append(ele);
			} else {
				column2.append(ele);
			}
		});
	},
	setLocationVal: function(location) {
		// 선택한 항목의 값을 input 태그에 설정
		$('.formLocation').val(location);
		// 드롭다운 메뉴 숨기기
		$('.dropdownContent').css('display', 'none');
	},
	// 초기화 버튼 기능
	resetBtn: function() {
		window.location.href = 'list?page=1';
	},

	// 이전달 버튼 클릭
	prevCalendar: function() {
		nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() - 1, nowMonth.getDate()); // 현재 달을 1 감소
		this.buildCalendar(); // 달력 다시 생성
	},

	// 다음달 버튼 클릭
	nextCalendar: function() {
		nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, nowMonth.getDate()); // 현재 달을 1 증가
		this.buildCalendar(); // 달력 다시 생성
	},

	// 날짜 선택 함수
	choiceDate: function(newDiv) {
		$('.choiceDay').removeClass('choiceDay');
		$(newDiv).addClass('choiceDay');
		let selYear = $('.calYearList').text();
		let selMonth = $('.calMonthList').text();
		let setDate = $('.choiceDay').text();

		// selectedDate 인풋에 값 집어넣기
		date.val(`${selYear}-${selMonth}-${setDate}`);
		console.log(date.val());
	},

	// 달력 생성
	buildCalendar: function() {
		let today = new Date();
		today.setHours(0, 0, 0, 0);

		let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1);
		let lastDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, 0);

		let $tbodyCalendar = $('.calendarList > tbody');
		$('.calYearList').text(nowMonth.getFullYear());
		$('.calMonthList').text(this.leftPad(nowMonth.getMonth() + 1));

		$tbodyCalendar.empty();

		let $nowRow = $('<tr>');

		for (let j = 0; j < firstDate.getDay(); j++) {
			$nowRow.append($('<td>'));
		}

		for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {
			let $nowColumn = $('<td>');
			let $newDiv = $('<p>').html(this.leftPad(nowDay.getDate()));
			$nowColumn.append($newDiv);

			if (nowDay < today) {
				$newDiv.addClass('pastDay');
			} else if (nowDay.getTime() === today.getTime()) {
				$newDiv.addClass('today');
				$newDiv.click(function() {
					listInit.choiceDate(this);
				});
			} else {
				$newDiv.addClass('futureDay');
				$newDiv.click(function() {
					listInit.choiceDate(this);
				});
			}

			$nowRow.append($nowColumn);

			if (nowDay.getDay() === 6 || nowDay.getTime() === lastDate.getTime()) {
				$tbodyCalendar.append($nowRow);
				$nowRow = $('<tr>');
			}
		}
	},

	leftPad: function(value) {
		if (value < 10) {
			value = '0' + value;
		}
		return value;
	},



	// 검색 url 생성
	makeSearchUrl: function() {
		let url = 'list?page=1';

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
	},
};

listInit.init();