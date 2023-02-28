import { useState } from "react";
// import { useSelector } from 'react-redux';
import axios from "axios";

//기존에 있던 데이터 수정기능 ???
function Admin() {

  // let { SelectList } = useSelector((state) => { return state })
  const [subject, setSubject] = useState('');
  const [machinery, setMachinery] = useState('');
  const [assembly, setAssembly] = useState('');
  const [items, setitems] = useState('');
  const [partNo, setPartNo] = useState('');
  const [category, setCategory] = useState('');
  const [leadtime, setLeadtime] = useState('');
  const [order, setOrder] = useState('');
  const [price, setPrice] = useState('');  
  // console.log(SelectList.map((i)=>i.machinery))

  //만약 입력이 아니라 선택일때 사용할 코드
  //===============================================================
  // let target1 = document.getElementById("choice1");
  // let target2 = document.getElementById("choice2");
  // let target3 = document.getElementById("choice3");
  // let target4 = document.getElementById("choice4");
  // let target5 = document.getElementById("choice5");
  // useEffect(() => {
  //   const result1 = [...new Set(SelectList.map((item)=>item.machinery))];
  //   result1.sort();
  //   for (let i = 0; i < result1.length; i++) {
  //     let opt = document.createElement("option");
  //     opt.value = result1[i];
  //     opt.innerHTML = result1[i];
  //     target1.appendChild(opt);
  //   }

  //   const result2 = [...new Set(SelectList.map((item)=>item.items))];
  //   result2.sort();
  //   for (let i = 0; i < result2.length; i++) {
  //     let opt = document.createElement("option");
  //     opt.value = result2[i];
  //     opt.innerHTML = result2[i];
  //     target2.appendChild(opt);
  //   }

  //   const result3 = [...new Set(SelectList.map((item)=>item.part1))];
  //   result3.sort();
  //   for (let i = 0; i < result3.length; i++) {
  //     let opt = document.createElement("option");
  //     opt.value = result3[i];
  //     opt.innerHTML = result3[i];
  //     target3.appendChild(opt);
  //   }

  //   const result4 = [...new Set(SelectList.map((item)=>item.key2))];
  //   result4.sort();
  //   for (let i = 0; i < result4.length; i++) {
  //     let opt = document.createElement("option");
  //     opt.value = result4[i];
  //     opt.innerHTML = result4[i];
  //     target4.appendChild(opt);
  //   }

  //   const result5 = [...new Set(SelectList.map((item)=>item.baljucheo))];
  //   result5.sort();
  //   for (let i = 0; i < result5.length; i++) {
  //     let opt = document.createElement("option");
  //     opt.value = result5[i];
  //     opt.innerHTML = result5[i];
  //     target5.appendChild(opt);
  //   }
  // }, [SelectList]);
  //===============================================================

  const handleSubject = (e) => {
    setSubject(e.target.value)
  }

  const handleMachinery = (e) => {
    setMachinery(e.target.value)
  }

  const handleAssembly = (e) => {
    setAssembly(e.target.value)
  }

  const handleItems = (e) => {
    setitems(e.target.value)
  }

  const handlePartNo = (e) => {
    setPartNo(e.target.value)
  }

  //통신에 리드타임 추가 하면 좋을듯
  const onClickLogin = () => {
    axios.post('http://localhost:5000/data/getitem', {
      "data1": subject,
      "data2": machinery,
      "data3": assembly,
      "data4": items,
      "data5": partNo
    })
      .then(function (response) {
        setCategory(response.data.prediction)
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  const handleOrder = (e) => {
    setOrder(e.target.value)
  }

  const handlePrice = (e) => {
    setPrice(e.target.value)
  }

  //입력할지 기존 정보에서 선택할지 모르겠음
  return (
    <>
      <div className="categorization">
        <div className="input1">
          <label htmlFor='input1'>Subject : </label>
          <input type='text' name='subject' value={subject} onChange={handleSubject} />
          {/* <select id="choice1" onChange={handleSubject}>
            <option value={null}>=== 선택 ===</option>
          </select> */}
        </div>
        <div className="input2">
          <label htmlFor='input2'>Machinery : </label>
          <input type='text' name='machinery' value={machinery} onChange={handleMachinery} />
          {/* <select id="choice2" onChange={handleMachinery}>
            <option value={null}>=== 선택 ===</option>
          </select> */}
        </div>
        <div className="input3">
          <label htmlFor='input3'>Assembly : </label>
          <input type='text' name='assembly' value={assembly} onChange={handleAssembly} />
          {/* <select id="choice3" onChange={handleAssembly}>
            <option value={null}>=== 선택 ===</option>
          </select> */}
        </div>
        <div className="input4">
          <label htmlFor='input4'>청구품목 : </label>
          <input type='text' name='items' value={items} onChange={handleItems} />
          {/* <select id="choice4" onChange={handleItems}>
            <option value={null}>=== 선택 ===</option>
          </select> */}
        </div>
        <div className="input5">
          <label htmlFor='input5'>PartNo : </label>
          <input type='text' name='partNo' value={partNo} onChange={handlePartNo} />
          {/* <select id="choice5" onChange={handlePartNo}>
            <option value={null}>=== 선택 ===</option>
          </select> */}
        </div>
        <div>
          <button type='button' onClick={onClickLogin}>카테고리 예측</button>
        </div>
      </div>

      {/* 통신해서 결과 받으면 출력 */}
      {category && <div>예상 카테고리는 {category}입니다.</div>}
      <div className="moreInfo">
        예상 카테고리, 예상 리드타임, 발주처, 견적단가(추가 예정)
        <div className="input6">
          <label htmlFor='input6'>발주처 : </label>
          <input type='text' name='order' value={order} onChange={handleOrder} />
        </div>
        <div className="input7">
          <label htmlFor='input7'>견적단가 : </label>
          <input type='text' name='price' value={price} onChange={handlePrice} />
        </div>
      </div>
      {/* db에 테이블 생성 및 스프링 연동해서 db저장 */}
    </>
  );
}
export default Admin