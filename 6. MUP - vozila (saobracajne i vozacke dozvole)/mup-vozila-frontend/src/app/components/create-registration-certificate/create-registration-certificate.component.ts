import { Component, OnInit } from '@angular/core';
import {RegistrationCertificateService} from "../../_services/registration-certificate.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RegistrationCertificate} from "../../_models/registration-certificate.model";

@Component({
  selector: 'app-create-registration-certificate',
  templateUrl: './create-registration-certificate.component.html',
  styleUrls: ['./create-registration-certificate.component.css']
})
export class CreateRegistrationCertificateComponent implements OnInit {

  id: any
  placeOfIssue: string = ""
  plates: string = ""
  warning: string = ""
  request: RegistrationCertificate = <RegistrationCertificate>{}

  constructor(private service: RegistrationCertificateService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.service.getRequest(this.id).subscribe((response: any) => {
      this.request = response
    })
  }

  onSubmit(){
    this.request.placeOfIssue = this.placeOfIssue
    this.request.licensePlate = this.plates
    console.log(this.request)
    this.service.createCertificate(this.id, this.request).subscribe((response: any)=> {
      console.log(response)
      this.router.navigate(["/"])
    })
  }

}
