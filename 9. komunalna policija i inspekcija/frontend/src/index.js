import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import axios from 'axios'

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

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
