import './App.css';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Page from "./component/Page";
import Auth from "./component/Auth";

function App() {

  return (
      <BrowserRouter>
          <Switch>
              <Route path="/page" component={Page} exact/>
              <Route path="/auth" component={Auth} exact/>
          </Switch>
      </BrowserRouter>
  );
}

export default App;
