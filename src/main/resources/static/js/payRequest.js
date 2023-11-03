/**
 * 		payRequest.jsp
 */

function convertUnixTimestamp(unixTimestamp) {
	let days = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
	let date = new Date(unixTimestamp);

	let year = date.getFullYear();
	let month = date.getMonth() + 1;
	let day = date.getDate();
	let dayOfWeek = days[date.getDay()];
	let hour = date.getHours();
	let minute = date.getMinutes();
	hour = hour < 10 ? "0" + hour : hour;
	minute = minute < 10 ? "0" + minute : minute;

	let formattedDate = `${month}월 ${day}일 (${dayOfWeek}) ${hour}:${minute}`;
	
	$("#sessionDate").text(formattedDate);
}



$(document).ready(function() {
	convertUnixTimestamp(sessionData.sessionDate);
	$("#payRequsetPrice").text(lectureData.price.toLocaleString('ko-KR') + '원');
	$("#payRequsetTitle").val(lectureData.title);
});