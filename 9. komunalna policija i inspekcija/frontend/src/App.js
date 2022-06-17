import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import Page from "./components/Page";
import Auth from "./components/Auth/Auth";
import WelcomePage from "./pages/WelcomePage";
import VrstePredstavkiPage from "./pages/VrstePredstavkiPage";
import VrstePredstavkiDetailsPage from "./pages/VrstePredstavkiDetailsPage";
import VrstePredstavkiFormPage from "./pages/VrstePredstavkiFormPage";
import VrsteKomunalnihProblemaPage from "./pages/VrsteKomunalnihProblemaPage";
import VrsteKomunalnihProblemaDetailsPage from "./pages/VrsteKomunalnihProblemaDetailsPage";
import VrsteKomunalnihProblemaFormPage from "./pages/VrsteKomunalnihProblemaFormPage";
import PredstavkeFormPage from "./pages/PredstavkeFormPage";
import PredstavkePage from "./pages/PredstavkePage";
import PredstavkeDetailsPage from "./pages/PredstavkeDetailsPage";
import KomunalniProblemiPage from "./pages/KomunalniProblemiPage";
import KomunalniProblemiDetailsPage from "./pages/KomunalniProblemiDetailsPage";
import KomunalniProblemiFormPage from "./pages/KomunalniProblemiFormPage";

function App() {

  return (
      <BrowserRouter>
          <Switch>
              <Route path="/dobro-dosli" component={WelcomePage}/>
              <Route path="/vrste-predstavki/form/:id" render={() => <VrstePredstavkiFormPage edit/>}/>
              <Route path="/vrste-predstavki/form" component={VrstePredstavkiFormPage}/>
              <Route path="/vrste-predstavki/:id" component={VrstePredstavkiDetailsPage}/>
              <Route path="/vrste-predstavki" component={VrstePredstavkiPage}/>
              <Route path="/vrste-komunalnih-problema/form/:id" render={() => <VrsteKomunalnihProblemaFormPage edit/>} exact />
              <Route path="/vrste-komunalnih-problema/form" component={VrsteKomunalnihProblemaFormPage} exact/>
              <Route path="/vrste-komunalnih-problema/:id" component={VrsteKomunalnihProblemaDetailsPage} exact/>
              <Route path="/vrste-komunalnih-problema" component={VrsteKomunalnihProblemaPage} exact/>
              <Route path="/predstavke/form" component={PredstavkeFormPage}/>
              <Route path="/predstavke/:id" component={PredstavkeDetailsPage}/>
              <Route path="/predstavke" component={PredstavkePage}/>
              <Route path="/komunalni-problemi/form" component={KomunalniProblemiFormPage} />
              <Route path="/komunalni-problemi/:id" component={KomunalniProblemiDetailsPage} />
              <Route path="/komunalni-problemi" component={KomunalniProblemiPage} />
              <Route path="/page" component={Page}/>
              <Route path="/auth" component={Auth}/>
              <Redirect to='/dobro-dosli'/>
          </Switch>
      </BrowserRouter>
  );
}

export default App;
