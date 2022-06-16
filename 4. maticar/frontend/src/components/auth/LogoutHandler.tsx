import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useResetRecoilState } from 'recoil';
import { tokenAtom } from '../../state/auth/auth.atom';
import { useToast } from '@chakra-ui/react';

const LogoutHandler = () => {
  const reset = useResetRecoilState(tokenAtom);
  const toast = useToast();
  const navigate = useNavigate();
  useEffect(() => {
    reset();
    toast({
      title: 'Logout successful.',
      description: "You've been successfully logged out.",
      status: 'success',
      duration: 2000,
      isClosable: true,
    });
    navigate('/');
  }, []);
  return <></>;
};

export default LogoutHandler;
