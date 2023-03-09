import "./View3.css";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { getBasketRD } from "../../Component/Store/Store"
import { getBasket } from "../../API/funcAPI";
import BasketDate from "./BasketDate";
import OrdDel from "./OrdDel";
import { BsCartCheck } from "react-icons/bs"
// 장바구니 리스트 출력 화면

function View3Main() {

  let dispatch = useDispatch();

  useEffect(() => {
    (async () => {
      await getBasket()
        .then((res) => {
          dispatch(getBasketRD(res.basket));
        })
        .catch(() => console.log("데이터가져오기 실패"))
    })();
  }, [])


  return (
    <>
      <div className="view3">
        <div className="view3Main">
          <h2 className="carth2"><BsCartCheck/> Cart</h2>
          <BasketDate />
          <OrdDel />
        </div>
      </div>
    </>
  );
}
export default View3Main