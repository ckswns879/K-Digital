import { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { getBasketRD } from "../../Component/Store/Store"
import { getBasket } from "../../API/funcAPI";
import BasketList from "./BasketList";
import BasketDate from "./BasketDate";
import OrdDel from "./OrdDel";
// 장바구니 리스트 출력 화면

function View3Main() {

  let dispatch = useDispatch();
  const [component, setComponent] = useState("a");

  useEffect(() => {
    (async () => {
      await getBasket()
        .then((res) => {
          dispatch(getBasketRD(res));
        })
        .catch(() => console.log("데이터가져오기 실패"))
    })();
  }, [])

  const clickBasket = () => {
    setComponent("a")
  }

  const clickDate = () => {
    setComponent("b")
  }

  return (
    <>
      <div className="view3Main">
        <div className="orderNav">
          <div className="basketList" onClick={clickBasket}>장바구니</div>
          <div className="basketDate" onClick={clickDate}>날짜보기</div>
        </div>
        {component === "a" ? <BasketList /> : <BasketDate />}
        <OrdDel />
      </div>
    </>
  );
}
export default View3Main