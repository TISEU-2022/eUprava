import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { TokenHandlerComponent } from './components/token-handler/token-handler.component';
import { AuthGuard } from './_helpers/auth.guard';
import {RegistrationCertificateRequestComponent} from "./components/registration-certificate-request/registration-certificate-request.component";

const routes: Routes = [
  {
    path: "", component: HomePageComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['admin', 'zaposleni', 'gradjanin']
    }
  },
  {
    path: "login", component: LoginComponent
  },
  {
    path: "auth/token_handler", component: TokenHandlerComponent,
  },
  {
    path: "registration-certificate/create-request", component: RegistrationCertificateRequestComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
