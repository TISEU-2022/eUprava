import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import Page from "./components/Page";
import Auth from "./components/Auth/Auth";
import WelcomePage from "./pages/WelcomePage";
import VrstePredstavkiPage from "./pages/VrstePredstavkiPage";
import VrstePredstavkiDetailsPage from "./pages/VrstePredstavkiDetailsPage";
import VrstePredstavkiFormPage from "./pages/VrstePredstavkiFormPage";

function App() {

  return (
      <BrowserRouter>
          <Switch>
              <Route path="/dobro-dosli" component={WelcomePage}/>
              <Route path="/vrste-predstavki/form/:id" render={() => <VrstePredstavkiFormPage edit/>}/>
              <Route path="/vrste-predstavki/form" component={VrstePredstavkiFormPage}/>
              <Route path="/vrste-predstavki/:id" component={VrstePredstavkiDetailsPage}/>
              <Route path="/vrste-predstavki" component={VrstePredstavkiPage}/>
              <Route path="/page" component={Page}/>
              <Route path="/auth" component={Auth}/>
              <Redirect to='/dobro-dosli'/>
          </Switch>
      </BrowserRouter>
  );
}

export default App;
