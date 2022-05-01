import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginCredentials } from '../login-credentials';
import { LoginService } from '../_services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginCredentials: LoginCredentials = new LoginCredentials();
  resultMsg: string = "";


  constructor(private loginService: LoginService,
    private router: Router) { }

  ngOnInit(): void {}

  onSubmit = async () => {
    this.loginService.login(this.loginCredentials).subscribe(
      body => {},
      error => {
        if (error.url.includes('token')) {
          // login successful - collect jwt
          this.router.navigate(['/collect_jwt']);
        }
        else {
          // failed to login
          this.resultMsg = 'Invalid username or password';
        }
      }
    );
  }
}
