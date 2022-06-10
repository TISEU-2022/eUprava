import { Outlet } from 'react-router-dom';
import MainLayout from '../common/MainLayout';

const Home: React.FC = () => {
  return (
    <MainLayout>
      <Outlet />
    </MainLayout>
  );
};

export default Home;
