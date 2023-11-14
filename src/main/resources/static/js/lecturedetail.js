let date = $('.selectedDate'); // <input class="selectedDate"/>요소 선택
let nowMonth = new Date(); // 현재 달을 페이지를 로드한 날의 달로 초기화

let insertTiny;
let updateTiny;
let userId;

let sumReview;

let detailInit = {
	version: 1,
	init: function() {
		$(document).ready(() => {
			this.showInformation(lectureData, photoList, reviewList);
			this.buildCalendar();
			// 리뷰 입력창 기본 세팅
			this.reviewInputInit();
		});
		// 서브 이미지 클릭
		$(document).on('click', '.subPhoto >img', (event) => {
			this.changeImage($(event.currentTarget).prop('src'));
			$('.subPhoto>img').removeClass('focus');
			$(event.currentTarget).addClass('focus');
		});
		// 이전 달 클릭
		$('#prevCalendar').on('click', () => this.prevCalendar());
		// 다음 달 클릭
		$('#nextCalendar').on('click', () => this.nextCalendar());
		// 날짜 선택 함수
		$('.futureDay, .today').click((event) => detailInit.choiceDate($(event.currentTarget)));
		$(window).scroll(this.handleScrollElementFixed);

		// "클래스 소개" 링크를 클릭했을 때 스크롤 위치를 조정합니다.
		$('.detailASet > a').on('click', function(event) {
			detailInit.moveScroll($(event.currentTarget), event);
		});
		// 리뷰 저장
		$('#reviewInputBtn').click(() => this.reviewInput());
		// 문의하기
		$('#inquiryBtn').click(() => this.inquiry());
		// 클래스 리뷰 점수
		classScore();
	},

	showInformation: function(lecture, photos, reviewList) {
		userId = $('#userId').val();
		$('.detailInfo.content').append(lecture.content);
		$('.lectureDetatilRight.address').text(lecture.address + ', ' + lecture.addressDetail);
		$('.detailLectureTitle').text(lecture.title);
		$('.lectureDetatilRight.maximum').text(`최대 수용가능 인원 ${lecture.maximum}명`);
		$('.lectureDetatilRight.phone').text(lecture.phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3'));
		$('.lectureDetatilRight.duration').text("강의 진행 시간 " + lecture.duration + '분');
		$('.lectureDetatilRight.price').text(lecture.price.toLocaleString('ko-KR') + ' 원');

		// 평균 계산
		if (reviewList.length > 0) {
			var scoreSum = reviewList.reduce(function(acc, review) {
				return acc + review.score;
			}, 0);
			var averageScore = parseFloat(scoreSum / reviewList.length).toFixed(1);
			$('.detailReviewAvr').text(averageScore + "(" + reviewList.length + ")");
		} else {
			$('.detailReviewAvr').text(0 + "(" + reviewList.length + ")");
		}


		options.forEach((item) => {
			let $div = $('<div>');
			$div.addClass('optionContainer');
			switch (item) {
				case '주차공간 준비됨':
					$div.append('<i class="fa-solid fa-car"></i>');
					break;
				case '녹화본 제공':
					$div.append('<i class="fa-solid fa-video"></i>');
					break;
				case '준비물 제공':
					$div.append('<i class="fa-solid fa-box-open">');
					break;
				case '어린이 놀이구역 있음':
					$div.append('<i class="fa-solid fa-child">');
					break;
				case '여성회원들만':
					$div.append('<i class="fa-solid fa-venus">');
					break;
				case '남성회원들만':
					$div.append('<i class="fa-solid fa-mars"></i>');
					break;
				case '노키즈존':
					$div.append('<i class="fa-solid fa-ban"></i>');
					break;
				default:
			}
			$div.append(`  <div>${item}</div>`);
			$('.detailInfo.option').append($div);
		});

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

		if (photos.length >= 1) {
			for (var i = 0; i < photos.length; i++) {
				let photo = photos[i];
				// 새로운 <div> 요소를 생성하고 클래스를 추가
				let newDiv = $('<div>').addClass('subPhoto');

				// <img> 요소를 생성하고 src와 alt 속성을	 설정
				let newImg = $('<img>').attr('src', photo.img);
				if (i == 0) {
					newImg.addClass('focus');
				}

				// <img> 요소를 <div> 요소 내에 추가
				newDiv.append(newImg);

				// 생성한 요소를 문서에 추가
				$('.detailSubPhoto').append(newDiv);
			}
		}

		if (reviewList.length > 0) {
			// 리뷰 추가
			reviewList.forEach((review) => {
				reviewAppend(review);
			});
		}
	},

	// 서브 이미지 클릭 => 본 이미지로 올림
	changeImage: function(src) {
		$('.detailMainPhoto > img').attr('src', src);
	},

	// 달력 생성 함수: 해당 달에 맞춰 테이블을 만들고, 날짜를 채워 넣음
	buildCalendar: function() {
		let today = new Date();
		today.setHours(0, 0, 0, 0);

		let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1); // 이번달 1일
		let lastDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, 0); // 이번달 마지막날

		let $tbodyCalendar = $('.calendarDetail > tbody');
		$('.calYearDetail').text(nowMonth.getFullYear()); // 연도 숫자 갱신
		$('.calMonthDetail').text(this.leftPad(nowMonth.getMonth() + 1)); // 월 숫자 갱신

		$tbodyCalendar.empty(); // 이전 결과 초기화

		let $nowRow = $('<tr>');

		for (let j = 0; j < firstDate.getDay(); j++) {
			$nowRow.append($('<td>'));
		}

		for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {
			let $nowColumn = $('<td>');
			let $newDiv = $('<p>').html(this.leftPad(nowDay.getDate()));
			$nowColumn.append($newDiv);

			if (nowDay < today) {
				$newDiv.addClass('pastDay'); // 지난 날인 경우
			} else if (nowDay.getTime() === today.getTime()) {
				$newDiv.addClass('today'); // 오늘인 경우
				$newDiv.click(function() {
					detailInit.choiceDate(this);
				});
			} else {
				$newDiv.addClass('futureDay'); // 미래인 경우
				$newDiv.click(function() {
					detailInit.choiceDate(this);
				});
			}

			$nowRow.append($nowColumn);

			if (nowDay.getDay() === 6 || nowDay.getTime() === lastDate.getTime()) {
				$tbodyCalendar.append($nowRow);
				$nowRow = $('<tr>'); // 새로운 행 추가
			}
		}
	},

	// 날짜 선택 함수
	choiceDate: function(newDiv) {
		let date = $('.selectedDate'); // <input class="selectedDate"/>요소 선택

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
		this.postData();
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

	// input값이 한자리 숫자인 경우 앞에 '0' 붙여주는 함수
	leftPad: function(value) {
		if (value < 10) {
			value = '0' + value;
		}
		return value;
	},
	// 예약 데이터 불러오는 함수
	postData: function() {
		let url = 'http://localhost:8080/lecture/reserve-data';
		fetch(url, {
			method: 'POST',
			body: JSON.stringify({
				lectureId: lectureData.lectureId,
				date: date.val(),
			}),
			headers: {
				'Content-Type': 'application/json',
			},
		})
			.then((response) => response.json())
			.then((result) => {
				//                $('#enroll').hide();
				$('.reserveList').empty();
				if (result.length > 0) {
					if (result.length > 2) {
						$('.reserveList').css('overflow', 'scroll');
						$('.reserveList').css('overflow-y', 'hidden');
					}
					// 새로운 컨테이너 요소 생성
					result.forEach((element) => {
						const $reserveSession = $('<div class="reserveSession">');
						const unixTimestamp = element.sessionDate;
						let minutesOffset = lectureData.duration;

						this.calculateTimeWithOffset(unixTimestamp, minutesOffset);
						const $state = $(`<div class="reserveSessionState">`);

						// 모집 상태 요소 생성 및 텍스트 설정
						if (element.students >= lectureData.maximum) {
							$state.text('모집 완료!');
							$reserveSession.addClass('done');
							console.log(element.seesionDate);
							if (element.myReserve === 1) {
								console.log('모집 완료 && 내가 신청 함');
								// 모집 완료 && 내가 신청 함
							} else {
								console.log('모집 완료 && 내가 신청 안함');
								// 모집 완료 && 내가 신청 안함
							}
						} else {
							$state.text('모집중!');
							$reserveSession.css('cursor', 'pointer');
							if (element.myReserve === 1) {
								console.log('모집중 && 내가 신청 함');
								// 모집중 && 내가 신청 함
							} else {
								console.log('모집중 && 내가 신청 안함');
								// 모집중 && 내가 신청 안함
								$reserveSession.click(function() {
									$('.reserveSession.select').removeClass('select');
									$reserveSession.addClass('select');
									$('#enroll').show();
									detailInit.makePaymentUrl(element.lectureSessionId);
								});
							}
						}
						const $count = $('<div class="reserveSessionCount">').text(`인원 : ${element.students}/${lectureData.maximum}`);
						// 시간대 요소 생성 및 텍스트 설정
						const $duration = $('<div class="reserveSessionDuration">').text(`수업 시간 : \n${this.calculateTimeWithOffset(unixTimestamp, minutesOffset)}`);
						// 생성한 하위 요소들을 컨테이너에 추가
						$reserveSession.append($state, $count, $duration);
						// 컨테이너를 원하는 부모 요소에 추가
						$('.reserveList').append($reserveSession);
					});
				} else {
					$noReserve = $(`<div class="noReserve">`);
					$noReserve.text('개설된 예약이 없어요 ');
					$noReserve.append('<i class="fa-regular fa-face-sad-tear fa-lg"></i>');
					$('.reserveList').append($noReserve);
				}
			});
	},

	handleScrollElementFixed: function() {
		let scrollPosition = $(window).scrollTop();
		if (scrollPosition == 0) {
			$('.column2Container').css({
				position: 'static',
				top: 'auto',
			});
		} else if (scrollPosition > 0) {
			$('.column2Container').css({
				position: 'fixed',
				top: '75px',
			});
		}
	},

	makePaymentUrl: function(lectureSessionId) {
		let enroll = $('#enroll');
		let url = `http://localhost:8080/payment/payRequest?lectureSessionId=${lectureSessionId}`;
		enroll.click(function() {
			detailInit.addAttr(url);
		});
	},

	calculateTimeWithOffset: function(unixTimestamp, minutesToAdd) {
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
	},

	addAttr: function(url) {
		window.location.href = url;
	},

	moveScroll: function(a, event) {
		event.preventDefault(); // 기본 동작(링크 이동)을 중지합니다.
		var url = a.prop('href');

		// URL에서 해시 부분 (예: #detailContent) 가져오기
		var hash = url.substring(url.indexOf('#'));

		// 대상 요소인 #detailContent의 상단 위치를 구합니다.
		let targetPosition = $(hash).offset().top;

		// 60px을 빼서 원하는 위치로 이동합니다.
		targetPosition -= 60;

		// 스무스한 스크롤을 적용하여 위치로 이동합니다.
		$('html, body').animate(
			{
				scrollTop: targetPosition,
			},
			700
		); // 700ms (0.7초) 동안 스무스한 스크롤을 적용
	},
	// 리뷰 등록 기본 세팅
	reviewInputInit: function() {
		if (userId !== '' && $('.reviewUser[name="' + userId + '"]').length === 0) {
			tinyInit('#reviewInput', 620, 100);
			insertTiny = tinymce.activeEditor;
		} else {
			$('.reviewInputContainer').hide();
		}
	},
	// 리뷰 등록 버튼 ==========================
	reviewInput: async function() {
		let reviewContent = insertTiny.getContent();
		let reviewScore = $('input[name="scoreInput"]:checked').val();

		const response = await fetch('/review/insert', {
			method: 'POST',
			body: JSON.stringify({
				lectureId: lectureData.lectureId,
				score: reviewScore,
				content: reviewContent,
			}),
			headers: {
				'Content-Type': 'application/json',
			},
		})
			.then((res) => res.json())
			.then((result) => {
				if (result.status === 'BAD_REQUEST') {
					alert(result.message);
					return;
				}
				reviewAppend(result);
				insertTiny.setContent('');
				classScore();
				$('.reviewInputContainer').hide()
			})
			.catch((error) => {
				console.log(error);
			});
	},
	// 문의하기
	inquiry: function() {
		location.href = '/chat/room?userId=' + lectureData.userId;
	},
}; // detailInit() 끝

// 리뷰 수정 버튼
function reviewUpdateBtn(comp) {
	if ($('#scoreUpdate').length > 0) {
		reviewChangeShow($('#scoreUpdate').closest('.reviewContainer'));
	}
	let reviewContainer = $(comp).parents('div[class="reviewContainer"]');
	let reviewContent = reviewContainer.find('#reviewContent').html();
	// 별점 수정 가능하게 확인 ===================

	reviewUpdateShow(reviewContainer);
	reviewContainer.append($('<textarea>').addClass('reviewInput').attr('id', 'reviewUpdateContent').text(reviewContent));

	tinyInit('#reviewUpdateContent', 650, 100);
	updateTiny = tinymce.activeEditor;
}
// 리뷰 수정 완료 버튼
async function reviewUpdateProc(comp) {
	let reviewContainer = $(comp).parents('div[class="reviewContainer"]');
	let reviewContent = reviewContainer.find('#reviewContent');

	await reviewUpdate(reviewContainer);

	reviewContent.html(updateTiny.getContent());

	reviewChangeShow(reviewContainer);
}
// 리뷰 수정 기능
async function reviewUpdate(reviewContainer) {
	let score = $('input[name="scoreUpdate"]:checked').val();
	const response = await fetch('/review/update', {
		method: 'put',
		body: JSON.stringify({
			reviewId: reviewContainer.val(),
			score: score,
			content: updateTiny.getContent(),
		}),
		headers: {
			'Content-Type': 'application/json',
		},
	})
		.then((res) => {
			if (res.status === 200) {
				return res.text();
			} else {
				return res.json();
			}
		})
		.then((result) => {
			if (result.status === 'BAD_REQUEST') {
				alert(result.message);
				return;
			}
			reviewContainer.find('#reviewScoreFill').css('width', score * 20 + '%');
			classScore();
		})
		.catch((error) => {
			console.log(error);
		});
}
// 리뷰 수정 취소 버튼
function reviewUpdateBack(comp) {
	let reviewContainer = $(comp).parents('div[class="reviewContainer"]');
	reviewChangeShow(reviewContainer);
}

// 리뷰 삭제
function reviewDelete(comp) {
	const response = fetch('/review/delete', {
		method: 'DELETE',
		body: JSON.stringify({
			reviewId: $(comp).val(),
		}),
		headers: {
			'Content-Type': 'application/json',
		},
	})
		.then((res) => {
			if (res.status === 200) {
				return res.text();
			} else {
				return res.json();
			}
		})
		.then((result) => {
			if (result.status === 'BAD_REQUEST') {
				alert(result.message);
				return;
			}
			$(comp).parents('div[class="reviewContainer"]').remove();
			if ($('#reviewContainer').length == 0) {
				$('#reviewNone').show();
			}
			classScore();
			$('.reviewInputContainer').show();
		})
		.catch((error) => {
			console.log(error);
		});
}

// 리뷰 append ===================
function reviewAppend(review) {
	$('#reviewNone').hide();
	let reviewContainer =
		$('<div>', { class: 'reviewContainer' }).val(review.reviewId).append(
			$('<div>', { class: 'reviewHeader' }).append(
				$('<div>', { class: 'reviewTitle', id: 'reviewTitle' }).append(
					$('<div>', { class: 'reviewUser', name: review.userId, text: review.username }),
					$('<div>', { class: 'reviewScoreContainer', id: 'reviewScoreContainer' }).append(
						$('<div>', { class: 'reviewScore', id: 'reviewScore' }).val(review.score).append(
							$('<div>', {
								class: 'reviewScoreFill', id: 'reviewScoreFill'
								, style: 'width: ' + review.score * 20 + '%', text: '★★★★★'
							}),
							$('<div>', { class: 'reviewScoreBase', text: '★★★★★' })
						)
					),
					$('<div>', { class: 'reviewCreatedAt', text: review.createdAt })
				),
				$('<div>', {
					class: 'reviewChange', id: 'reviewChange'
					, style: review.userId.toString() === userId ? '' : 'display: none'
				}).append(
					$('<button>', { class: 'reviewUpdateBtn', text: '수정', onclick: 'reviewUpdateBtn(this)' }),
					$('<button>', { class: 'reviewDeleteBtn', text: '삭제', value: review.reviewId, onclick: 'reviewDelete(this)' })
				),
				$('<div>', { class: 'reviewUpdate', id: 'reviewUpdate', style: 'display: none' }).append(
					$('<button>', { class: 'reviewUpdateProc', text: '수정 완료', value: review.reviewId, onclick: 'reviewUpdateProc(this)' }),
					$('<button>', { class: 'reviewUpdateBack', text: '취소', onclick: 'reviewUpdateBack(this)' })
				)
			),
			$('<div>', { class: 'reviewContent', id: 'reviewContent', html: review.content })
		);

	$('.detailInfo.review').append(reviewContainer);
}

// 수정 버튼 누름
function reviewUpdateShow(reviewContainer) {
	reviewContainer.find('#reviewContent').hide();
	reviewContainer.find('#reviewChange').hide();
	reviewContainer.find('#reviewUpdate').show();
	reviewContainer.find('#reviewScore').hide();
	$('#scoreUpdate').remove();

	// 평점 변경 가능하게 수정
	let reviewScore = reviewContainer.find('#reviewScore');
	let reviewScoreUpdate = $('<div>').addClass('star-rating update').attr('id', 'scoreUpdate');

	for (let i = 5; i >= 1; i--) {
		reviewScoreUpdate.append(
			$('<input>', {
				type: 'radio', id: i + '-starsUpdate', name: 'scoreUpdate', val: i
				, checked: i === parseInt(reviewScore.val()) ? true : false
			}),
			$('<label>', { for: i + '-starsUpdate', class: 'star', text: '★' })
		);
	}
	reviewContainer.find('#reviewScoreContainer').append(reviewScoreUpdate);
}
// 수정 완료, 취소 버튼 누름
function reviewChangeShow(reviewContainer) {
	reviewContainer.find('#reviewContent').show();
	reviewContainer.find('#reviewChange').show();
	reviewContainer.find('#reviewUpdate').hide();
	reviewContainer.find('#reviewScore').show();
	updateTiny.editorContainer.remove();
	$('#scoreUpdate').remove();
	$('#reviewUpdateContent').remove();
}

// 클래스 평점 계산
function classScore() {
	let scoreAvg = 0.0;
	let scoreAll = $('.reviewScore');
	if (scoreAll.length > 0) {
		scoreAll.each((index, score) => {
			scoreAvg += parseInt(score.value);
		});
		scoreAvg = scoreAvg / scoreAll.length;
	}

	scoreAvg =
		parseFloat(scoreAvg.toFixed(2)) === parseInt(scoreAvg) ? parseInt(scoreAvg) : parseFloat(scoreAvg.toFixed(2)) === parseFloat(scoreAvg.toFixed(1)) ? scoreAvg.toFixed(1) : scoreAvg.toFixed(2);
	$('.detailReviewAvr').text('(' + scoreAvg + ')');
}

// tiby 에디터 초기화
function tinyInit(selector, width, height) {
	var plugins = [
		'advlist',
		'autolink',
		'lists',
		'link',
		'charmap',
		'print',
		'preview',
		'anchor',
		'searchreplace',
		'visualblocks',
		'code',
		'fullscreen',
		'insertdatetime',
		'media',
		'table',
		'paste',
		'code',
		'help',
	];

	tinymce.init({
		language: 'ko_KR', //한글판으로 변경
		selector: selector,
		width: width,
		height: height,
		menubar: false,
		plugins: plugins,
		toolbar: [],

		/*** image upload ***/
		image_title: true,
		/* enable automatic uploads of images represented by blob or data URIs*/
		automatic_uploads: true,
		/*
			URL of our upload handler (for more details check: https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_url)
			images_upload_url: 'postAcceptor.php',
			here we add custom filepicker only to Image dialog
		*/
		file_picker_types: 'image',
		/* and here's our custom image picker*/
		file_picker_callback: function(cb, value, meta) {
			var input = document.createElement('input');
			input.setAttribute('type', 'file');
			input.setAttribute('accept', 'image/*');

			/*
			Note: In modern browsers input[type="file"] is functional without
			even adding it to the DOM, but that might not be the case in some older
			or quirky browsers like IE, so you might want to add it to the DOM
			just in case, and visually hide it. And do not forget do remove it
			once you do not need it anymore.
			*/
			input.onchange = function() {
				var file = this.files[0];

				var reader = new FileReader();
				reader.onload = function() {
					/*
					Note: Now we need to register the blob in TinyMCEs image blob
					registry. In the next release this part hopefully won't be
					necessary, as we are looking to handle it internally.
					*/
					var id = 'blobid' + new Date().getTime();
					var blobCache = tinymce.activeEditor.editorUpload.blobCache;
					var base64 = reader.result.split(',')[1];
					var blobInfo = blobCache.create(id, file, base64);
					blobCache.add(blobInfo);

					/* call the callback and populate the Title field with the file name */
					cb(blobInfo.blobUri(), { title: file.name });
				};
				reader.readAsDataURL(file);
			};
			input.click();
		},
		/*** image upload ***/

		content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px; margin: 10px } p { margin:0 }',
	});
}

detailInit.init();
