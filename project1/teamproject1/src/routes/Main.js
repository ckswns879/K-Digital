import "../components/Main.css";
import Mynavbar from "../components/Mynavbar";
import Mapbus from "../components/Gobus";
import { Col, Nav, Row, Tab } from "react-bootstrap";
import { useSelector } from "react-redux";
import Page1 from "./Page1"
import Page2 from "./Page2"
import Service1 from "./Service1";
import Service2 from "./Service2";

function Main() {
  return (
    <>
      <Mynavbar />

      <Tab.Container id="left-tabs-example" defaultActiveKey="p1">
        <Row>
          <Col md={1}>
            <Nav variant="pills" className="flex-column">
              <Nav.Item>
                <Nav.Link eventKey="p1">서비스 소개</Nav.Link>
              </Nav.Item>
              <Nav.Item>
                <Nav.Link eventKey="p2">기대효과</Nav.Link>
              </Nav.Item>
              <Nav.Item>
                <Nav.Link eventKey="p3">안전등급 확인</Nav.Link>
              </Nav.Item>
              <Nav.Item>
                <Nav.Link eventKey="p4">운행정보 분석</Nav.Link>
              </Nav.Item>
            </Nav>
          </Col>
          <Col sm={9}>
            <Tab.Content>
              <Tab.Pane eventKey="p1">
                <Service1 />
              </Tab.Pane>
              <Tab.Pane eventKey="p2">
                <Service2 />
              </Tab.Pane>
              <Tab.Pane eventKey="p3">
                <Page1 />
              </Tab.Pane>
              <Tab.Pane eventKey="p4" >
                <Page2 />
              </Tab.Pane>
            </Tab.Content>
          </Col>
        </Row>
      </Tab.Container>




      {/* <Mysection /> */}

    </>
  );
}
export default Main;


