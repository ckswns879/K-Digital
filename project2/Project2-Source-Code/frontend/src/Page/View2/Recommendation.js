import { useState, useEffect } from "react";
import { recommendation } from "../../API/funcAPI";

function Recommendation(props) {
  const [recommendList, setRecommendList] = useState();
  const [listStatus, setListStatus] = useState();

  useEffect(() => {
    const data = {"items": props['props'].items};
    (async () => {
      await recommendation(data)
        .then((res) => setRecommendList(res))
        .catch((error) => console.log(error))
    })();
  }, [])

  //추천 상품 검색이 시간이 걸려서 아직 통신 완료 안되있을 때 메세지 띄움
  useEffect(() => {
    if (recommendList === undefined) {
      setListStatus(<div className="load">추천 결과를 호출 중입니다.</div>)
    } else if (!recommendList) {
      setListStatus(<div className="none">추천 상품이 없습니다.</div>)
    } else {
      setListStatus(
        <div>
          <table className="recTable">
            <thead>
              <tr>
                <th>Machinery</th>
                <th>Assembley</th>
                <th>청구품목</th>
                <th>PartNo</th>
              </tr>
            </thead>
            <tbody>
              {recommendList?.map((item, index) => (
                //테이블 클릭하여 저장된 정보를 새로운 테이블로 출력(장바구니 같은 개념으로 생각중)
                //클릭하면 테이블에서 삭제하는 코드(현재 안씀)
                // <tr key={index} onClick={() => setRowdata(rowdata.filter(ritem => ritem.id !== item.id))}>
                <tr key={index} >
                  <td>{item.machinery}</td>
                  <td>{item.Assembly}</td>
                  <td>{item.items}</td>
                  <td>{item.part1}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )
    }
  }, [recommendList])

  return (
    <div className="rec">
      <h4 className="announce">해당 물품과 관련된 추천 상품입니다.</h4>
      {listStatus}
    </div>
  );
}

export default Recommendation