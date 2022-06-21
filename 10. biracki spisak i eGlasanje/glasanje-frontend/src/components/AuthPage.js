import React from 'react';
import {Navigate} from "react-router-dom";
import jwtDecode from "jwt-decode";
import {TokenService} from "../services/TokenService";

const AuthPage = (props) => {

    var token = window.location.href;
    token = token.replace("http://localhost:10001/auth?token=", "");
    // const query = new URLSearchParams(props.location.search);
    // const token = query.get('token')

    let tokenDecode = jwtDecode(token);
    TokenService.setToken(token);
    localStorage.setItem("id", tokenDecode.identityNumber);

    return (
        <Navigate to={"/aktuelniIzbori"}/>
    );
};

export default AuthPage;