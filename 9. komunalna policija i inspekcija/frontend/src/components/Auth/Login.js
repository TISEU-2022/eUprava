import React, {useEffect} from 'react';
import authService from "../../services/auth-service";

const Login = (props) => {

    useEffect(() => {
        const query = new URLSearchParams(props.location.search);
        const token = query.get('token');
        authService.login(token);
    })

    return <></>;
};

export default Login;