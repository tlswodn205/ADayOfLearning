$(document).ready(function() {
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

	let $tbodyCalendar = $('.calendarDetail > tbody');
	$('.calYearDetail').text(nowMonth.getFullYear()); // 연도 숫자 갱신
	$('.calMonthDetail').text(leftPad(nowMonth.getMonth() + 1)); // 월 숫자 갱신

	$tbodyCalendar.empty(); // 이전 결과 초기화

	let $nowRow = $('<tr>');

	for (let j = 0; j < firstDate.getDay(); j++) {
		$nowRow.append($('<td>'));
	}

	for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {
		let $nowColumn = $('<td>');
		let $newDiv = $('<p>').html(leftPad(nowDay.getDate()));
		$nowColumn.append($newDiv);

		if (nowDay < today) {
			$newDiv.addClass('pastDay'); // 지난 날인 경우
		} else if (nowDay.getTime() === today.getTime()) {
			$newDiv.addClass('today'); // 오늘인 경우
			$newDiv.click(function() {
				choiceDate(this);
			});
		} else {
			$newDiv.addClass('futureDay'); // 미래인 경우
			$newDiv.click(function() {
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

// <input class="selectedDate" type="hidden" name="date" />요소 선택
let date = $('.selectedDate');
// 날짜 선택 함수
function choiceDate(newDiv) {
	$('.choiceDay').removeClass('choiceDay'); // 기존에 선택한 날짜가 있으면 해당 날짜의 "choiceDay" class 제거
	$(newDiv).addClass('choiceDay'); // 선택된 날짜에 "choiceDay" class 추가
	let selYear = $('.calYearDetail').text();
	let selMonth = $('.calMonthDetail').text();
	let setDate = $('.choiceDay').text();

	// selectedDate 인풋에 값 집어넣기
	date.val(`${selYear}-${selMonth}-${setDate}`);
	console.log(date.val());

	$('.reserveArea').show();
	$('.dateReserve').text(date.val() + ' 개설된 클래스');
	console.log(lectureData);
	postData();


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

$('.futureDay, .today').click(function() {
	console.log(1);
	date.val($(this).text());
});
//===================================================

function showInformation(lecture, photos) {
	$('.detailInfo.content').append(lecture.content);
	$('.detailLectureAddress').text(lecture.address + ', ' + lecture.addressDetail);
	$('.detailLectureTitle').text(lecture.title);
	$('.maximumStudents').text(`최대 수용가능 인원 ${lecture.maximum}명`);
	$('.detailPrice').text(lecture.price.toLocaleString('ko-KR') + '원');

	console.log('la' + lecture.latitude);
	console.log('lo' + lecture.longitude);

	// 지도 ==========================
	var mapContainer = $('.detailInfo.location')[0], // 지도를 표시할 div
		mapOption = {
			center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level: 3, // 지도의 확대 레벨
		};

	// 지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption);

	// 지도 확대 축소를 제어할 수 있는 줌 컨트롤을 생성합니다
	let zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(lecture.address, function(result, status) {
		// 정상적으로 검색이 완료됐으면
		if (status === kakao.maps.services.Status.OK) {
			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords,
			});

			// 인포윈도우로 장소에 대한 설명을 표시합니다
			var infowindow = new kakao.maps.InfoWindow({
				content: `<div style="width:150px;text-align:center;padding:6px 0;">${lecture.title}</div>`,
			});
			infowindow.open(map, marker);

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		}
	});

	// ==========================

	// 사진 ==========================
	$('.detailMainPhoto>img').attr('src', photos[0].img).attr('height', '500px').attr('width', '100%');
	let subPhotoArea = $('.detailSubPhoto');

	if (photos.length > 1) {
		for (var i = 0; i < photos.length; i++) {
			let photo = photos[i];
			// 새로운 <div> 요소를 생성하고 클래스를 추가
			let newDiv = $('<div>').addClass('subPhoto');

			// <img> 요소를 생성하고 src와 alt 속성을 설정
			let newImg = $('<img>').attr('src', photo.img).attr('height', '92px');

			// <img> 요소를 <div> 요소 내에 추가
			newDiv.append(newImg);

			// 생성한 요소를 문서에 추가
			$('.detailSubPhoto').append(newDiv);
		}
	}
}

// 스크롤 이벤트 리스너 : 아래로 내리면 오른쪽 결제창이 따라옴
window.addEventListener('scroll', function() {
	let scrollPosition = $(window).scrollTop();

	// 스크롤 위치가 0에서 60 범위에 있을 때
	if (scrollPosition >= 0 && scrollPosition <= 60) {
		$('.column2Container').css({
			position: 'static',
			top: 'auto',
		});
	}
	// 스크롤 위치가 61에서 72 범위에 있을 때
	else if (scrollPosition >= 61 && scrollPosition <= 72) {
		var topValue = scrollPosition - 60;
		$('.column2Container').css({
			position: 'fixed',
			top: topValue + 'px',
		});
	}
	// 스크롤 위치가 73 이상일 때
	else if (scrollPosition >= 73) {
		$('.column2Container').css({
			position: 'fixed',
			top: '12px',
		});
	}
});
// =======================

$(document).ready(function() {
	$('.subPhoto > img').on('click', function() {
		$('.detailMainPhoto > img').attr('src', this.src);
	});
});

async function postData() {
	let url = 'http://localhost:8080/lecture/reserve-data';
	console.log(url);
	const response = await fetch(url, {
		method: 'POST',
		body: JSON.stringify({
			lecuteId: lectureData.lectureId,
			date: date.val(),
		}),
		headers: {
			'Content-Type': 'application/json',
		},
	})
		.then((response) => response.json())
		.then((result) => {
			$(".reserveList").empty();
			if (result.length > 0) {
				// 새로운 컨테이너 요소 생성
				result.forEach((element) => {
					console.log(element);

					const $reserveSession = $('<div class="reserveSession">');
					const unixTimestamp = element.sessionDate;
					let minutesOffset = lectureData.duration;
					$reserveSession.click(function() { makePaymentUrl(element.lectureSessionId) });

					calculateTimeWithOffset(unixTimestamp, minutesOffset);

					// 모집 상태 요소 생성 및 텍스트 설정
					const $state = $('<div class="reserveSessionState">').text('모집중');

					// 모집 현황 요소 생성 및 텍스트 설정
					const $count = $('<div class="reserveSessionCount">').text(`${element.students}/${lectureData.maximum}`);

					// 시간대 요소 생성 및 텍스트 설정
					const $duration = $('<div class="reserveSessionDuration">').text(
						calculateTimeWithOffset(unixTimestamp, minutesOffset)
					);

					// 생성한 하위 요소들을 컨테이너에 추가
					$reserveSession.append($state, $count, $duration);

					// 컨테이너를 원하는 부모 요소에 추가
					$('.reserveList').append($reserveSession);


				});
			}
		});
}

function calculateTimeWithOffset(unixTimestamp, minutesToAdd) {
	const startDate = new Date(unixTimestamp);

	// 시작 시간 계산
	const startHours = startDate.getHours();
	const startMinutes = startDate.getMinutes();

	// 분을 추가하는 함수
	function addMinutes(date, minutes) {
		return new Date(date.getTime() + minutes * 60000);
	}

	// 종료 시간 계산
	const endDate = addMinutes(startDate, minutesToAdd);
	const endHours = endDate.getHours();
	const endMinutes = endDate.getMinutes();

	// HH:mm ~ HH:mm 형식으로 표시
	const formattedStartTime = `${startHours.toString().padStart(2, '0')}:${startMinutes.toString().padStart(2, '0')}`;
	const formattedEndTime = `${endHours.toString().padStart(2, '0')}:${endMinutes.toString().padStart(2, '0')}`;

	const timeRange = `${formattedStartTime} ~ ${formattedEndTime}`;

	return timeRange;
}



function makePaymentUrl(lectureSessionId) {
	let enroll = $("#enroll");
	let url = `http://localhost:8080/payment/payRequest?lectureSessionId=${lectureSessionId}`;
	enroll.click(function() { addAttr(url) });
}

function addAttr(url) {
	window.location.href = url;
}

