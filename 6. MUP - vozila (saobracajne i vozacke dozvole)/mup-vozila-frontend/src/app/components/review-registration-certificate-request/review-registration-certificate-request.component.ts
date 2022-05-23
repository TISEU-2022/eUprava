import { Component, OnInit } from '@angular/core';
import {RegistrationCertificateService} from "../../_services/registration-certificate.service";
import {TokenService} from "../../_services/token.service";

@Component({
  selector: 'app-review-registration-certificate-request',
  templateUrl: './review-registration-certificate-request.component.html',
  styleUrls: ['./review-registration-certificate-request.component.css']
})
export class ReviewRegistrationCertificateRequestComponent implements OnInit {

  request: any = {}

  constructor(private service: RegistrationCertificateService,
              private tokenService: TokenService) { }

  ngOnInit(): void {
    this.request = this.service.getRequestForUser(this.tokenService.getUserId().toString())
  }

}
