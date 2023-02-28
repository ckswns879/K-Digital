import { useEffect, useState } from "react";
import { Nav, Table } from "react-bootstrap";
import "./Main.css";
import Gobus from "./Gobus";
import PathMap from "./PathMap";



function Mynav1() {
  let [tab, setTab] = useState(0)

  return (
    <>
      <Nav variant="pills" defaultActiveKey="/link0" className="nav1">
        <Nav.Item>

          <Nav.Link onClick={() => { setTab(0) }}
            eventKey="link0">운행주의구간확인</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link onClick={() => { setTab(1) }} eventKey="link1">버스 모의주행</Nav.Link>
        </Nav.Item>
      </Nav>
      <TabContent tab={tab} />
    </>
  );


  function TabContent({ tab }) {
    let [fade, setFade] = useState('')

    useEffect(() => {
      setTimeout(() => { setFade('end1') }, 1000)
      return () => { setFade('') }
    }, [tab])

    return (
      <div className={`start1 ${fade}`}>
        {[
          <div className="tab2-1"><PathMap /></div>,
          <div className="tab2"><Gobus /></div>,

        ][tab]}
      </div>


    );

  }
}
export default Mynav1;
