// Tiny Editor Script
$(function () {
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
        'wordcount',
        'save',
    ];
    var edit_toolbar =
        'formatselect fontselect fontsizeselect |' +
        ' forecolor backcolor |' +
        ' bold italic underline strikethrough |' +
        ' alignjustify alignleft aligncenter alignright |' +
        ' bullist numlist |' +
        ' table tabledelete';

    tinymce.init({
        language: 'ko_KR', //한글판으로 변경
        selector: '#reviseFormContent',
        height: 500,
        menubar: false,
        plugins: plugins,
        toolbar: edit_toolbar,
        content_style: 'body { font-family: Noto Sans KR, sans-serif; font-size:14px }',
    });

    $('#save').on('click', function () {
        var content = tinymce.activeEditor.getContent();
        console.log(content);
    });
});

// 우편번호 찾기 화면을 넣을 element
var element_layer = document.getElementById('layer');

// 주소-좌표 변환 객체
var geocoder = new kakao.maps.services.Geocoder();
let imgCount = 0;

let reviseInit = {
    version: 1,
    init: function () {
        $(document).ready(() => {
            this.postData();
            this.getOriginImage();
            this.checkOriginOption();
            this.validateForm();
            this.preventEnterKeySubmit();
        });
        $(document).on('click', '.deleteImg', (event) => {
            this.deleteImage(event);
        });
        $(document).on('change', '#reviseFormThumbnail', (event) => {
            this.setThumbnail(event);
        });
        $(document).on('change', '#reviseFormFiles', (event) => {
            this.setImage(event);
        });
        $('#reviseSearchAddress').click(() => this.execDaumPostcode());

        $('#btnCloseLayer').click(() => this.closeDaumPostcode());
    },
    // 카테고리 메뉴 sql통신으로 받아오기
    postData: function () {
        let url = 'http://localhost:8080/category/getCategory';
        fetch(url)
            .then((response) => response.json())
            .then((data) =>
                data.forEach((item, idx) => {
                    let newOption = $('<option>', {
                        value: idx + 1,
                        text: item,
                    });
                    $('#reviseCategory').append(newOption);
                })
            );
    },
    deleteImage: function (event) {
        let imgPanel = $(event.currentTarget).parent().parent();
        imgPanel.css('display', 'none');
        imgPanel.children('.state').val('delete');
    },
    getOriginImage: function () {
        photoList.forEach((photo, idx) => {
            if (photo.thumbnail === true) {
                let inputHidden1 = $('<input>');
                let inputHidden2 = $('<input>');
                let imgDiv = $('<div>');
                let img = $('<img>');
                let overlay = $('<div>');

                // 이미지 태그 옵션
                img.attr('src', photo.img).attr('width', '100').attr('height', '60');
                inputHidden1
                    .attr('type', 'hidden')
                    .attr('value', photo.lecturePhotoId)
                    .attr('name', 'revisePhotoList[' + idx + '].lecturePhotoId');
                inputHidden2
                    .attr('type', 'hidden')
                    .attr('name', 'revisePhotoList[' + idx + '].state')
                    .attr('class', 'state')
                    .val('default');

                imgDiv.addClass('imagePanel ' + idx);
                overlay.addClass('reviseLectureImgOverlay');

                imgDiv.append(inputHidden1);
                imgDiv.append(inputHidden2);
                imgDiv.append(img);
                imgDiv.append(overlay);
                $('#thumbnailImageContainer').append(imgDiv);
                imgCount = idx;
            } else {
                let inputHidden1 = $('<input>');
                let inputHidden2 = $('<input>');
                let imgDiv = $('<div>');
                let img = $('<img>');
                let overlay = $('<div>');
                let xMark = $('<div>');

                // 이미지 태그 옵션
                img.attr('src', photo.img).attr('width', '100').attr('height', '60');

                inputHidden1
                    .attr('type', 'hidden')
                    .attr('value', photo.lecturePhotoId)
                    .attr('name', 'revisePhotoList[' + idx + '].lecturePhotoId');
                inputHidden2
                    .attr('type', 'hidden')
                    .attr('name', 'revisePhotoList[' + idx + '].state')
                    .attr('class', 'state')
                    .val('default');
                imgDiv.addClass('imagePanel ' + idx);
                overlay.addClass('reviseLectureImgOverlay');
                xMark.addClass('deleteImg');

                imgDiv.append(inputHidden1);
                imgDiv.append(inputHidden2);
                imgDiv.append(img);
                imgDiv.append(overlay);
                overlay.append(xMark);
                $('#imageContainer').append(imgDiv);
                imgCount = idx;
            }
        });
    },
    setThumbnail: function (event) {
        $('#thumbnailImageContainer').empty();

        let reader = new FileReader();

        reader.onload = function (event) {
            let inputHidden2 = $('<input>');
            let imgDiv = $('<div>');
            let img = $('<img>');
            let overlay = $('<div>');

            // 이미지 태그 옵션
            img.attr('src', event.target.result).attr('width', '100').attr('height', '60');

            inputHidden2.attr('type', 'hidden').attr('name', 'revisePhotoList[0].state').attr('class', 'state').val('update');
            imgDiv.addClass('imagePanel 0');
            overlay.addClass('reviseLectureImgOverlay');

            imgDiv.append(inputHidden2);
            imgDiv.append(img);
            imgDiv.append(overlay);
            $('#thumbnailImageContainer').append(imgDiv);
        };

        reader.readAsDataURL(event.target.files[0]);
    },
    setImage: function (event) {
        let reader = new FileReader();

        reader.onload = function (event) {
            imgCount++;
            let inputHidden2 = $('<input>');
            let inputFile = $('<input>');
            let imgDiv = $('<div>');
            let img = $('<img>');
            let overlay = $('<div>');
            let xMark = $('<div>');

            img.attr('src', event.target.result).attr('width', '100').attr('height', '60');
            $('#reviseFormFiles')
                .attr('name', 'revisePhotoList[' + imgCount + '].file')
                .attr('id', '')
                .attr('class', 'hidden');
            inputHidden2
                .attr('type', 'hidden')
                .attr('name', 'revisePhotoList[' + imgCount + '].state')
                .attr('class', 'state')
                .val('insert');
            inputFile.attr('type', 'file').attr('id', 'reviseFormFiles');

            imgDiv.addClass('imagePanel ' + imgCount);
            overlay.addClass('reviseLectureImgOverlay');
            xMark.addClass('deleteImg');
            let imgTd = $('#imageContainer').parent();
            imgDiv.append(inputHidden2);
            imgDiv.append(img);
            imgDiv.append(overlay);
            overlay.append(xMark);
            $('#imageContainer').append(imgDiv);
            console.log(imgTd);
            imgTd.prepend(inputFile);
        };

        reader.readAsDataURL(event.target.files[0]);
    },
    checkOriginOption: function () {
        optionList.forEach((item) => {
            $('#checkbox' + item.optionId).prop('checked', true);
        });
    },
    execDaumPostcode: function () {
        new daum.Postcode({
            oncomplete: function (data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') {
                    // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else {
                    // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if (data.userSelectedType === 'R') {
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraAddr += extraAddr !== '' ? ', ' + data.buildingName : data.buildingName;
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById('reviseFormAddress').value = extraAddr;
                } else {
                    document.getElementById('reviseFormAddress').value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('reviseFormAddress').value = addr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('reviseFormAddrDetail').focus();

                // 주소로 좌표를 검색
                geocoder.addressSearch($('#reviseFormAddress').val(), function (result, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === kakao.maps.services.Status.OK) {
                        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                        console.log(coords);
                        $('#reviseFormLongitude').val(coords.La);
                        $('#reviseFormLatitude').val(coords.Ma);
                    }
                });

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_layer.style.display = 'none';
            },
            width: '100%',
            height: '100%',
            maxSuggestItems: 5,
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        this.initLayerPosition();
    },
    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    initLayerPosition: function () {
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 400; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = ((window.innerWidth || document.documentElement.clientWidth) - width) / 2 - borderWidth + 'px';
        element_layer.style.top = ((window.innerHeight || document.documentElement.clientHeight) - height) / 2 - borderWidth + 'px';
    },

    // 닫기버튼에 on
    closeDaumPostcode: function () {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    },

    // 유효성 검사
    validateForm: function () {
        $('#reviseFormSubmit').click(function (event) {
            event.preventDefault(); // 제출을 막습니다

            // 입력 필드의 값을 가져옵니다
            let title = $('#reviseFormTitle').val();
            let content = $('#reviseFormContent').val();
            let duration = $('#reviseFormDuration').val();
            let address = $('#reviseFormAddress').val();
            let addressDetail = $('#reviseFormAddrDetail').val();
            let maximum = $('#reviseFormMaximum').val();
            let price = $('#reviseFormPrice').val();
            let phoneNumber = $('#reviseFormPhoneNumber').val();
            // 입력 값이 공백 또는 null인 경우
            if (!title || !content || !duration || !address || !addressDetail || !maximum || !price || !phoneNumber) {
                alert('모든 필드를 입력해주세요.');
            } else {
                alert('수정 성공.');
                $('#reviseForm').submit();
            }
        });
    },
    preventEnterKeySubmit: function () {
        $('#reviseForm').on('keydown', function (event) {
            if (event.key === 'Enter') {
                event.preventDefault();
            }
        });
    },
};
reviseInit.init();
