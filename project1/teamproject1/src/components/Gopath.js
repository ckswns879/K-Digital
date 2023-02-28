import React, { useEffect, useState, } from 'react';
import { useSelector } from 'react-redux';

function Gopath() {
    const { kakao } = window;
    let { gps } = useSelector((state) => { return state })

    useEffect(() => {
        const mapContainer = document.getElementById('map'),
            // 지도를 표시할 div
            mapOption = {
                center: new kakao.maps.LatLng(
                    "35.198742898617816",
                    "129.12954324249225"),
                // 지도의 중심좌표
                level: 7 // 지도의 확대 레벨
            };

        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        const map = new kakao.maps.Map(mapContainer, mapOption);


        const myPath = [gps.map(function (a, i) {
            return (new kakao.maps.LatLng(
                a.car_location_GPS_Y, a.car_location_GPS_X)
            );
        })]


        //마커 좌표
        const Point = new kakao.maps.LatLng(
            gps[cnt]?.car_location_GPS_Y,
            gps[cnt]?.car_location_GPS_X
        )
        // 마커 이미지의 주소1
        const markerImageUrl = 'https://img.icons8.com/plasticine/512/bus.png',
            markerImageSize = new kakao.maps.Size(45, 45), // 마커 이미지의 크기
            markerImageOptions = {
                offset: new kakao.maps.Point(20, 42)// 마커 좌표에 일치시킬 이미지 안의 좌표
            };
        // 마커 이미지를 생성한다 2
        const markerImage = new kakao.maps.MarkerImage(markerImageUrl, markerImageSize, markerImageOptions);

        const marker = new kakao.maps.Marker({
            position: Point,    // 마커의 좌표
            image: markerImage, // 마커의 이미지
            map: map // 마커를 표시할 지도 객체

        },);



        map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);

        // 지도에 선을 표시한다
        const polyline = new kakao.maps.Polyline({
            map: map, // 선을 표시할 지도 객체 
            path: myPath, // 선을 구성하는 좌표 배열
            strokeWeight: 6, // 선의 두께
            strokeColor: 'darkslategray', // 선 색
            strokeOpacity: 0.8, // 선 투명도
            strokeStyle: 'solid', // 선 스타일
            endArrow: 'True'//화살표

        });
        marker.setMap(map); //마커 지도에 출력


    }, [])


    return (
        <>
            <div
                id="map" style={{
                    width: '90%',
                    height: '800px'
                }}>
            </div>
        </>
    );
}

export default Gopath;





