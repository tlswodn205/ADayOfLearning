
// 페이지 초기 설정 시작
let salesInit = {
	version: 1,
	init: function() {
		$(document).ready(() => {
			this.buildSevenDaysChart();
			this.buildCustomerChart();
			this.buildBusinessChart();
			this.buildRequestBusinessChart();
			$('#sevenDaysTable').on('click', () => this.buildSevenDaysChart());
			$('#monthlyTable').on('click', () => this.buildMonthlyChart());
		});
	}, // end of init: function()
	

	// 최근 7일 매출
	buildSevenDaysChart: function() {
		
		$.ajax({
			url: "/admin/daily-data",
			type: "get",
			dataType: 'json',
			success: function(res) {
				chartInit(res, '최근 7일 매출 현황', 'chart');
			},
			error: function() {
				alert("데이터를 불러오는데 실패하였습니다.");
			}
		});
	},

	// 월별 매출 (1월~12월)
	buildMonthlyChart: function() {
		$.ajax({
			url: "/admin/monthly-data",
			type: "get",
			dataType: 'json',
			success: function(res) {
				chartInit(res, '월별 매출 현황', 'chart');
				res.map(item => parseInt(item.periodTotal, 10))
			},
			error: function() {
				alert("데이터를 불러오는데 실패하였습니다.");
			}
		});
	},
	
	buildRequestBusinessChart: function() {
		$.ajax({
			url: "/admin/requestBusinessChartData",
			type: "get",
			dataType: 'json',
			success: function(res) {
				lineChartInit(res, '주간 판매자 신청 현황', 'requestBusinessChart');
				res.map(item => parseInt(item.periodTotal, 10))
			},
			error: function() {
				alert("데이터를 불러오는데 실패하였습니다.");
			}
		});
	},
	
	buildCustomerChart: function() {
		$.ajax({
			url: "/admin/customerChartData",
			type: "get",
			dataType: 'json',
			success: function(res) {
				lineChartInit(res, '주간 유저 가입 현황', 'customerChart');
				res.map(item => parseInt(item.periodTotal, 10))
			},
			error: function() {
				alert("데이터를 불러오는데 실패하였습니다.");
			}
		});
	},
	
	buildBusinessChart: function() {
		$.ajax({
			url: "/admin/businessChartData",
			type: "get",
			dataType: 'json',
			success: function(res) {
				lineChartInit(res, '주간 판매자 신청 현황', 'businessChart');
				res.map(item => parseInt(item.periodTotal, 10))
			},
			error: function() {
				alert("데이터를 불러오는데 실패하였습니다.");
			}
		});
	},
	
} // end of salesInit


// chart 생성
function chartInit(res,title, chartname) {

	$('#'+chartname).remove();
	$('#'+chartname+'Area').append('<canvas id="'+chartname+'"></canvas>');
	
	const ctx = document.getElementById(chartname);
	
	
	
	new Chart(ctx, {
		type: 'bar',
		data: {
			labels: res.map(item => item.period),
			datasets: [{
				label: '매출액',
				backgroundColor: '#f5d042',
				data: res.map(item => parseInt(item.periodTotal, 10)),
			}]
		},
		options: {
			title: {
				display: true,
				text: title,
				fontSize: 18
			},
			scales: {
				y: {
					beginAtZero: true,
				}
			},
		}
	})
} // end of chartInit

function lineChartInit(res,title, chartname) {

	$('#'+chartname+'Area').append('<canvas id="'+chartname+'"></canvas>');
	
	const ctx = document.getElementById(chartname);
	
	new Chart(ctx, {
		type: 'line',
		data: {
			labels: res.map(item => item.period),
			datasets: [{
				label: '유저수',
				backgroundColor: '#f5d042',
				data: res.map(item => parseInt(item.periodTotal, 10)),
			}]
		},
		options: {
			title: {
				display: true,
				text: title,
				fontSize: 18
			},
			scales: {
				y: {
					beginAtZero: true,
				},
				yAxes: [{
					ticks: {
						min: 0.0,
					}
				}]
			},
		}
	})
} 

salesInit.init();

