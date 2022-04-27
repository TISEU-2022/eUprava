import { Navigate, Outlet } from 'react-router-dom';
import { useRecoilValueLoadable } from 'recoil';
import { isLoggedInSelector } from '../../state/auth/auth.atom';

const Protection: React.FC = () => {
  const isLoggedIn = useRecoilValueLoadable(isLoggedInSelector);
  switch (isLoggedIn.state) {
    case 'hasError':
      return <Navigate to={'/'}></Navigate>;
    case 'loading':
      return <></>;
    case 'hasValue':
      const value = isLoggedIn.contents;
      if (!value) {
        return <Navigate to={'/'}></Navigate>;
      } else return <Outlet />;
    default:
      return <></>;
  }
};

export default Protection;
