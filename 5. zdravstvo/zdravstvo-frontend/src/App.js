import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes, Outlet} from "react-router-dom";
import Home from "./pages/Home"
import TokenHandler from './components/TokenHandler';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path='/' element={<Home/>}/>
        <Route path="/auth" element={<Outlet />}>
          <Route path="token_handler" element={<TokenHandler />} />
        </Route>
        <Route path="/auth" element={<Outlet />}>
          <Route path="token_handler" element={<TokenHandler />} />
        </Route>        
        </Routes>
  </BrowserRouter>
  );
}

export default App;
