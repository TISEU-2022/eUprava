import React, {useEffect, useState} from 'react';
import logo from "../logo.svg";
import axios from "axios";

const Page = () => {

    const [myVal, setMyVal] = useState("");


    useEffect(() => {
        sendRequest();
    }, [])

    const getLogin = () => {
        window.location.href = 'http://localhost:4101/auth/login?successUrl=http://localhost:9001/auth';
    }

    const sendRequest = () => {
        axios.get("/test")
            .then(response => setMyVal(response.data))
            .catch(error => setMyVal("Backend error"));
    }

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <p>
                    {myVal}
                </p>
                <button onClick={getLogin}>Login</button>
                <button onClick={sendRequest}>Send request again</button>
            </header>
        </div>
    );
};

export default Page;