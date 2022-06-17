import './App.css';
import {
    BrowserRouter as Router,
    Routes,
    Route
} from "react-router-dom";
import React from "react";
import NavigationBar from "./components/NavigationBar";
import Footer from "./components/Footer";
import Login from "./components/Login";
import ProtectedAktuelniIzbori from "./components/protected/ProtectedAktuelniIzbori";
import AuthPage from "./components/AuthPage";
import ProtectedGlasanje from "./components/protected/ProtectedGlasanje";
import ProtectedRaspisivanjeIzbora from "./components/protected/ProtectedRaspisivanjeIzbora";
import ProtectedZavrseniIzbori from "./components/protected/ProtectedZavrseniIzbori";
import ProtectedRezultatiIzbora from "./components/protected/ProtectedRezultatiIzbora";

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
                element={<ProtectedAktuelniIzbori/>}>
            </Route>

            <Route
                exact
                path="/zavrseniIzbori"
                element={<ProtectedZavrseniIzbori/>}>
            </Route>

            <Route
                exact
                path="/rezultati/:id"
                element={<ProtectedRezultatiIzbora/>}>
            </Route>

            <Route
                exact
                path="/glasanje/:id"
                element={<ProtectedGlasanje/>}>
            </Route>

            <Route
                exact
                path="/raspisivanje"
                element={<ProtectedRaspisivanjeIzbora/>}>
            </Route>
            <Route
                path="/auth"
                element={<AuthPage/>}>
            </Route>



            <Route
                exact
                path="/"
                element={<Login/>}>
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
