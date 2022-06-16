import { Button, Flex, List, ListItem, Popover, PopoverArrow, PopoverBody, PopoverCloseButton, PopoverContent, PopoverHeader, PopoverTrigger, Skeleton } from '@chakra-ui/react';
import { useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { useRecoilValue, useRecoilValueLoadable } from 'recoil';
import { AUTH_SERVER_URL, PUBLIC_URL } from '../../constants';
import { isLoggedInSelector, userSelector } from '../../state/auth/auth.atom';

const Navbar: React.FC = () => {
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
            <Button mx="1" colorScheme="teal" onClick={performLogout}>
              Log-out
            </Button>
          );
        else
          return (
            <Button mx="1" colorScheme="teal" onClick={performLogin}>
              Log-in
            </Button>
          );
    }
  };

  return (
    <Flex padding={3}>
      <Flex flex={1} justifyContent="flex-end">
        {isLoggedInResolved && (<CurrentProfile />)}
        <AuthButton />
      </Flex>
    </Flex>
  );
};

const CurrentProfile = () => {
  const currentUser = useRecoilValue(userSelector);
  return <Popover>
    <PopoverTrigger>
      <Button colorScheme="orange">Profile</Button>
    </PopoverTrigger>
    <PopoverContent>
      <PopoverArrow />
      <PopoverCloseButton />
      <PopoverHeader>Profile</PopoverHeader>
      <PopoverBody>
        <List mx="1" my="2">
          <ListItem>Username: {currentUser?.username}</ListItem>
          <ListItem>First name: {currentUser?.firstName}</ListItem>
          <ListItem>Last name: {currentUser?.lastName}</ListItem>
          <ListItem>Roles: {currentUser?.roles.join(", ")}</ListItem>
        </List>
      </PopoverBody>
    </PopoverContent>
  </Popover>
}

export default Navbar;
