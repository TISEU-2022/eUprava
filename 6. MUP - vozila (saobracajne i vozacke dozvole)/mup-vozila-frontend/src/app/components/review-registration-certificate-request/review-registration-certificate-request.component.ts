import { Component, OnInit } from '@angular/core';
import {RegistrationCertificateService} from "../../_services/registration-certificate.service";
import {TokenService} from "../../_services/token.service";
import {RegistrationCertificate} from "../../_models/registration-certificate.model";

@Component({
  selector: 'app-review-registration-certificate-request',
  templateUrl: './review-registration-certificate-request.component.html',
  styleUrls: ['./review-registration-certificate-request.component.css']
})
export class ReviewRegistrationCertificateRequestComponent implements OnInit {

  request: RegistrationCertificate =  <RegistrationCertificate>{}

  constructor(private service: RegistrationCertificateService,
              private tokenService: TokenService) { }

  ngOnInit(): void {
    this.service.getRequestForUser(this.tokenService.getUserId().toString()).subscribe(req => {
      this.request = req
    })
  }

}
