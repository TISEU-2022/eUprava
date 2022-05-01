import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../_services/auth-service';
import { TokenService } from '../_services/token.service';

@Component({
  selector: 'app-collect-jwt',
  templateUrl: './collect-jwt.component.html',
  styleUrls: ['./collect-jwt.component.css']
})
export class CollectJwtComponent implements OnInit {
  token!: string;

  constructor(private tokenService: TokenService,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.tokenService.collectToken().subscribe(
      body => {},
      error => {
        if (error.status == 200) {
          this.token = error.error.text;
          const decoded_token = this.tokenService.decodeToken(this.token);
          if (decoded_token) {
            console.log("JWT: " + this.token);
            console.log("ROLE: " + localStorage.getItem('ROLE'));

            this.authService.login(this.token, decoded_token.roles);

            if (localStorage.getItem('ROLE') == "admin") {
              this.router.navigate(['/login']);
            }
            else if (localStorage.getItem('ROLE') == "GRADJANIN") {
              this.router.navigate(['/login']);
            }
            else if (localStorage.getItem('ROLE') == "ZAPOSLENI") {
              this.router.navigate(['/login']);
            }

          }
          else {
            console.log("Not able to decode this token.");
          }
        }
        else {
          console.log("ERROR");
          console.log(error);
          this.router.navigate(['/login']);
        }
      }
    );

    

  }

}
