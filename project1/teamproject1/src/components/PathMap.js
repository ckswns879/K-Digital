import React, { useState } from "react";
import { useSelector } from 'react-redux';
import KakaoMap from "./KakaoMap";
import "./Main.css";
import styled from 'styled-components';
import ToggleButton from 'react-bootstrap/ToggleButton';
import ToggleButtonGroup from 'react-bootstrap/ToggleButtonGroup';
import { Container, Row, Col } from "react-bootstrap";

export default function PathBus() {
    const [visible, setVisible] = useState(true);

    const [mapSize, setMapSize] = useState([500, 500]);
    const [markerPositions, setMarkerPositions] = useState([]);
    const [buspaths, setBuspaths] = useState([]);

    const { gps } = useSelector((state) => { return state })


    let Box5 = styled.div`padding-bottom:5px;`;
    let Box20 = styled.div`padding : 10px;`;
    let Box30 = styled.div` padding : 30px;`;

    const marker1 = gps.filter((a) => a.sra !== 0).map((a) => {
        return [a.car_location_GPS_Y, a.car_location_GPS_X];
    }
    )
    //운행판별 sra값이 0이 아닌 구간에 대해 주의 마커 필터링
    // console.log(marker1)

    const path1 = gps.filter((a) => a).map((a) => {
        return [a.car_location_GPS_Y, a.car_location_GPS_X];
    }
    )
    //버스 운행경로 위도경도 값 배열에 저장
    // information_date




    return (
        <>
            <div id="wrap">
                <Container>
                    <Row>
                        <Col sm={8}>    {visible && (
                            <>
                                <Box5 />
                                <h2>Kakao Map API</h2>
                                <KakaoMap markerPositions={markerPositions} size={mapSize}
                                    buspaths={buspaths}
                                />
                            </>
                        )}
                            <Box30 />
                            <Box30 /></Col>
                        <Col sm={4} ><ToggleButtonGroup type="checkbox" defaultValue={[1, 3]} className="mb-2">
                            <ToggleButton onClick={() => setVisible(!visible)} id="tbg-check-1" value={1}>
                                지도 숨기기
                            </ToggleButton>
                            <ToggleButton onClick={() => setMapSize([1000, 1000])} id="tbg-check-2" value={2}>
                                크게보기
                            </ToggleButton>
                            <ToggleButton onClick={() => setMapSize([500, 500])} id="tbg-check-3" value={3}>
                                작게보기
                            </ToggleButton>
                        </ToggleButtonGroup>
                            <Box5 />
                            <ToggleButtonGroup type="checkbox" defaultValue={[1, 3]} className="mb-2">
                                <ToggleButton onClick={() => setBuspaths(path1)} id="tbg-check-1" value={1}>
                                    버스 운행 경로 보기
                                </ToggleButton>
                                <ToggleButton onClick={() => setBuspaths([])} id="tbg-check-2" value={2}>
                                    운행경로 지우기
                                </ToggleButton>

                            </ToggleButtonGroup>
                            <Box5 />
                            <ToggleButtonGroup type="radio" name="options" defaultValue={1} className="mb-2">
                                <ToggleButton onClick={() => setMarkerPositions(marker1)} id="tbg-radio-1" value={1}>
                                    운행 주의 구간 보기
                                </ToggleButton>
                                <ToggleButton onClick={() => setMarkerPositions([])} id="tbg-radio-2" value={2}>
                                    마커 지우기
                                </ToggleButton>
                            </ToggleButtonGroup>
                        </Col>
                    </Row>
                </Container>
            </div>
        </>
    );
}
