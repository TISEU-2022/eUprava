import React, { useEffect } from 'react';
import { useNavigate, useParams, useSearchParams } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { tokenAtom } from '../../state/auth/auth.atom';

const TokenHandler = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const setAuthToken = useSetRecoilState(tokenAtom);

  useEffect(() => {
    const token = searchParams.get('token');
    if (token) {
      setAuthToken(token);
    }
    navigate('/');
  }, []);

  return <></>;
};

export default TokenHandler;
