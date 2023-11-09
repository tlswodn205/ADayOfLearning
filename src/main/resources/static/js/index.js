let indexInit = {
	init: function() {
		$(document).ready(() => {
			this.bannerSwiperInit();
			this.lectureSwiperInit();
			this.lectureListInit();
		});
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
	},
	lectureListInit: function() {
		console.log(lectureList);
		lectureList.forEach(function (lecture, index) {
			let swiperSlide = $('<div>', { class: 'swiper-slide' }).append(lectureContainer(lecture));
			$('#swiper-wrapper').append(swiperSlide);
		});
	}
}

indexInit.init();