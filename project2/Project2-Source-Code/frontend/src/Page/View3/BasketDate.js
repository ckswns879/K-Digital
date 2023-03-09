import "./View3.css"
import "react-calendar/dist/Calendar.css";
import React, { useState, useEffect, useCallback } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { getBasketListRD } from "../../Component/Store/Store";
import { useNavigate } from "react-router-dom";
import moment from 'moment';
import Calendar from 'react-calendar';


function BasketDate() {

  let dispatch = useDispatch();
  const navigate = useNavigate();
  let { Basket } = useSelector((state) => { return state })
  const [checkItems, setCheckItems] = useState([]);
  const [data, setData] = useState();
  const [category, setCategory] = useState();
  const [orderDue, setOrderDue] = useState();
  const [value, onChange] = useState(new Date());

  const fixPrice = useCallback(price => {
    return parseInt(price.toFixed(0)).toLocaleString();
  }, []);

  //현재 조금 이상한 구조. 스프링부트에서 장바구니를 받아와서 그 데이터를 다시 플라스크로 보내서 리드타임 예측해옴
  //좀더 효율적으로 변경할 필요 있음
  useEffect(() => {
    setData(Basket)
    setCategory(Basket.map((i) => i.items.category));
  }, [Basket])

  //체크박스 선택한 아이템을 redux에 입력
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

  useEffect(() => {
    const possible = [];
    const impossible = [];
    const urgent = [];
    Basket.map((i) => {
      if (ramainTime - i.items.leadtime > 7) {
        possible.push(i.items.id)
      } else if (ramainTime - i.items.leadtime > 0) {
        urgent.push(i.items.id)
      } else {
        impossible.push(i.items.id)
      }
    })
    setOrderDue(
      <ul className="ox">
        <li>입항일: {moment(value).format("YYYY-MM-DD")}</li>
        <br/>
        <li>⭕ 주문 가능 : {possible.length}</li>
        <li className="li">❗❗ 주문 임박 : {urgent.length}</li>
        <li>❌ 주문 불가 : {impossible.length}</li>
      </ul>
    )
  }, [value])

  //남은 날짜 계산(목표 날짜 - 오늘 날짜)
  //아래 테이블에서 (위의 남은 날짜 - 리드타임)해서 몇일 안에 발주해야하는지 계산
  const today = moment(new Date());
  const selectDate = moment(value);
  const ramainTime = Math.ceil(moment.duration(selectDate.diff(today)).asDays());

  //입력된 params 별로 정렬
  const sortByMachinery = (params) => {
    if (params === "leadtime"){      
      setData([...data].sort((a, b) => a['predictLead'] - b['predictLead']));    
    } else if (params === "esti_unit_price") {
      setData([...data].sort((a, b) => a['items'][`${params}`] - b['items'][`${params}`]));
    } else {
    setData([...data].sort((a, b) => a['items'][`${params}`].localeCompare(b['items'][`${params}`])));
    }
  };

  const handleClicktd = (item) => {
    navigate('/view2', { state: item })
  }

  return (
    <>
      <div className="View3Main">
        <div className="calender">
          <Calendar className="calendar" locale="en-EN" onChange={onChange} value={value} />
          {orderDue}
        </div>
        <div className="OrderList">
        {key2.map((kitem, index) =>
          <div className="byCategory" key={index}>
            <div className="categoryName"> {kitem} </div>
            <table className="orderTable">
              <thead>
                <tr>
                  <th></th>
                  <th className="th2">카테고리</th>
                  <th className="th2" onClick={(e)=>sortByMachinery('machinery')}>Machinery</th>
                  <th className="th2" onClick={(e)=>sortByMachinery('items')}>청구품목</th>
                  <th className="th3" onClick={(e)=>sortByMachinery('part1')}>Part.No</th>
                  <th className="th2" onClick={(e)=>sortByMachinery('clients')}>발주처</th>
                  <th className="th3" onClick={(e)=>sortByMachinery('leadtime')}>리드타임(일)</th>
                  <th className="th3" onClick={(e)=>sortByMachinery('currency')}>견적화폐</th>
                  <th className="th3" onClick={(e)=>sortByMachinery('esti_unit_price')}>견적단가</th>
                  <th className="th1"></th>
                </tr>
              </thead>
              <tbody>
                {data.filter((item) => kitem.includes(item.items.category))
                  .map((item, index) => (
                    <tr key={index}>
                      {/* 똑같은 항목이 여러개 들어가면 전부 다 체크되는 상황 */}
                      <td><input type={'checkbox'} onChange={(e) =>{
                        if(ramainTime - item.items.leadtime<=0){
                          return alert("주문 불가능")
                        };
                        handleSingleCheck(e.target.checked, item.id)}}
                        checked={checkItems.includes(item.id) ? true : false}></input></td>
                      <td onClick={(e) => handleClicktd(item.items)}>{item.items.category}</td>
                      <td onClick={(e) => handleClicktd(item.items)}>{item.items.machinery}</td>
                      <td onClick={(e) => handleClicktd(item.items)}>{item.items.items}</td>
                      <td onClick={(e) => handleClicktd(item.items)}>{item.items.part1}</td>
                      <td onClick={(e) => handleClicktd(item.items)}>{item.items.clients}</td>
                      <td onClick={(e) => handleClicktd(item.items)}>{item.predictLead}</td>
                      <td onClick={(e) => handleClicktd(item.items)}>{item.items.currency}</td>
                      <td onClick={(e) => handleClicktd(item.items)}>{fixPrice(parseInt(item.items.esti_unit_price))}</td>
                      <td onClick={(e) => handleClicktd(item.items)} className="th1">{ramainTime - item.items.leadtime > 7 ? "" :
                    ramainTime - item.items.leadtime > 0 ? `${ramainTime - item.items.leadtime}일이내 주문 필요` : "주문불가능"}</td>
                    </tr>
                  ))
                }
              </tbody>
            </table>
            <div className="leadtimeByCategory">
              {/* 각 카테고리별 리드타임중 큰값 출력 */}
              {/* {kitem} */}
              <div> 총 {(data.filter((item) => kitem.includes(item.items.category))).length}개</div>
              <div>소요 예상일: {Math.max(...data.filter((item) => kitem.includes(item.items.category)).map((i) => i.predictLead))}(일)</div>
            </div>
          </div>
        )}
      </div>
      </div>
    </>
  );
}
export default BasketDate
