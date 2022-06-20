import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css';

axios.defaults.baseURL='http://localhost:9002/api';

axios.interceptors.request.use(request => {
    request.headers.Authorization = "Bearer " + localStorage.getItem("token");
    return request;
});

axios.interceptors.response.use(response => {
    return response;
}, error => {

    if (error.response.status === 401) {
        localStorage.clear();
        window.location.replace("/");
        return Promise.reject(error);
    }

    return Promise.reject(error);
});

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
