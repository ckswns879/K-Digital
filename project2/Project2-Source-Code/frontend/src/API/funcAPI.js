import { NotAuthInstance, authInstance } from './indexAPI'
//생성된 axios인스턴스를 사용해 API호출

//회원가입
export const signup = async (requestBody) => {
  try {
    const { data } = await NotAuthInstance.post(
        "/auth/signup",
        requestBody
      )
    return data
  } catch (error) {
    console.error(error)
  }
}

//login - return yes(리덕스 저장 필요없음)
export const login = async (requestBody) => {
  try {
    const { data } = await NotAuthInstance.post(
        "/auth/login",
        requestBody
      )
    return data
  } catch (error) {
    console.error(error)
  }
}

// 목록 한번에 출력 - return yes(redux로 대체)
export const getSelectList = async () => {
  try{
    const {data}  = await authInstance.get(
      'get',
    )
    return data
  } catch (error) {
    console.log(error)
  }
}

//login - return yes(입력마다 출력이 달라지기 때문에 리덕스 저장 불가)
export const getSearchResults = async (requestBody) => {
  try {
    const { data } = await authInstance.post(
        "search",
        requestBody
      )
    return data
  } catch (error) {
    console.error(error)
  }
}

//login - return no
export const addBasket = async (requestBody) => {
  try {
    const { data } = await authInstance.post(
        "basket",
        requestBody
      )
    return data
  } catch (error) {
    console.error(error)
  }
}

//login - return yes(일단 장바구니 전체 호출)
export const getBasket = async () => {
  try{
    const {data}  = await authInstance.get(
      'getbasket',
    )
    return data
  } catch (error) {
    console.log(error)
  }
}

//Basket 삭제 - return no
export const delBasket = async (requestBody) => {
  try{
    const {data}  = await authInstance.post(
      'delbasket',
      requestBody
    )
    return data
  } catch (error) {
    console.log(error)
  }
}

// post method example
// export const addLog = async (logInfo) => {
//   try {
//     const { data } = await defaultInstance.post(
//         "searchlog",
//         logInfo
//       )
//     return data
//   } catch (error) {
//     console.error(error)
//   }
// }