import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../_services/auth-service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  userRole!: string;

  constructor(private router: Router,
    private authService: AuthService) { }

  ngOnInit(): void {
    // setting userRole so it can be used to filter which
    // elements to show or not on page
    this.userRole = <string> this.authService.getRole();


    // add desired functionalities for each role in if statement
    /*
    if (this.userRole == 'admin') {

    }
    else if (this.userRole ==  "zaposleni") {

    }
    else if (this.userRole == "gradjanin") {

    }
    */

  }

}
