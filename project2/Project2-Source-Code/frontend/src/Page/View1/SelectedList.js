import { useState, useEffect } from "react";
import { useSelector } from 'react-redux';
import { Link } from "react-router-dom";
import { getSearchResults, addBasket } from "../../API/funcAPI";
import { Paging } from "../../Component/Paging/Paging";

//리스트 출력, 과거데이터 출력, 장바구니 저장
//페이징 기능 필요

function SelectedList() {

  let { SearchInfo } = useSelector((state) => { return state })
  const [data, setData] = useState(); //통신 데이터 저장
  const [checkItems, setCheckItems] = useState([]); //체크한 아이템 저장

  //검색조건이 포함된 리스트 호출(통신 호출할지 redux로 필터링 할지 결정못함)
  useEffect(() => {
    //context값(lead)가 빈값이 아닐때 통신 호출
    if (SearchInfo[0] !== '') {
      (async () => {
        await getSearchResults(SearchInfo)
          .then((res) => {
            //모든 데이터를 datas에 넣기
            setData(res);
          })
      })();
    }
  }, [SearchInfo]);

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

  //전체 선택
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

  //선택저장 버튼 클릭 (장바구니 추가)
  const addItemBasket = () => {
    const arr = []
    //선택한 목록을 배열에 추가
    arr.push(data?.filter((item) => checkItems.includes(item.id)));
    //배열을 db에 저장
    (async () => {
      await addBasket(arr[0])
        .then((res) => res)
    })();
  }

  //pagination 적용 (react-js-pagination)
  const [count, setCount] = useState(0); //아이템 총 개수
  const [currentpage, setCurrentpage] = useState(1); //현재페이지
  const [postPerPage] = useState(10); //페이지당 아이템 개수
  const [indexOfLastPost, setIndexOfLastPost] = useState(0);
  const [indexOfFirstPost, setIndexOfFirstPost] = useState(0);
  const [currentPosts, setCurrentPosts] = useState(0);

  useEffect(() => {
    setCount(data?.length);
    setIndexOfLastPost(currentpage * postPerPage);
    setIndexOfFirstPost(indexOfLastPost - postPerPage);
    setCurrentPosts(data?.slice(indexOfFirstPost, indexOfLastPost));
  }, [currentpage, indexOfFirstPost, indexOfLastPost, data, postPerPage]);

  //페이지 변경할때마다 발생하는 이벤트(새로운 페이지 입력)
  const setPage = (e) => {
    setCurrentpage(e);
  };

  return (
    <>
      <div className="searchList">
        <table>
          <thead>
            <tr>
              {/* 체크박스 전체 클릭 */}
              <th><input type={'checkbox'} onChange={(e) => handleAllCheck(e.target.checked)}
                checked={checkItems?.length === data?.length ? true : false}></input></th>
              <th>Machinery</th>
              <th>청구품목</th>
              <th>Part.No</th>
              <th>카테고리</th>
              <th>발주처</th>
              <th>리드타임(일)</th>
              <th>견적화폐</th>
              <th>견적단가</th>
            </tr>
          </thead>
          <tbody>
            {currentPosts && data.length > 0 ? currentPosts.map((item, index) => (
              //테이블 클릭하여 저장된 정보를 새로운 테이블로 출력(장바구니 같은 개념으로 생각중)
              //클릭하면 테이블에서 삭제하는 코드(현재 안씀)
              // <tr key={index} onClick={() => setRowdata(rowdata.filter(ritem => ritem.id !== item.id))}>
              <tr key={index} >
                {/* 체크박스 클릭 */}
                <td><input type={'checkbox'} onChange={(e) => handleSingleCheck(e.target.checked, item.id)}
                  checked={checkItems.includes(item.id) ? true : false}></input></td>
                {/* 클릭하면 과거 데이터로 이동(뒤로가기하면 view1이 리셋되는 현상 해결해야함) */}
                {/* 한 행 전체를 link로 걸면 체크박스를 클릭해도 과거데이터로 이동해버림 */}
                <td><Link to='/view2' state={item}>{item.machinery}</Link></td>
                <td>{item.items}</td>
                <td>{item.part1}</td>
                <td>{item.key2}</td>
                <td>{item.baljucheo}</td>
                <td>{item.leadtime}</td>
                <td>{item.gyeonjeokhwapye}</td>
                <td>{item.gyeonjeokdanga}</td>
              </tr>
            ))
              : <></>}
          </tbody>
        </table>
      </div>
      {/* 페이징 기능 */}
      {count && <Paging page={currentpage} count={count} setPage={setPage} />}
      {checkItems.length > 0 && <button onClick={removeRow}>선택 삭제</button>}
      {checkItems.length > 0 && <button onClick={addItemBasket}>선택 저장</button>}
    </>
  );
}
export default SelectedList