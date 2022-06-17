import "./App.css";
import GradjaniComponent from "./components/GradjaniComponent";
import OglasiComponent from "./components/OglasiComponent";
import Navbar from "./navbar/Navbar";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Outlet,
} from "react-router-dom";
import ViewOglasComponent from "./components/ViewOglasComponent";
import TokenHandler from "./auth/TokenHandler";
import UpdateOglasComponent from "./components/UpdateOglasComponent";
import AddOglasComponent from "./components/AddOglasComponent";
import SignUpComponent from "./components/SignUpComponent";

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
          <Route path="/oglasi" element={<OglasiComponent />}></Route>
          <Route exact path="/oglasi/add" element={<AddOglasComponent />}></Route>
          <Route exact path="/oglasi/:id" element={<UpdateOglasComponent />}></Route>
          <Route exact path="/viewOglas/:id" element={<ViewOglasComponent />}></Route>
          <Route exact path="/signUp/:id" element={<SignUpComponent />}></Route>
        </Routes>
      </Router>
    </>
  );
}

export default App;
