import "./Login.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { login, signup } from "../../API/funcAPI";

function Login() {

  const navigate = useNavigate();
  const [inputId, setInputId] = useState('')
  const [inputPw, setInputPw] = useState('')

  useEffect(() => {
    if (sessionStorage.getItem('accessToken')) {
      navigate("/view1")
    }
  }, [])

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
            sessionStorage.setItem('accessToken', res?.accessToken);
            //현재는 refreshtoken도 localstorage에 저장하지만 보안을 위해서는 cookie에 저장하는 것으로 변경해야 할듯...
            sessionStorage.setItem('refreshToken', res?.refreshToken);
            alert('login success');
            window.location.replace("/view1") //navigate는 랜더링이 안되서 필요한 정보들이 안떠서 이 명령어를 사용
            // navigate("/view1")  //로그인 성공하면 view1으로 이동
          } else {
            console.log("login false");
            alert('login false');
          }
        })
    })();
  }

  //회원가입 버튼(따로 화면 구성할 필요 있어보임)
  const onClickSignup = () => {
    navigate("/signin")
    // const requestBody = {
    //   "email": inputId,
    //   "password": inputPw
    // };
    // (async () => {
    //   await signup(requestBody)
    //     .then((res) => {
    //       if (res !== undefined) {
    //         console.log("res", res);
    //         alert('signin success');
    //         navigate("/");  //가입 성공하면 화면 
    //       } else {
    //         alert('wrong id/password');
    //       }
    //     })
    // })();
  }

  //로그아웃 구현--네비게이션 바에서 가능하도록 이동

  return (
    <div className="loginView">
      <div className="loginBox">
        <h1 className="login">Login</h1>
        <div>
          <input className="inputBox" type='text' placeholder="Username" name='input_id' value={inputId} onChange={handleInputId} />
        </div>
        <div>
          <input className="inputBox" type='password' placeholder="Password" name='input_pw' value={inputPw} onChange={handleInputPw} />
        </div>
        <div className="Butttons">
          <button className="loginButton" type='button' onClick={onClickLogin}>Sign In</button>
          <button className="loginButton" type='button' onClick={onClickSignup}>Sign Up</button>
        </div>
      </div>
    </div>
  )
}
export default Login