import { configureStore, createSlice } from '@reduxjs/toolkit'

//자동완성 리스트
let SelectList = createSlice({
  name: 'SelectList',
  initialState: [],
  reducers: {
    getSelectListRD(state, action) {
      if (state.length == 0) {
        state = action.payload;
        return state
      }
    }
  }
})

//뒤로가기 했을 때 검색 결과 유지
let SearchInfo = createSlice({
  name: 'SearchInfo',
  initialState: [],
  reducers: {
    getSearchInfoRD(state, action) {
      state = action.payload;
      return state
    }
  }
})

//장바구니 목록
let Basket = createSlice({
  name: 'Basket',
  initialState: [],
  reducers: {
    getBasketRD(state, action) {
      if (state.length == 0) {
        state = action.payload;
        return state
      }
    }
  }
})

//장바구니 체크박스 선택 목록
let BasketList = createSlice({
  name: 'BasketList',
  initialState: [],
  reducers: {
    getBasketListRD(state, action) {
      // if (state.length == 0) {
      //     state = action.payload;
      //     return state
      // }
      //위의 코드는 state의 길이가 0일때만 실행, 길이가 0이 아닐때도 실행하려면 아래와 같이 해야함
      state = action.payload;
      return state
    }
  }
})

export default configureStore({
  reducer: {
    SelectList: SelectList.reducer,
    SearchInfo: SearchInfo.reducer,
    Basket: Basket.reducer,
    BasketList: BasketList.reducer

  }
})

export let { getSelectListRD } = SelectList.actions
export let { getSearchInfoRD } = SearchInfo.actions
export let { getBasketRD } = Basket.actions
export let { getBasketListRD } = BasketList.actions
