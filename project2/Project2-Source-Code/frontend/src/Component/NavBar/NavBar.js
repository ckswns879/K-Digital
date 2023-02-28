import './NavBar.css'
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import jwtDecode from "jwt-decode";

const Nav = () => {

  //어드민일때 admin 아이콘 생성
  const [checkAdmin, setCheckAdmin] = useState(true)
  const decode = () => {
    try{
      jwtDecode(localStorage.getItem('accessToken'))
    } catch(error) {
      console.log(error)
    }
  }
  
  useEffect(()=>{
    if (!decode.auth === "ROLE_ADMIN"){ //임시로 ! 넣어서 user일때도 사용 가능
      setCheckAdmin(false)
    }
  },[])

  const [checkLogin, setCheckLogin] = useState(true)
  

  return (
    <nav className="wrapper">
      <div>Logo</div> 
      <div><Link to='/'>login</Link></div>
      <div><Link to='/view1'>품목검색</Link></div>
      <div><Link to='/view3'>장바구니</Link></div>
      {checkAdmin && <div><Link to='/admin'>관리자</Link></div>}
    </nav>
  );
};

export default Nav;