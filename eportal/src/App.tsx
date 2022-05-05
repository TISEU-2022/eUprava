import { BrowserRouter, Outlet, Route, Routes } from 'react-router-dom';
import LogoutHandler from './components/auth/LogoutHandler';
import TokenHandler from './components/auth/TokenHandler';
import Home from './components/home/Home';
import Portals from './components/portals/Portals';
import Profile from './components/profile/Profile';
import Protection from './components/routing/protected.route';
import Welcome from './components/welcome/Welcome';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/auth" element={<Outlet />}>
          <Route path="token_handler" element={<TokenHandler />} />
          <Route path="logout_handler" element={<LogoutHandler />} />
        </Route>
        <Route element={<Home />}>
          <Route index element={<Welcome />} />
          <Route path="p" element={<Protection />}>
            <Route path="portals" element={<Portals />}></Route>
          </Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
