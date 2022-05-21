import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import { TokenHandlerComponent } from './components/token-handler/token-handler.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { RegistrationCertificateRequestComponent } from './components/registration-certificate-request/registration-certificate-request.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TokenHandlerComponent,
    HomePageComponent,
    RegistrationCertificateRequestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
