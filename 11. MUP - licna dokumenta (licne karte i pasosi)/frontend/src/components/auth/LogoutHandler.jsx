import { useNavigate } from 'react-router-dom';
import React, { useEffect } from 'react';

const LogoutHandler = () => {
    const navigate = useNavigate();

    useEffect(() => {
        localStorage.removeItem('token');
        navigate('/');
    }, []);

    return <>Success logout</>;
};

export default LogoutHandler;