import { useLocation } from "react-router-dom";
import { useSelector } from 'react-redux';
import { useState, useEffect } from "react";
import RandomDate from "../../Component/RamdomDate/RandomDate";
import Graph from "./Graph";
import Recommendation from "./Recommendation";
import './View2.css';

//과거 데이터 출력 페이지
//추천 목록 출력

function View2Main(props) {

  const location = useLocation();
  let { SelectList } = useSelector((state) => { return state }) //view1에서 검색한 정보
  const [data, setData] = useState(location.state);
  const [pageTitle, setPageTitle] = useState(null);
  const [oldList, setOldList] = useState(null);

  //선택 상품의 과거 정보 호출
  useEffect(()=>{
    const oldData = [];
    SelectList.map((item)=>{
    if(item.machinery === data.machinery && item.items === data.items && item.part1 === data.part1){
      setPageTitle(data.machinery + " > " + data.items + " > " + data.part1)
      oldData.push({
        "x": item.leadtime, 
        "y": RandomDate()
      })
    }});
    setOldList(oldData);    
  },[])

  return (
    <>
      <div className="view2Main">
        <div className="title"><h3 className="h3">{pageTitle} 상품의 최근 리드타임 내역</h3></div>
        <div className="ai">
        <Graph props={oldList} />
        <Recommendation props={data} />
        </div>
      </div>
    </>
  );
}
export default View2Main