import { Component, OnInit } from '@angular/core';
import {RegistrationCertificateService} from "../../_services/registration-certificate.service";
import {Router} from "@angular/router";
import {RegistrationCertificate} from "../../_models/registration-certificate.model";

@Component({
  selector: 'app-registration-certificate-requests',
  templateUrl: './registration-certificate-requests.component.html',
  styleUrls: ['./registration-certificate-requests.component.css']
})
export class RegistrationCertificateRequestsComponent implements OnInit {

  requests: RegistrationCertificate[] = []

  constructor(private service: RegistrationCertificateService,
              private router: Router) { }

  ngOnInit(): void {
    this.service.getRequests().subscribe((requests) => {
      this.requests = requests
    })
  }

  onDeclineReq(request: RegistrationCertificate) {
    this.service.declineRequest(request.id)
    this.requests.filter((el) => (el.id !== request.id))
  }

  onAcceptRequest(request: RegistrationCertificate){
    this.service.createCertificate(request.id, request)
    this.requests.filter((el) => (el.id !== request.id))
  }
}
