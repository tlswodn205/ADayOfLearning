
/**
 * lecture 객체 하나를 받아 domTree 객체 생성
 * @return lecture (<div> 트리 - jQuery)
 */
function lectureContainer(lecture) {
	let lectureContainer =
	$('<div>', { class: 'lectureContainer' }).append(
		$('<a>', { class: 'lectureItem', href: "/lecture/detail?id=" + lecture.lectureId}).append(
			$('<div>', { class: 'lectureItemContainer' }).append(
				$('<div>', { class: 'lectureItemPictureArea' }).append(
					$('<img>', { class: 'lectureItemPicture', src: lecture.img }),
					$('<div>', { class: 'lectureItemAddress', text: lecture.address })
				),
				$('<div>', { class: 'lectureItemInfoTop'}).append(
					$('<div>', { class: 'lectureItemCategory', text: lecture.categoryName}),
					$('<div>', { class: 'lectureItemReview'}).append(
						
						$('<i>', { class: 'fa-solid fa-star'}),
						$('<div>', { class: 'lectureItemReviewScore', text: lecture.reviewScore}),
						$('<div>', { class: 'lectureItemReviewCount', text: "("+lecture.reviewCount+")"}),
					),
				),
				$('<div>', { class: 'lectureItemProvider', text: lecture.businessname}),
				$('<div>', { class: 'lectureItemTitle', text: lecture.title}),
				$('<div>', { class: 'lectureItemPrice', text: priceToString(lecture.price) + '원'}),
			)
		)
	)
	return lectureContainer;
}

/**
 * 숫자를 입력 받아 1,000 단위로 , 생성
 */
function priceToString(price) {
	return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}
