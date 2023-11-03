let indexInit = {
	init: function() {
		$(document).ready(() => {
			swiperInit();
		});
	},
	swiperInit: function() {
		const swiper = new Swiper('.swiper', {
		  // Optional parameters
		  direction: 'vertical',
		  loop: true,
		
		  // Navigation arrows
		  navigation: {
		    nextEl: '.swiper-button-next',
		    prevEl: '.swiper-button-prev',
		  },
		
		  // And if we need scrollbar
		  scrollbar: {
		    el: '.swiper-scrollbar',
		  },
		});
	}
}
    
indexInit.init();