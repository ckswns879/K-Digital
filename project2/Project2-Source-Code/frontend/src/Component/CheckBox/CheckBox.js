//체크박스 사용이 반복되어서 체크박스 생성용 함수의 반복을 피하기 위해 코드를 모았지만 useState의 사용이 어떻게 변경되어야 할지 감이 안와서 일단 미뤄둠

// // 체크박스 단일 선택
// export const handleSingleCheck = (checked, id) => {
//   if (checked) {
//     // 단일 선택 시 체크된 아이템을 배열에 추가
//     setCheckItems(prev => [...prev, id]);
//   } else {
//     // 단일 선택 해제 시 체크된 아이템을 제외한 배열 (필터)
//     setCheckItems(checkItems.filter((el) => el !== id));
//   }
// };

// export const handleAllCheck = (checked) => {
//   if (checked) {
//     // 전체 선택 클릭 시 데이터의 모든 아이템(id)를 담은 배열로 checkItems 상태 업데이트
//     const idArray = [];
//     data.forEach((el) => idArray.push(el.id));
//     setCheckItems(idArray);
//   }
//   else {
//     // 전체 선택 해제 시 checkItems 를 빈 배열로 상태 업데이트
//     setCheckItems([]);
//   }
// }

// //체크박스 선택한 행 삭제
// export const removeRow = () => {
//   setData(data.filter((item) =>
//     !checkItems.includes(item.id)
//   ))
// }