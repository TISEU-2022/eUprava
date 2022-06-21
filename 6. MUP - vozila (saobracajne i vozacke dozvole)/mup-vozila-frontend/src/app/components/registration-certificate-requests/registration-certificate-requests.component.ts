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
    this.getRequests()
  }

  getRequests(){
    this.service.getRequests().subscribe((requests: any) => {
      this.requests = requests
      console.log(this.requests)
    })
  }

  onDeclineReq(request: RegistrationCertificate) {
    this.service.declineRequest(request.id).subscribe((response:any) => {
      console.log(response)
      this.getRequests()
    })
  }

  onAcceptRequest(request: RegistrationCertificate){
    this.router.navigate([`/registration-certificate/create/${request.id}`])
  }
}
