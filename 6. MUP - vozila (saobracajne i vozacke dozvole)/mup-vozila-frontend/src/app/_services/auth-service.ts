import { Injectable } from "@angular/core";
import { of } from "rxjs";
import { TokenService } from "./token.service";

    @Injectable({
      providedIn: 'root'
    })
    export class AuthService {
      isLogin = false;

      roleAs: any;

      constructor(private tokenService: TokenService) { }
    
      login(token: string, role: string) {
        this.isLogin = true;
        this.roleAs = role;
        this.tokenService.setToken(token);
        localStorage.setItem('STATE', 'true');
        localStorage.setItem('ROLE', this.roleAs);
        return of({ success: this.isLogin, role: this.roleAs });
      }
    
      logout() {
        this.isLogin = false;
        this.roleAs = '';
        this.tokenService.removeToken();
        localStorage.setItem('STATE', 'false');
        localStorage.setItem('ROLE', '');
        return of({ success: this.isLogin, role: '' });
      }
    
      isLoggedIn() {
        const loggedIn = localStorage.getItem('STATE');
        if (loggedIn == 'true')
          this.isLogin = true;
        else
          this.isLogin = false;
        return this.isLogin;
      }
    
      getRole() {
        this.roleAs = localStorage.getItem('ROLE');
        return this.roleAs;
      }
    
    }
