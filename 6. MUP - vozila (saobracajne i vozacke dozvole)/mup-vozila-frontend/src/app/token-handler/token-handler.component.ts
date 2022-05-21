import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../_services/auth-service';
import { TokenService } from '../_services/token.service';

@Component({
  selector: 'app-token-handler',
  templateUrl: './token-handler.component.html',
  styleUrls: ['./token-handler.component.css']
})
export class TokenHandlerComponent implements OnInit {
  token!: string;

  constructor(private tokenService: TokenService,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    let url = this.router.url;
    console.log(url);

    if (url.startsWith("/auth/token_handler?token=")) {
      this.token = url.split("=")[1];
    }
    else {
      console.log("Bad URL. Redirecting to login page...");
      this.router.navigate(['/login']);
      return;
    }

    const decoded_token = this.tokenService.decodeToken(this.token);
    if (decoded_token) {
      console.log("JWT: " + this.token);
      console.log("ROLE: " + localStorage.getItem('ROLE'));

      this.authService.login(this.token, decoded_token.roles);

      // navigate to home-page
      this.router.navigate([''])

      console.log("Successfully obtained token and logged user!")
    }
    else {
      console.log("Invalid token. Redirecting to login page...");
      this.router.navigate(['/login']);
    }


  }

}
