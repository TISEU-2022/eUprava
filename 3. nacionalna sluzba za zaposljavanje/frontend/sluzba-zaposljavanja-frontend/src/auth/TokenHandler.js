import { useNavigate, useParams, useSearchParams } from 'react-router-dom';
import React, { useEffect } from 'react';

const TokenHandler = () => {
    const [searchParams] = useSearchParams();
    const navigate = useNavigate();
  
    useEffect(() => {
      const token = searchParams.get('token');
      if (token) {
        localStorage.setItem('token', token);
      }
      navigate('/');
    }, []);
  
    return <>a</>;
  };
  
  export default TokenHandler;