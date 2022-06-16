import axios from "axios";



const AxiosClient = axios.create({
    baseURL: "http://localhost:5001"
});

AxiosClient.interceptors.request.use(function success(config) {
    const token = localStorage.getItem("token").replaceAll('"', '');
    if (token) {
        config.headers["Authorization"] = "Bearer " + token;
    }
    return config;
});


export default AxiosClient;