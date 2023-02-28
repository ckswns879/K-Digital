import { useNavigate } from "react-router-dom";
import Mynavbar from "../components/Mynavbar";
import Mynav from "../components/Mynav";
import { useEffect, useState } from "react";

function Page1() {
  const navigate = useNavigate();
  const handle = (k) => {
    navigate(k);
  };

  let [fade2, setFade2] = useState('')

  useEffect(() => {
    setFade2('end')
    return () => {
      setFade2('')
    }
  })

  return (
    <>
      <div className={`start ` + fade2}>
        <div className="page1h1">
          <h1>운전 습관 분석을 통한
            안전 등급 확인</h1>
          <h2>수집한 데이터를 바탕으로 어떤 어떤 방식(*나중에 바꿈) 을 통해 분석한 결과 입니다.</h2>
        </div>

        <Mynav />

      </div>
    </>
  );
}
export default Page1;
