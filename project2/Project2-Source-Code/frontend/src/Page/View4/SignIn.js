import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { login, signup } from "../../API/funcAPI";

function SignIn(){

  const navigate = useNavigate();
  const [inputId, setInputId] = useState('')
  const [inputPw, setInputPw] = useState('')

  const handleInputId = (e) => {
    setInputId(e.target.value)
  }

  const handleInputPw = (e) => {
    setInputPw(e.target.value)
  }

  const onClickLogin = () => {
    navigate("/login");
  }

  const onClickSignup = () => {
    const requestBody = {
      "email": inputId,
      "password": inputPw
    };
    (async () => {
      await signup(requestBody)
        .then((res) => {
          if (res !== undefined) {
            console.log("res", res);
            alert('signin success');
            navigate("/");  //가입 성공하면 화면 
          } else {
            alert('wrong id/password');
          }
        })
    })();
  }

  return(
    <div className="loginView">
      <div className="loginBox">
        <h1 className="login">Sign Up</h1>
        <div>
          <input className="inputBox" type='text' placeholder="Username" name='input_id' value={inputId} onChange={handleInputId} />
        </div>
        <div>
          <input className="inputBox" type='password' placeholder="Password" name='input_pw' value={inputPw} onChange={handleInputPw} />
        </div>
        <div className="Butttons">
          <button className="loginButton" type='button' onClick={onClickLogin}>Back to Sign In</button>
          <button className="loginButton" type='button' onClick={onClickSignup}>Sign Up</button>
        </div>
      </div>
    </div>
  );
}
export default SignIn