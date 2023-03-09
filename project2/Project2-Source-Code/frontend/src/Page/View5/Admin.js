import { useState } from "react";
import { addItem, classifier, prediction } from "../../API/funcAPI";
import './View5.css'

//찬준 DB설계 맞춤
function Admin() {

  const [subject, setSubject] = useState(''); //필수
  const [machinery, setMachinery] = useState(''); //필수
  const [assembly, setAssembly] = useState(''); //필수
  const [items, setitems] = useState(''); //필수
  const [partNo, setPartNo] = useState(''); //필수
  const [partNo2, setPartNo2] = useState('');
  const [price, setPrice] = useState('');
  const [client, setClient] = useState('');
  const [currency, setCurrency] = useState(''); //필수
  const [controlNo, setControlNo] = useState(''); //필수(리드타임 예측에만 쓰고 저장안하고 버림)

  const [category, setCategory] = useState('');
  const [leadtime, setLeadtime] = useState('');


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

  const handlePartNo2 = (e) => {
    setPartNo2(e.target.value)
  }

  const handlePrice = (e) => {
    setPrice(e.target.value)
  }

  const handleClient = (e) => {
    setClient(e.target.value)
  }

  const handleCurrency = (e) => {
    setCurrency(e.target.value)
  }

  const handleControlNo = (e) => {
    setControlNo(e.target.value)
  }

  //통신에 리드타임 추가 하면 좋을듯
  const onClickPredict = () => {
    if (!subject){
      alert("subject를 입력하세요")
      return
    } else if (!machinery){
      alert("machinery를 입력하세요")
      return
    } else if (!assembly){
      alert("assembly를 입력하세요")
      return
    } else if (!items){
      alert("청구품목을 입력하세요")
      return
    } else if (!partNo){
      alert("partNo를 입력하세요")
      return
    } else if (!client){
      alert("발주처를 입력하세요")
      return
    } else if (!currency){
      alert("견적화폐를 입력하세요")
      return
    }
    const classifyData = {
      "data1": subject,
      "data2": machinery,
      "data3": assembly,
      "data4": items,
      "data5": partNo
    };

    (async () => {
      await classifier(classifyData)
        .then((res) => setCategory(res.prediction))
        .catch((error) => console.log(error))
    })();

    // (axios.post('http://localhost:5000/data/classifier', {
    //   "data1": subject,
    //   "data2": machinery,
    //   "data3": assembly,
    //   "data4": items,
    //   "data5": partNo
    // })
    //   .then(function (response) {
    //     setCategory(response.data.prediction)
    //   })
    //   .catch(function (error) {
    //     console.log(error);
    //   }));

    const predictdata = {
      "data1": subject,
      "data2": partNo,
      "data3": controlNo,//control No
      "data4": assembly
    };

    (async () => {
      await prediction(predictdata)
        .then((res) => setLeadtime(res.prediction))
        .catch((error) => console.log(error))
    })();

    // (axios.post('http://localhost:5000/data/prediction', {
    //   "data1": currency,
    //   "data2": machinery,
    //   "data3": subject,
    //   "data4": "",
    //   "data5": assembly
    // })
    //   .then(function (response) {
    //     setLeadtime(response.data.prediction)
    //   })
    //   .catch(function (error) {
    //     console.log(error);
    //   }));
  }

  const onClickSaveItem = (e) => {
    e.preventDefault()
    console.log("db저장")
    const data = {
      "subject": subject,
      "machinery": machinery,
      "assembly": assembly,
      "items": items,
      "part1": partNo,
      "part2": partNo2,
      "esti_unit_price": price,
      "client": client,
      "currency": currency,
      "category": category,
      "leadtime": leadtime
    };
    (async () => {
      await addItem(data)
        .then((res) => res)
    })();
    alert("저장되었습니다.")
  }


  //입력할지 기존 정보에서 선택할지 모르겠음
  return (
    <div className="admin">
      <div className="categorization">
        <div className="adminForm">
        <div className="input1">
          <label htmlFor='input1'>(*)Subject : </label>
          <input type='text' placeholder="Subject를 입력해주세요." name='subject' value={subject} onChange={handleSubject} />
        </div>
        <div className="input1">
          <label htmlFor='input2'>(*)Machinery : </label>
          <input type='text' placeholder="Machinery를 입력해주세요." name='machinery' value={machinery} onChange={handleMachinery} />
        </div>
        <div className="input1">
          <label htmlFor='input3'>(*)Assembly : </label>
          <input type='text' placeholder="Assembly를 입력해주세요." name='assembly' value={assembly} onChange={handleAssembly} />
        </div>
        <div className="input1">
          <label htmlFor='input4'>(*)청구품목 : </label>
          <input type='text' placeholder="청구품목을 입력해주세요." name='items' value={items} onChange={handleItems} />
        </div>
        <div className="input1">
          <label htmlFor='input5'>(*)Part No.1 : </label>
          <input type='text' placeholder="Part No.1을 입력해주세요." name='partNo' value={partNo} onChange={handlePartNo} />
        </div>
        <div className="input1">
          <label htmlFor='input6'>Part No.2 : </label>
          <input type='text' placeholder="Part No.2을 입력해주세요." name='partNo2' value={partNo2} onChange={handlePartNo2} />
        </div>
        <div className="input1">
          <label htmlFor='input7'>견적단가 : </label>
          <input type='text' placeholder="견적단가를 입력해주세요." name='esti_unit_price' value={price} onChange={handlePrice} />
        </div>
        <div className="input1">
          <label htmlFor='input8'>(*)발주처 : </label>
          <input type='text' placeholder="발주처를 입력해주세요." name='client' value={client} onChange={handleClient} />
        </div>
        <div className="input1">
          <label htmlFor='input9'>(*)견적화폐 : </label>
          <input type='text' placeholder="견적화폐를 입력해주세요." name='currency' value={currency} onChange={handleCurrency} />
        </div>
        <div className="input1">
          <label htmlFor='input10'>(*)ControlNo : </label>
          <input type='text' placeholder="ControlNo를 입력해주세요?" name='controlNo' value={controlNo} onChange={handleControlNo} />
        </div>
        <div className="data">
        {category && <div className="inData">예상 카테고리는 <p className="pred">{category}</p>입니다.</div>}
        {leadtime && <div className="inData">예상 리드타임은 <p className="pred">{leadtime}일</p> 입니다.</div>}
        </div>
        <div className="adminButtons">
          <button className="adminButton" type='button' onClick={onClickPredict}>예측결과 출력</button>
          <button className="adminButton" type='button' onClick={onClickSaveItem}>DB저장</button>
        </div>
        </div>
      </div>

      {/* 통신해서 결과 받으면 출력 */}
      

    </div>
  );
}
export default Admin