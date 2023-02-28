import { useEffect, useState } from "react";
import { Nav, Table } from "react-bootstrap";
import { useSelector } from "react-redux";
import Mytable from "./Mytable";
import Mytable1 from "./Mytable1";
import Mysearch from "./Mysearch";
import Mychart from "./Mychart";
import "./Main.css";
import PathMap from "./PathMap";
import Grade from "./Grade";


function Mynav(props) {
  let { drive } = useSelector((state) => { return state })

  let [tab, setTab] = useState(0)

  return (
    <>
      <Nav variant="pills" defaultActiveKey="/link0" className="nav1">
        <Nav.Item>
          <Nav.Link onClick={() => { setTab(0) }}
            eventKey="link0">모든 차량 주행기록 조회</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link onClick={() => { setTab(1) }} eventKey="link2">차량별 안전등급 확인</Nav.Link>
        </Nav.Item>
      </Nav>
      <TabContent tab={tab} />
    </>
  );


  function TabContent({ tab }) {
    let [fade, setFade] = useState('')

    useEffect(() => {
      setTimeout(() => { setFade('end1') }, 100)
      return () => { setFade('') }
    }, [tab])

    return (
      <div className={`start1${fade}`}>
        {[<div className="tab1">
          <>
            <Mytable />
          </>
        </div>,

        <div className="tab2">
          <Grade />
          {/* <Mychart /> */}
        </div>


        ][tab]}
      </div>

    );

  }
}
export default Mynav;
