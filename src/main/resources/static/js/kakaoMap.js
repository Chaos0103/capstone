var map = document.getElementById('map');
var options = {
    center: new kakao.maps.LatLng(37.54873756651074, 127.07460850115896),
    level: 3
};

var maps = new kakao.maps.Map(map, options);

// 지도를 클릭한 위치에 표출할 마커입니다
var marker = new kakao.maps.Marker({
    // 지도 중심좌표에 마커를 생성합니다 
    position: maps.getCenter()
});
// 지도에 마커를 표시합니다
marker.setMap(maps);

var iwContent = '<div class="col-lg-9"><h6 class = "fw-bold">세종대학교</h6><span style = "font-size: 0.7rem;">서울특별시 광진구 능동로 206</span></div>'// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    position: iwPosition,
    content: iwContent
});

// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
infowindow.open(maps, marker); 

//지도 확대/축소 고정
maps.setZoomable(false);    