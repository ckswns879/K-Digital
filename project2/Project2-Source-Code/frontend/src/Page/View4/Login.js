import { useEffect, useState } from "react";
import { useNavigate, useLocation, Outlet } from "react-router-dom";
import { login, signup } from "../../API/funcAPI";

function Login() {

  const navigate = useNavigate();
  const [inputId, setInputId] = useState('')
  const [inputPw, setInputPw] = useState('')

  useEffect(()=>{
    if (localStorage.getItem('accessToken')){
      navigate("/view1")
    }
  },[])

  // input data 의 변화가 있을 때마다 value 값을 변경해서 useState 해준다
  const handleInputId = (e) => {
    setInputId(e.target.value)
  }

  const handleInputPw = (e) => {
    setInputPw(e.target.value)
  }

  // login 버튼 클릭 이벤트()
  const onClickLogin = () => {
    const requestBody = {
      "email": inputId,
      "password": inputPw
    };
    (async () => {
      await login(requestBody)
        .then((res) => {
          if (res?.accessToken) {
            localStorage.setItem('accessToken', res?.accessToken);
            localStorage.setItem('refreshToken', res?.refreshToken);
            alert('login success');
            navigate("/view1")  //로그인 성공하면 view1으로 이동
          } else {
            console.log("login false");
            alert('login false');
          }
        })
    })();
  }

  //회원가입 버튼(따로 화면 구성할 필요 있어보임)
  const onClickSignup = () => {
    const requestBody = {
      "email": inputId,
      "password": inputPw
    };
    (async () => {
      await signup(requestBody)
        .then((res) => {
          if (res !== undefined){
            console.log("res", res);
            alert('signin success');
            navigate("/");  //가입 성공하면 화면 
          } else {
            alert('wrong id/password');
          }
        })
    })();
  }
  
  //로그아웃 구현
  //토큰이 있으면 로그인으로 판단하여 시스템 사용 가능하기 때문에 토큰을 삭제하여 시스템을 사용하지 못하게 함
  //좀더 다양한 방법을 사용 가능
  //네비게이션에서 로그인하는 방법 찾아보기
  //로그아웃 시에 본인확인...
  //브라우저 종료시 토큰 삭제
  const onClickLogout = () => {
    try{
      localStorage.removeItem('accessToken');
      localStorage.removeItem('refreshToken');
      alert('logout success');
    } catch(error){
      console.log(error);
      alert('logout false')
    }    
  }

  return (
    <div className="loginView">
      <h2>Login</h2>
      <div className="inputId">
        <label htmlFor='input_id'>ID : </label>
        <input type='text' name='input_id' value={inputId} onChange={handleInputId} />
      </div>
      <div className="inputPs">
        <label htmlFor='input_pw'>PW : </label>
        <input type='password' name='input_pw' value={inputPw} onChange={handleInputPw} />
      </div>
      <div className="Butttons">
        <button type='button' onClick={onClickLogin}>Login</button>
        <button type='button' onClick={onClickSignup}>Signin</button>
        <button type='button' onClick={onClickLogout}>Logout</button>
      </div>
    </div>
  )
}
export default Login