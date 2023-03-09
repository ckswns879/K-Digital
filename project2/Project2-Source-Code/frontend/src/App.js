import './App.css';
import { Route, Routes } from 'react-router-dom';
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { getSelectListRD } from "./Component/Store/Store"
import { getList } from './API/funcAPI';
import AuthLayout from './Page/AuthLayout';
import AdminLayout from './Page/AdminLayout';
import Home from './Page/Home/Home';
import View1Main from './Page/View1/View1Main';
import View2Main from './Page/View2/View2Main';
import View3Main from './Page/View3/View3Main';
import Login from './Page/View4/Login';
import SignIn from './Page/View4/SignIn';
import Admin from './Page/View5/Admin';
import NavBar from './Component/NavBar/NavBar'

//라우터 구조, 순서 생각할 필요 있음
//1. 기본화면 '/' 어떤 view로 잡을 건지
//2. 페이지가 실행되면 기본이 로그인화면인지 아니면 1에서 설정한 기본화면인지
//==> 로그인이 기본화면

function App() {

  let dispatch = useDispatch();

  //어플이 시작되면 통신해서 선택용 리스트 호출
  useEffect(() => {
    (async () => {
      await getList()
        .then((res) => dispatch(getSelectListRD(res)))
        .catch(() => console.log("데이터가져오기 실패"))
    })();
  }, []);


  return (
    <>
    {/* 상단 네비게이션 바는 아무거나 일단 넣어놈 */}
      <NavBar/>
      
      <Routes>        
        <Route>
          <Route path='/' element={<Home />} />
          <Route path='/login' element={<Login />} />
          <Route path='/signin' element={<SignIn />} />
        </Route>
        <Route element={<AdminLayout/>}>
          <Route path='/admin' element={<Admin />} />
        </Route>
        <Route element={<AuthLayout/>}> {/* 아래 페이지는 로그인으로 토큰 정보가 없으면 접근이 안되도록 함(로그인화면으로 자동 이동) */}
          <Route path='/view1' element={<View1Main />} />
          <Route path='/view2' element={<View2Main />} />
          {/* view2Main은 url로 입력하면 에러가 먼저 나서 로그인 화면으로 이동이 안됨 */}
          <Route path='/view3' element={<View3Main />} />
        </Route>
      </Routes>

    </>
  );
}

export default App;
