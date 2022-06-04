import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { TokenHandlerComponent } from './components/token-handler/token-handler.component';
import { ViewDrivingLicenceComponent } from './components/view-driving-licence/view-driving-licence.component';
import { AuthGuard } from './_helpers/auth.guard';
import {RegistrationCertificateRequestComponent} from "./components/registration-certificate-request/registration-certificate-request.component";
import { CreateDriverLicenseComponent } from './components/create-driver-license/create-driver-license.component';
import { DrivingLicenceCitizenPageComponent } from './components/driving-licence-citizen-page/driving-licence-citizen-page.component';
import { DrivingLicenceEmployeePageComponent } from './components/driving-licence-employee-page/driving-licence-employee-page.component';

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
    path: "citizen/driving-licence", component: DrivingLicenceCitizenPageComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gradjanin']
    }
  },
  {
    path: "employee/driving-licence", component: DrivingLicenceEmployeePageComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['zaposleni']
    }
  },
  {
    path: "driving-licence/:id", component: ViewDrivingLicenceComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['admin','zaposleni','gradjanin']
    }
  },
  {
    path: "driving-licence/create/:requestId", component: CreateDriverLicenseComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['admin', 'zaposleni']
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
