import { Heading } from '@chakra-ui/react';
import React, { useEffect, useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { useRecoilValueLoadable } from 'recoil';
import { isLoggedInSelector } from '../../state/auth/auth.atom';

const Welcome = () => {
  const isLoggedIn = useRecoilValueLoadable(isLoggedInSelector);
  const isLoggedInResolved = useMemo(() => {
    switch (isLoggedIn.state) {
      case 'hasValue':
        return isLoggedIn.contents;
      default:
        return false;
    }
  }, [isLoggedIn]);

  const navigate = useNavigate();
  useEffect(() => {
    if (isLoggedInResolved) {
      navigate('/p/portals');
    }
  }, [isLoggedInResolved])

  return <Heading flex={1} justifyContent="center" justifySelf="center" margin={'auto'}>Welcome to e-Portal!</Heading>;
};

export default Welcome;
