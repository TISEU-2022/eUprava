import "./App.css";
import GradjaniComponent from "./components/GradjaniComponent";
import OglasiComponent from "./components/OglasiComponent";
import Navbar from "./navbar/Navbar";
import { BrowserRouter as Router, Routes, Route, Link, Outlet } from "react-router-dom";
import ViewOglasComponent from "./components/ViewOglasComponent";
import TokenHandler from "./auth/TokenHandler";

function App() {
  return (
    <>
      <Router>
        <Navbar />

        <Routes>
          <Route path="/auth" element={<Outlet />}>
            <Route path="token_handler" element={<TokenHandler />} />
            {/* <Route path="logout_handler" element={<LogoutHandler />} /> */}
          </Route>
          <Route exact path="/gradjani" element={<GradjaniComponent />}></Route>
          <Route exact path="/oglasi" element={<OglasiComponent />}></Route>
          <Route
            exact
            path="/viewOglas/:id"
            element={<ViewOglasComponent />}
          ></Route>
        </Routes>
      </Router>
    </>
  );
}

// let loginBtn = document.getElementById('loginNavbar')
// console.log(loginBtn)
// loginBtn.addEventListener('click', () => {
//   window.location.href = "http://localhost:4011"
// })

export default App;
