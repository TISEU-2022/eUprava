import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import { TokenHandlerComponent } from './components/token-handler/token-handler.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ViewDrivingLicenceComponent } from './components/view-driving-licence/view-driving-licence.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { RegistrationCertificateRequestComponent } from './components/registration-certificate-request/registration-certificate-request.component';
import {CreateDriverLicenseComponent} from "./components/create-driver-license/create-driver-license.component";
import { ReviewRegistrationCertificateRequestComponent } from './components/review-registration-certificate-request/review-registration-certificate-request.component';
import { DrivingLicenceCitizenPageComponent } from './components/driving-licence-citizen-page/driving-licence-citizen-page.component';
import { DrivingLicenceEmployeePageComponent } from './components/driving-licence-employee-page/driving-licence-employee-page.component';

import { RegistrationCertificateRequestsComponent } from './components/registration-certificate-requests/registration-certificate-requests.component';
import { ViewRegistrationCertificateComponent } from './components/view-registration-certificate/view-registration-certificate.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TokenHandlerComponent,
    HomePageComponent,
    ViewDrivingLicenceComponent,
    RegistrationCertificateRequestComponent,
    CreateDriverLicenseComponent,
    ReviewRegistrationCertificateRequestComponent,
    DrivingLicenceCitizenPageComponent,
    DrivingLicenceEmployeePageComponent
    ReviewRegistrationCertificateRequestComponent,
    RegistrationCertificateRequestsComponent,
    ViewRegistrationCertificateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
