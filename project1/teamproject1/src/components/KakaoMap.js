import React, { useEffect, useState, useRef } from "react";
import { useSelector } from 'react-redux';

export default function KakaoMap(props) {
    const { kakao } = window;
    const container = useRef();
    const { markerPositions, size, buspaths, cnt } = props;
    const [kakaoMap, setKakaoMap] = useState(null);
    const [, setMarkers] = useState([]);
    const [, setPolyline] = useState([]);
    let { gps } = useSelector((state) => { return state })



    useEffect(() => {
        const script = document.createElement("script");
        script.src =
            "https://dapi.kakao.com/v2/maps/sdk.js?appkey=2d8c8539b5431cee512032a5fa17e024&autoload=false";
        document.head.appendChild(script);

        script.onload = () => {
            kakao.maps.load(() => {
                const center = new kakao.maps.LatLng(35.1988, 129.12395);
                // 지도의 중심좌표
                const options = {
                    center,
                    level: 7
                    // 지도의 확대 레벨
                };

                const map = new kakao.maps.Map(container.current, options);
                // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
                // setMapCenter(center);
                setKakaoMap(map);
                map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
                // 교통정보 지도로 출력변경

            });
        };
    }, [container]);

    useEffect(() => {
        if (kakaoMap === null) {
            return;
        }
        // save center position
        const center = kakaoMap.getCenter();

        // change viewport size
        const [width, height] = size;
        container.current.style.width = `${width}px`;
        container.current.style.height = `${height}px`;

        // relayout and...
        kakaoMap.relayout();
        // restore
        kakaoMap.setCenter(center);

    }, [kakaoMap, size]);

    useEffect(() => {
        if (kakaoMap === null) {
            return;
        }

        const positions = markerPositions.map(pos => new kakao.maps.LatLng(...pos));
        const paths = buspaths.map(pos => new kakao.maps.LatLng(...pos));


        const path = [gps.map((a) =>
            new kakao.maps.LatLng(
                a.car_location_GPS_Y,
                a.car_location_GPS_X
            )
        )]
        // #DC143C   https://img.icons8.com/plasticine/512/bus.png
        setPolyline(polylines => {
            polylines.forEach(i => i.setMap(null));
            return paths.map(() => new kakao.maps.Polyline({
                map: kakaoMap,
                path: path, //선의 구성하는 좌표 배열 입니다.
                strokeColor: 'indigo', //선의 색상입니다.
                strokeWeight: 3, // 선의 두께 입니다
                strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                strokeStyle: 'line', // 선의 스타일입니다
                endArrow: true,
            })

            );
        });

        var marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(gps[10]?.car_location_GPS_Y, gps[10]?.car_location_GPS_X),
            image: new kakao.maps.MarkerImage(
                'https://cdn2.iconfinder.com/data/icons/3d-transport/512/Bus-Blue.png',
                new kakao.maps.Size(130, 130),

            )
        });

        const markerImageUrl = 'https://cdn2.iconfinder.com/data/icons/alert-message/64/siren-light-exclamation-icon-512.png',
            markerImageSize = new kakao.maps.Size(35, 35), // 마커 이미지의 크기
            markerImageOptions = {
                offset: new kakao.maps.Point(20, 42)// 마커 좌표에 일치시킬 이미지 안의 좌표
            };
        // 마커 이미지를 생성
        const markerImage = new kakao.maps.MarkerImage(markerImageUrl, markerImageSize, markerImageOptions);



        setMarkers(markers => {
            // clear prev markers
            markers.forEach(i => i.setMap(null));
            // assign new markers
            return positions.map(
                position => new kakao.maps.Marker({
                    map: kakaoMap,
                    position,
                    image: markerImage,
                    opacity: 1,
                })
            );
        });

        marker.setMap(kakaoMap);

        if (paths.length > 0) {
            const bounds = paths.reduce(
                (bounds, latlng) => bounds.extend(latlng),
                new kakao.maps.LatLngBounds()
            );
            kakaoMap.setBounds(bounds);
        }

        // if (positions.length > 0) {
        //     const bounds = positions.reduce(
        //         (bounds, latlng) => bounds.extend(latlng),
        //         new kakao.maps.LatLngBounds()
        //     );

        //     kakaoMap.setBounds(bounds);
        // }

    }, [kakaoMap, markerPositions, buspaths, gps]);

    return <div id="container" ref={container} />;
}