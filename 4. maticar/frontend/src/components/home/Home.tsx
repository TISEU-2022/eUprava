import { Outlet } from 'react-router-dom';
import MainLayout from '../common/MainLayout';
import Navbar from '../navbar/Navbar';
import RoleRedirector from './RoleRedirector';

const Home: React.FC = () => {
  return (
    <MainLayout>
      <RoleRedirector />
      <Outlet />
    </MainLayout>
  );
};

export default Home;
