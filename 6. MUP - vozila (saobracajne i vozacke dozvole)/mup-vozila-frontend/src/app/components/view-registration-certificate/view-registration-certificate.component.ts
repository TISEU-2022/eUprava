import { Component, OnInit } from '@angular/core';
import {RegistrationCertificate} from "../../_models/registration-certificate.model";
import {RegistrationCertificateService} from "../../_services/registration-certificate.service";
import {TokenService} from "../../_services/token.service";

@Component({
  selector: 'app-view-registration-certificate',
  templateUrl: './view-registration-certificate.component.html',
  styleUrls: ['./view-registration-certificate.component.css']
})
export class ViewRegistrationCertificateComponent implements OnInit {

  request: RegistrationCertificate = new RegistrationCertificate()

  constructor(private service: RegistrationCertificateService,
              private tokenService: TokenService) { }

  ngOnInit(): void {
    this.service.getRequestForUser(this.tokenService.getUserId().toString()).subscribe((req)=> {
      this.request = req
    })
  }

}
