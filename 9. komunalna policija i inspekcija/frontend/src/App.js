import {BrowserRouter, Route, Switch} from "react-router-dom";
import Page from "./components/Page";
import Auth from "./components/Auth/Auth";

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
