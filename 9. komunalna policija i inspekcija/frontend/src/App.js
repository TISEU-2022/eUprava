import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import routeService from "./services/route-service";

function App() {

  const allowedRoutes = routeService.getAllowedRoutes();
  const redirect = routeService.getRedirect();

  return (
      <BrowserRouter>
          <Switch>
              { allowedRoutes.map((route, index) => {
                  if(route.hasOwnProperty("component"))
                      return <Route key={index} path={route.path} component={route.component} exact/>
                  else
                      return <Route key={index} path={route.path} render={() => route.render} exact/>
              })} }
              { redirect }
          </Switch>
      </BrowserRouter>
  );
}

export default App;
