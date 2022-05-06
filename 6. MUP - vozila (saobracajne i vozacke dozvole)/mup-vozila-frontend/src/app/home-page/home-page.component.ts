import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth-service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit(): void {

    const userRole = this.authService.getRole();

    // navigate to specific home-page componenet OR
    // add desired functionalities for each role in if statement
    if (userRole == 'admin') {
      //this.router.navigate(['/adminHomePage']);
    }
    else if (userRole ==  "zaposleni") {
      //this.router.navigate(['/zaposleniHomePage']);
    }
    else if (userRole == "gradjanin") {
      //this.router.navigate(['/gradjaninHomePage']);
    }

  }

}
