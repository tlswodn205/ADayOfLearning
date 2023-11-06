let indexInit = {
	init: function() {
		$(document).ready(function() {
			indexInit.swiperInit();
		})
	},
	swiperInit: function() {
		let swiper = new Swiper('.swiper', {
			slidesPerView: 4,
			spaceBetween: 30,
			pagination: {
				el: ".swiper-pagination",
				type: "progressbar",
				clickable: true,
			},
		});
	}

}

indexInit.init();