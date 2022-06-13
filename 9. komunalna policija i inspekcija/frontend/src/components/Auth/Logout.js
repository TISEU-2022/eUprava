import React, {useEffect} from 'react';
import authService from "../../services/auth-service";

const Logout = () => {

    useEffect(() => {
        authService.logout();
    })

    return <></>;
};

export default Logout;