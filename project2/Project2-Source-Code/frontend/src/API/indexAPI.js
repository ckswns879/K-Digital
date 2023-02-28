import axios from 'axios'

//axios 인스턴스 생성
const BASE_URL = "http://localhost:8080/data/"

const axiosAPI = (url, options) => {
  const instance = axios.create({ baseURL: url, ...options })
  return instance
}

const axiosAuthAPI = (url, options) => {
  const token = localStorage.getItem('accessToken') || ''
  const instance = axios.create({
    baseURL: url,
    headers: { Authorization: `Bearer ${token}` }, //bearer를 사용하는건 암묵적 약속
    ...options,
  });
  // return instance
  instance.interceptors.response.use(    
    (response) => {      
      return response;
    },
    async (error) => {
      const {
        config,
        response: { status },
      } = error;
      
      const originalRequest = config;
  
      if (status === 401) {
        const accessToken = localStorage.getItem('accessToken');
        const refreshToken = localStorage.getItem('refreshToken');
        
        try {
          const { data } = await axios({
            method: 'post',
            url: "http://localhost:8080/data/auth/reissue",
            data: { "accessToken": accessToken, "refreshToken": refreshToken },
          });
          console.log(data)
          const newAccessToken = data.accessToken;
          const newRefreshToken = data.refreshToken;
          
          originalRequest.headers = {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + newAccessToken,
          };
          localStorage.setItem('accessToken', newAccessToken);
          localStorage.setItem('refreshToken', newRefreshToken);
          return await axios(originalRequest);
        } catch (err) {
          new Error(err);
        }
      }
      return Promise.reject(error);
    }
  );
  return instance;
}


export const NotAuthInstance = axiosAPI(BASE_URL)
export const authInstance = axiosAuthAPI(BASE_URL)


// fetch('http://localhost:8000/login/', {
//   method: 'POST',
//   headers: {
//       'Content-Type': 'application/json',
//   },
//   body: JSON.stringify({
//     'id': 'kim',
//     'password': '1234'
//   })
// })
// .then(response => response.json())
// .then(response => {
//   if (response.token) {
//     localStorage.setItem('wtw-token', response.token);
//   }
// })

// let token = localStorage.getItem('wtw-token') || '';

// fetch('http://localhost:8000/likes/', {
//   headers: {
//       'Authorization': token,
//   }
// })
// .then(response => response.json())
// .then(response => {
//    console.log(response.data);
// })