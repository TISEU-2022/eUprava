import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import jwt_decode from 'jwt-decode';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private baseURL = "http://localhost:6001/api/auth";

  constructor(private httpClient: HttpClient) { }

  collectToken(): Observable<string> {
    return this.httpClient.get<string>(`${this.baseURL}/collect_jwt`);
  }

  setToken(token: string) {
    localStorage.setItem('TOKEN', token);
  }

  removeToken() {
    localStorage.removeItem('TOKEN');
  }

  getUserId(): number {
    let token: string = localStorage.getItem('TOKEN') as string;
    let decoded_token = this.decodeToken(token);
    let userId = decoded_token.sub;
    return userId;
  }

  decodeToken(token: string): any {
    try {
      return jwt_decode(token);
    }
    catch (error) {
      console.log(error);
      return null;
    }
  }

}
