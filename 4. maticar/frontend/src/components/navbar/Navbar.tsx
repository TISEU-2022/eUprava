import { Button, Flex, Skeleton } from '@chakra-ui/react';
import { useRecoilValueLoadable } from 'recoil';
import { AUTH_SERVER_URL, PUBLIC_URL } from '../../constants';
import { isLoggedInSelector } from '../../state/auth/auth.atom';

const Navbar: React.FC = () => {
  const isLoggedIn = useRecoilValueLoadable(isLoggedInSelector);

  const performLogin = () => {
    window.open(
      `${AUTH_SERVER_URL}/auth/login?successUrl=${PUBLIC_URL}/auth/token_handler`,
      '_self',
    );
  };

  const performLogout = () => {
    window.open(
      `${AUTH_SERVER_URL}/auth/logout?successUrl=${PUBLIC_URL}/auth/logout_handler`,
      '_self',
    );
  };

  const AuthButton = () => {
    switch (isLoggedIn.state) {
      case 'hasError':
      case 'loading':
        return <Skeleton />;
      case 'hasValue':
        const value = isLoggedIn.contents;
        if (value)
          return (
            <Button colorScheme="teal" onClick={performLogout} mx="1">
              Log-out
            </Button>
          );
        else
          return (
            <Button colorScheme="teal" onClick={performLogin} mx="1">
              Log-in
            </Button>
          );
    }
  };

  return (
    <Flex padding={3}>
      <Flex flex={1} justifyContent="flex-end">
        <AuthButton />
        <Button mx="1" colorScheme="orange" onClick={() => {
          window.location.href = "http://localhost:4011"
        }}>ePortal</Button>
      </Flex>
    </Flex>
  );
};

export default Navbar;
