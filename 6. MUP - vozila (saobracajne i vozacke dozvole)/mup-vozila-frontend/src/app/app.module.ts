import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import { TokenHandlerComponent } from './token-handler/token-handler.component';
import { HomePageComponent } from './home-page/home-page.component';
import { EmployeeHomePageComponent } from './employee-home-page/employee-home-page.component';
import { CitizenHomePageComponent } from './citizen-home-page/citizen-home-page.component';
import { ViewDrivingLicenceComponent } from './view-driving-licence/view-driving-licence.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { RegistrationCertificateRequestComponent } from './components/registration-certificate-request/registration-certificate-request.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TokenHandlerComponent,
    HomePageComponent,
    EmployeeHomePageComponent,
    CitizenHomePageComponent,
    ViewDrivingLicenceComponent,
    RegistrationCertificateRequestComponent
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
