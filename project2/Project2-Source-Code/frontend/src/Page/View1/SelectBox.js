// import React from "react";
// import { useEffect, useState, useContext } from "react";
// import { AppContext } from "./View1Main";
// import { getSelectList } from "../../API/funcAPI";

// function SelectBox() {
//   const [datas, setdatas] = useState();
//   const [data1, setdata1] = useState();
//   const [data2, setdata2] = useState();
//   const [data3, setdata3] = useState();
//   const [machinery, setMachinery] = useState();
//   const [items, setItems] = useState();
//   const [part1, setPart1] = useState();
//   const [lead, setLead] = useContext(AppContext);

//   //콤보박스에 넣을 리스트 만들기 위해 data call
//   useEffect(() => {
//     (async () => {
//       await getSelectList()
//         .then((res) => {
//           //모든 데이터를 data에 넣기
//           setdatas(res);
//           //machinery만 date1에 넣기
//           setdata1(res.map((item) => item.machinery));
//         })
//     })();
//   }, []);

  

//   //selectbox를 id별로 변수화
//   let target = document.getElementById("choice"); //select box 1번
//   let target2 = document.getElementById("choice2");
//   let target3 = document.getElementById("choice3");

//   //data1의 내용을 target(selectbox1)에 넣기
//   useEffect(() => {
//     const result = [...new Set(data1)];
//     result.sort();
//     for (let i = 0; i < result.length; i++) {
//       let opt = document.createElement("option");
//       opt.value = result[i];
//       opt.innerHTML = result[i];
//       target.appendChild(opt);
//     }
//   }, [data1]);

//   //새로운 machinary가 선택될 때 마다 choice2를 초기화
//   const changeValue = (e) => {
//     e.preventDefault();
//     target2.length = 0;
//     let items = [];
//     items.push("===선택===");
//     //선택된 machinery값 저장
//     setMachinery(e.target.value);
//     //변경된 machinery값에 포함되는 items(청구품목)값을 item 배열에 추가
//     datas.map((item) => {
//       if (item.machinery === e.target.value) {
//         items.push(item.items);
//       }
//     });
//     //item배열에 추가된 값을 data2에 입력
//     setdata2(items);
//   };

//   //data2의 내용을 target2(selectbox2)에 넣기
//   useEffect(() => {
//     const result2 = [...new Set(data2)];
//     result2.sort();
//     for (let i = 0; i < result2.length; i++) {
//       let opt = document.createElement("option");
//       opt.value = result2[i];
//       opt.innerHTML = result2[i];
//       target2.appendChild(opt);
//     }
//   }, [data2]);

//   //새로운 items가 선택될때마다 choice3를 초기화
//   const changeValue2 = (e) => {
//     e.preventDefault();
//     target3.length = 0;
//     //선택된 items값 저장
//     setItems(e.target.value);
//     let items2 = [];
//     items2.push("===선택===");
//     //변경된 machinery, items값에 포함되는 items(청구품목)값을 item 배열에 추가
//     datas.map((item) => {
//       if (item.machinery === machinery && item.items === e.target.value) {
//         items2.push(item.part1);
//       }
//     });
//     setdata3(items2);
//   };

//   //data3의 내용을 target3(selectbox3)에 넣기
//   useEffect(() => {
//     const result3 = [...new Set(data3)];
//     // result3.sort();
//     for (let i = 0; i < result3.length; i++) {
//       let opt = document.createElement("option");
//       opt.value = result3[i];
//       opt.innerHTML = result3[i];
//       target3.appendChild(opt);
//     }
//   }, [data3]);

//   //part1은 초기화 할 필요 없음
//   const changeValue3 = (e) => {
//     e.preventDefault();
//     //선택된 part1값 저장
//     setPart1(e.target.value);
//   };

//   const logdata = [machinery, items, part1]
//   // {
//   //   "machinery": machinery,
//   //   "items": items,
//   //   "part1": part1
//   // }

//   const submitdata = (e) => {
//     e.preventDefault();
//     setLead(logdata);
//   };

//   return (
//     <>
//         <label className="label" for="choice"> 대분류 </label>
//         <select id="choice" onChange={changeValue}>
//           <option value={null}>=== 선택 ===</option>
//         </select>
//         <label className="label" for="choice2"> 부품명 </label>
//         <select id="choice2" onChange={changeValue2}>
//           <option value={null}>=== 선택 ===</option>
//         </select>
//         <label className="label" for="choice3"> 번호 </label>
//         <select id="choice3" onChange={changeValue3}>
//           <option value={null}>=== 선택 ===</option>
//         </select>

//         <button variant="dark" className="butt" onClick={submitdata}>검색</button>
//     </>
//   );
// };

// export default SelectBox;