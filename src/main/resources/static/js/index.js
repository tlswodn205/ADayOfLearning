let indexInit = {
	init: function() {
		$(document).ready(function() {
			indexInit.bannerSwiperInit();
			indexInit.lectureSwiperInit();
		})
	},
	bannerSwiperInit: function() {
		let bannerSwiper = new Swiper('.bannerSwiper', {
			pagination: {
				el: ".swiper-pagination",
				clickable: true,
			},
		});
	},
	lectureSwiperInit: function() {
		let lectureSwiper = new Swiper('.lectureSwiper', {
			slidesPerView: 4,
			spaceBetween: 30,
			pagination: {
				el: ".swiper-pagination",
				type: "progressbar",
				clickable: true,
			},
			autoplay: {
				delay: 2.5 * 1000,
				disableOnInteraction: false,
			},
		});
	}

}

indexInit.init();