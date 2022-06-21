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
import {ViewRegistrationCertificateComponent} from "./components/view-registration-certificate/view-registration-certificate.component";
import {ViewRegistrstionCertificatesComponent} from "./components/view-registrstion-certificates/view-registrstion-certificates.component";
import {RegistrationCertificateRequestsComponent} from "./components/registration-certificate-requests/registration-certificate-requests.component";
import {ReviewRegistrationCertificateRequestComponent} from "./components/review-registration-certificate-request/review-registration-certificate-request.component";
import {
  CreateRegistrationCertificateComponent
} from "./components/create-registration-certificate/create-registration-certificate.component";

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
    path: "registration-certificate/create-request", component: RegistrationCertificateRequestComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gradjanin']
    }
  },
  {
    path: "registration-certificate/status", component: ReviewRegistrationCertificateRequestComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gradjanin']
    }
  },
  {
    path: "registration-certificates", component: ViewRegistrstionCertificatesComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gradjanin']
    }
  },
  {
    path: "registration-certificates/:id", component: ViewRegistrationCertificateComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['gradjanin']
    }
  },
  {
    path: "registration-certificate/requests", component: RegistrationCertificateRequestsComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['zaposleni']
    }
  },
  {
    path: "registration-certificate/create/:id", component: CreateRegistrationCertificateComponent,
    canActivate: [AuthGuard],
    data: {
      role: ['zaposleni']
    }
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
