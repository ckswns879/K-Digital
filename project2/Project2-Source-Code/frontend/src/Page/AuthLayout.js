import React, { useEffect } from "react";
import { useNavigate, useLocation, Outlet } from "react-router-dom";

const AuthLayout = () => {
  const navigate = useNavigate();
  const { pathname } = useLocation();

  useEffect(() => {
    if (!sessionStorage.getItem('accessToken')) {
      alert('로그인 정보가 없습니다.')
      //토큰없으면 로그인화면으로 이동
      navigate("/login", { state: pathname });
    }
  }, []);

  return (
    <div>
      <Outlet />
    </div>
  );
};

export default AuthLayout;