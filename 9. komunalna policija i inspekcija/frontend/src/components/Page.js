import React, {useEffect, useState} from 'react';
import StandardLayout from '../layouts/StandardLayout'
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
        <StandardLayout>
            <div className="d-flex justify-content-center align-items-center bg-info" style={{height: "95vh"}}>
                <header className="App-header">
                    <p>
                        {myVal}
                    </p>
                    <button onClick={getLogin}>Login</button>
                    <button onClick={sendRequest}>Send request again</button>
                </header>
            </div>
        </StandardLayout>
    );
};

export default Page;