import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CitizenHomePageComponent } from './citizen-home-page/citizen-home-page.component';
import { EmployeeHomePageComponent } from './employee-home-page/employee-home-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginComponent } from './login/login.component';
import { TokenHandlerComponent } from './token-handler/token-handler.component';
import { ViewDrivingLicenceComponent } from './view-driving-licence/view-driving-licence.component';
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
  },
  {
    path: "citizen/home-page", component: CitizenHomePageComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gradjanin']
    }
  },
  {
    path: "employee/home-page", component: EmployeeHomePageComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['zaposleni']
    }
  },
  {
    path: "admin/home-page", component: CitizenHomePageComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['admin']
    }
  },
  {
    path: "driving-licence/:id", component: ViewDrivingLicenceComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['admin','zaposleni','gradjanin']
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
