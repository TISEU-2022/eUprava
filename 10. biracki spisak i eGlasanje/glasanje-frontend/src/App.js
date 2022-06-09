import './App.css';
import {
    BrowserRouter as Router,
    Routes,
    Route
} from "react-router-dom";
import React from "react";
import NavigationBar from "./components/NavigationBar";
import Footer from "./components/Footer";
import RaspisivanjeIzbora from "./components/RaspisivanjeIzbora";
import AktuelniIzbori from "./components/AktuelniIzbori";
import Glasanje from "./components/Glasanje";
import Login from "./components/Login";
import {PrivateRoute} from "./PrivateRoute";

function App() {
  return (
      <Router>
        <NavigationBar/>

        <Routes>
          <Route
              exact
              path="/prijava"
              element={<Login/>}>
          </Route>

            <Route
                exact
                path="/aktuelniIzbori"
                element={<AktuelniIzbori/>}>
            </Route>

            <Route
                exact
                path="/glasanje/:id"
                element={<Glasanje/>}>
            </Route>

            <Route
                exact
                path="/raspisivanje"
                element={<RaspisivanjeIzbora/>}>
            </Route>




            <Route
                exact
                path="/"
                element={<AktuelniIzbori/>}>
            </Route>

        </Routes>

        <Footer/>
      </Router>
  );
}

export default App;

/*<PrivateRoute
              exact
              path="/raspisivanjeIzbora"
              component={RaspisivanjeIzbora}
              roles={["ROLE_SLUZBENIK"]}
          />

          <PrivateRoute
              exact
              path="/aktuelniIzbori"
              component={AktuelniIzbori}
              roles={["ROLE_SLUZBENIK", "ROLE_GRADJANIN"]}
          />*/
