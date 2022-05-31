import React from 'react';
import {Redirect} from "react-router-dom";

const Auth = (props) => {

    const query = new URLSearchParams(props.location.search);
    const token = query.get('token')
    localStorage.setItem("token", token);

    return (
        <Redirect to={"/page"}/>
    );
};

export default Auth;