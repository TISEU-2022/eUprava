import axios from "axios";

const httpClient = axios.create({
  baseURL: process.env.REACT_APP_APP_BACKEND_URL
});

httpClient.interceptors.request.use(function success(config) {
  const token = localStorage.getItem("token").replaceAll('"', '');
  if (token) {
      config.headers["Authorization"] = "Bearer " + token;
  }
  return config;
});


export default httpClient;