let lectureMapInit = {
	version: 1,
	init: function() {
		$(document).ready(() => {
			this.mapInit();
		})
	},
	// mapInit 시작
	mapInit: function() {
		let mapContainer = $('#map')[0]; // 지도를 표시할 div
		let locPosition = new kakao.maps.LatLng(lastLecture === null ? 37.56682645618825 : lastLecture.latitude, lastLecture === null ? 126.97865791770842 : lastLecture.longitude);
		let mapOption = {
			center: locPosition, // 지도의 중심좌표
			level: 3 // 지도의 확대 레벨
		};
		// 지도를 생성합니다
		map = new kakao.maps.Map(mapContainer, mapOption);

		// 지도 확대 축소를 제어할 수 있는 줌 컨트롤을 생성합니다
		let zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		map.setCenter(locPosition);
		
		this.searchLecture(lastLecture);

		// 지도 위치 이동 이벤트
		kakao.maps.event.addListener(map, 'dragend', async function() {
			await lectureMapInit.searchLecture();
		});
		// 지도 level 변경 이벤트
		kakao.maps.event.addListener(map, 'zoom_changed', async function() {
			await lectureMapInit.searchLecture();
		});
	}, // mapInit 끝
	searchLecture: async function() {
		
		let mapBounds = map.getBounds();
		let mapParam = new URLSearchParams(mapBounds);

		let response = await fetch(`/lecture/findMap?${mapParam.toString()}`, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
			},
		})
		.then((response) => response.json())
		.then((response) => {
			markers = [];
			response.forEach(function(lecture, index) {
				addressSearch(lecture);
			});
		});
	}
}
function addressSearch(lecture) {
	let geocoder = new kakao.maps.services.Geocoder();
	geocoder.addressSearch(lecture.address, function(result, status) {
		// 정상적으로 검색이 완료됐으면
		console.log(lecture);
		if (status === kakao.maps.services.Status.OK) {
			console.log('주소 조회 완료');
			let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			console.log(coords);
			// 결과값으로 받은 위치를 마커로 표시합니다
			let marker = new kakao.maps.Marker({
				map: map,
				position: coords,
			});
			console.log(marker);
			
			// 인포윈도우로 장소에 대한 설명을 표시합니다
			let infowindow = new kakao.maps.InfoWindow({
				content: lectureContainer(lecture).html(),
			});
			infowindows.push(infowindow);
			
			// 마커에 마우스 클릭 이벤트를 등록합니다
			kakao.maps.event.addListener(marker, 'click', function() {
				// 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
				infowindows.forEach(function (infoItem) {
					infoItem.close();
				})
				infowindow.open(map, marker);
			});

			// 맵에 마우스아웃 이벤트를 등록합니다
			kakao.maps.event.addListener(map, 'click', function() {
				// 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
				infowindow.close();
			});
		}
	});
}

let map;
let infowindows = [];
lectureMapInit.init();