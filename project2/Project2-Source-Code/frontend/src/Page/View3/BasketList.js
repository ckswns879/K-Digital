import { useState, useEffect, useCallback } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { getBasketListRD } from "../../Component/Store/Store";

function BasketList() {

  let dispatch = useDispatch();
  let { Basket } = useSelector((state) => { return state })
  const [data, setData] = useState();
  const [category, setCategory] = useState();
  const [checkItems, setCheckItems] = useState([]);

  const fixPrice = useCallback(price => {
    return parseInt(price.toFixed(0)).toLocaleString();
  }, []);

  //main에서 통신 호출된 장바구니를 state에 담기
  useEffect(() => {
    setData(Basket);
    setCategory(Basket.map((item) => item.items.category));
  }, [Basket])

  useEffect(() => {
    dispatch(getBasketListRD(checkItems));
  }, [checkItems])

  //통신한 데이터를 key2별로 sort
  const key2 = [...new Set(category)];
  key2.sort()

  // 체크박스 단일 선택
  const handleSingleCheck = (checked, id) => {
    if (checked) {
      // 단일 선택 시 체크된 아이템을 배열에 추가
      setCheckItems(prev => [...prev, id]);
    } else {
      // 단일 선택 해제 시 체크된 아이템을 제외한 배열 (필터)
      setCheckItems(checkItems.filter((el) => el !== id));
    }
  };

  const handleAllCheck = (checked) => {
    if (checked) {
      // 전체 선택 클릭 시 데이터의 모든 아이템(id)를 담은 배열로 checkItems 상태 업데이트
      const idArray = [];
      data.forEach((el) => idArray.push(el.id));
      setCheckItems(idArray);
    }
    else {
      // 전체 선택 해제 시 checkItems 를 빈 배열로 상태 업데이트
      setCheckItems([]);
    }
  }

  //체크박스 선택한 행 삭제
  const removeRow = () => {
    setData(data.filter((item) =>
      !checkItems.includes(item.id)
    ))
  }

  const sortByName = () => {
    //카테고리별로 분류
    setData([...data].sort((a, b) => a.items.machinery.localeCompare(b.items.machinery)));
  };

  return (
    <>
      <h2 className="cart">장바구니</h2>      
      <div className="OrderList">
        {key2.map((kitem, index) =>
          <div className="byCategory" key={index}>
            <div className="categoryName"> {kitem} </div>
            <table className="orderTable">
              <thead>
                <tr>
                  <th></th>
                  <th className="th2">카테고리</th>
                  <th className="th2" onClick={sortByName}>Machinery</th>
                  <th className="th2">청구품목</th>
                  <th className="th2">Part.No</th>
                  <th className="th2">발주처</th>
                  <th className="th2">리드타임(일)</th>
                  <th className="th2">견적화폐</th>
                  <th className="th2">견적단가</th>
                </tr>
              </thead>
              <tbody>
                {data.filter((item) => kitem.includes(item.items.category))
                  .map((item, index) => (
                    <tr key={index}>
                      {/* 똑같은 항목이 여러개 들어가면 전부 다 체크되는 상황 */}
                      <td><input type={'checkbox'} onChange={(e) => handleSingleCheck(e.target.checked, item.id)}
                        checked={checkItems.includes(item.id) ? true : false}></input></td>
                      <td>{item.items.category}</td>
                      <td>{item.items.machinery}</td>
                      <td>{item.items.items}</td>
                      <td>{item.items.part1}</td>
                      <td>{item.items.clients}</td>
                      <td>{item.items.leadtime}</td>
                      <td>{item.items.currency}</td>
                      <td>{fixPrice(parseInt(item.items.esti_unit_price))}</td>
                    </tr>
                  ))
                }
              </tbody>
            </table>
            <div className="leadtimeByCategory">
              {/* 각 카테고리별 리드타임중 큰값 출력 */}
              {/* {kitem} */}
              <div> 총 {(data.filter((item) => kitem.includes(item.items.category))).length}개</div>
              <div>소요 예상일: {Math.max(...data.filter((item) => kitem.includes(item.items.category)).map((i) => i.items.leadtime))}(일)</div>
            </div>
          </div>
        )}
      </div>
    </>
  );
}
export default BasketList