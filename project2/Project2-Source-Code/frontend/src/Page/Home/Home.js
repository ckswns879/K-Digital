import monitor from '../../Img/monitor.png'
import scimg from '../../Img/screen.png'
import scimg1 from '../../Img/screen1.png'
import scimg2 from '../../Img/screen2.png'
import scimg3 from '../../Img/screen3.png'
import './Home.css'
import { Link } from "react-router-dom";

function Home() {

  return (
    <div className='home'>
      <div className='bgsm'>
        <div className='sc1'></div>
        <div className='sc2'></div>
        <div className='sc3'></div>
      </div>
      <div className='bglg'>
        <div className='circle'></div>
      </div>
      <img className='monitor' src={monitor} />
      <img className='scimg' src={scimg} />
      <img className='scimg1' src={scimg1} />
      <img className='scimg2' src={scimg2} />
      <img className='scimg3' src={scimg3} />
      <div className='info'>
        <h2 className='infoh2'>AI 활용 리드타임 예측을 통한</h2>
        <h1 className='infoh1'>선용품 발주 웹서비스</h1>
        <div className='infodiv'>
          <Link to='/login'><button className='loginButton'>Login</button></Link>
        </div>
      </div>
    </div>
  )
}

export default Home;