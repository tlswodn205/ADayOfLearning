
// 페이지 초기 설정 시작
let salesInit = {
	version: 1,
	init: function() {
		$(document).ready(() => {
			this.buildSevenDaysChart();
			$('#sevenDaysTable').on('click', () => this.buildSevenDaysChart());
			$('#monthlyTable').on('click', () => this.buildMonthlyChart());
		});
	}, // end of init: function()
	

	// 최근 7일 매출
	buildSevenDaysChart: function() {
		
		$.ajax({
			url: "/business/daily-data",
			type: "get",
			dataType: 'json',
			success: function(res) {
				console.log(res);
				chartInit(res, '최근 7일 매출 현황');
			},
			error: function() {
				alert("데이터를 불러오는데 실패하였습니다.");
			}
		});
	},

	// 월별 매출 (1월~12월)
	buildMonthlyChart: function() {
		$.ajax({
			url: "/business/monthly-data",
			type: "get",
			dataType: 'json',
			success: function(res) {
				console.log(res);
				chartInit(res, '월별 매출 현황');
				res.map(item => parseInt(item.periodTotal, 10))
			},
			error: function() {
				alert("데이터를 불러오는데 실패하였습니다.");
			}
		});
	}
} // end of salesInit


// chart 생성
function chartInit(res, title) {
	
	$('#chart').remove();
	$('#chartArea').append('<canvas id="chart"></canvas>');
	
	const ctx = document.getElementById('chart');
	
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

salesInit.init();

