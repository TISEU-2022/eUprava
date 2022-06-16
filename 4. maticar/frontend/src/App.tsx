import { BrowserRouter, Outlet, Route, Routes } from 'react-router-dom';
import AdminPanel from './components/admin/AdminPanel';
import LogoutHandler from './components/auth/LogoutHandler';
import TokenHandler from './components/auth/TokenHandler';
import Home from './components/home/Home';
import Profile from './components/profile/Profile';
import Protection from './components/routing/protected.route';
import RolesProtection from './components/routing/roles-protection.route';
import UserPanel from './components/user/UserPanel';
import WorkerPanel from './components/worker/WorkerPanel';
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
            <Route path="profile" element={<Profile />}></Route>
          </Route>
          <Route
            path="/admin"
            element={<RolesProtection role={'maticar_administrator'} />}
          >
            <Route path="panel" element={<AdminPanel />}></Route>
          </Route>
          <Route
            path="/worker"
            element={<RolesProtection role={'maticar_worker'} />}
          >
            <Route path="panel" element={<WorkerPanel />}></Route>
          </Route>
          <Route
            path="/user"
            element={<RolesProtection role={'maticar_user'} />}
          >
            <Route path="panel" element={<UserPanel />}></Route>
          </Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
