import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginCredentials } from '../login-credentials';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseURL = "http://localhost:3101/auth/login";
  // port 4200 for testing purposes, change later to port 6003 
  private successUrl = "http://localhost:8080/api/auth/jwt";

  constructor(private httpClient: HttpClient) { }


  login(loginCredentials: LoginCredentials): Observable<any> {
    return this.httpClient.post<any>(`${this.baseURL}?successUrl=` + this.successUrl, loginCredentials);
  }
}
