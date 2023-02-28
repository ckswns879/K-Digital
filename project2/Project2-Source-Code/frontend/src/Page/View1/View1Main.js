import React, { useState, useEffect } from "react";
import { useSelector } from 'react-redux';
import { Link } from "react-router-dom";
import Search from "./Search";
import SelectedList from "./SelectedList";

//검색 페이지
//과거데이터에서 뒤로가기 눌렀을 때 리셋되어있는 현상 해결해야함
// export const AppContext = createContext();
//useContext로도 랜더링 관리가 힘들어진다. Redux 사용
function View1Main() {

  //뒤로 갔을 떄 값 저장을 위해서 redux 사용
  let { SearchInfo } = useSelector((state) => { return state })
  const [visible, setVisible] = useState(false);

  //selectbox에서 선택한 값이 있을때만 리스트(SelectedList)를 보여주기 위한 변수 visible
  useEffect(() => {
    if (SearchInfo === undefined) {
      setVisible(false)
    }
    else if (!SearchInfo[0]) {
      setVisible(false)
    }
    else {
      setVisible(true)
    }
  }, [SearchInfo])

  return (
    <>
      <div className="view1Main">
        <Search />
        {visible && <SelectedList />}
        <button><Link to='/view3'>장바구니 이동</Link></button>
      </div>
    </>
  );
}
export default View1Main;