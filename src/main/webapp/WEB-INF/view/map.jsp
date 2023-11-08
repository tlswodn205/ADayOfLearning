<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<main>
	<div id="lectureMapPage" class="mainColumn">
		<div class="areaMapContainer">
			지도
			<div class="map" id="map" style="width: 500px; height: 500px">
				
			</div>
			<div id="result"></div>
		</div>
	</div>
</main>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=af13f32c842ba98c342a15aeef40f5e4&libraries=services"></script>
<script src="/js/lectureMap.js"></script>
<script type="text/javascript">
    let lastLecture = ${lastLecture};
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>