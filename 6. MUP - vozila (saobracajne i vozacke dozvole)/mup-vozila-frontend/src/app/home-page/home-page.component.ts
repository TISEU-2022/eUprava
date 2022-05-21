import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../_services/auth-service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private router: Router,
    private authService: AuthService) { }

  ngOnInit(): void {

    const userRole = this.authService.getRole();

    // navigate to specific home-page componenet OR
    // add desired functionalities for each role in if statement
    if (userRole == 'admin') {
      this.router.navigate(['admin/home-page']);
    }
    else if (userRole ==  "zaposleni") {
      this.router.navigate(['employee/home-page']);
    }
    else if (userRole == "gradjanin") {
      this.router.navigate(['citizen/home-page']);
    }

  }

}
