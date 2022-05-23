import { Injectable } from '@angular/core';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  setToken(token: string) {
    localStorage.setItem('TOKEN', token);
  }

  removeToken() {
    localStorage.removeItem('TOKEN');
  }

  getUserId(): string {
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
