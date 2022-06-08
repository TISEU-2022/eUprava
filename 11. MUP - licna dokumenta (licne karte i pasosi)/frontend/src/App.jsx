import './App.css';
import { BrowserRouter, Routes, Route, Outlet } from "react-router-dom";
import Home from './home/Home';
import Registration from './components/auth/Registration';
import ZakazivanjeLicna from './components/zakazivanja/ZakazivanjeLicna';
import ZakazivanjeLicnaDete from './components/zakazivanja/ZakazivanjeLicnaDete';
import ZakazivanjePasos from './components/zakazivanja/ZakazivanjePasos';
import TokenHandler from './components/auth/TokenHandler';
import LogoutHandler from './components/auth/LogoutHandler';


function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/auth" element={<Outlet />}>
          <Route path="token_handler" element={<TokenHandler />} />
          <Route path="logout_handler" element={<LogoutHandler />} />
        </Route>

        <Route path="/" element={<Home />}>
          <Route path="/zakazivanje-licna" element={<ZakazivanjeLicna />} />
          <Route path="/zakazivanje-licna-dete" element={<ZakazivanjeLicnaDete />} />
          <Route path="/zakazivanje-pasos" element={<ZakazivanjePasos />} />
        </Route>

        <Route path="registration" element={<Registration />} />

      </Routes>
    </BrowserRouter>
  );
}

export default App;
