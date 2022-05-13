import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter, Route, Routes, Outlet } from "react-router-dom";
import Home from "./pages/Home";
import TokenHandler from "./components/TokenHandler";
import CitizenManipulation from "./components/citizen-manipulation/CitizenManipulation";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route path="/auth" element={<Outlet />}>
          <Route path="token_handler" element={<TokenHandler />} />
        </Route>
        <Route path="/auth" element={<Outlet />}>
          <Route path="token_handler" element={<TokenHandler />} />
        </Route>
        <Route
          exact
          path="/citizen-manipulation"
          element={<CitizenManipulation />}
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
