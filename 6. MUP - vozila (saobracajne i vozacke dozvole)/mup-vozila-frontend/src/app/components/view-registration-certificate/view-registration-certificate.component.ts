import { Component, OnInit } from '@angular/core';
import {RegistrationCertificate} from "../../_models/registration-certificate.model";
import {RegistrationCertificateService} from "../../_services/registration-certificate.service";
import {TokenService} from "../../_services/token.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-view-registration-certificate',
  templateUrl: './view-registration-certificate.component.html',
  styleUrls: ['./view-registration-certificate.component.css']
})
export class ViewRegistrationCertificateComponent implements OnInit {

  request: RegistrationCertificate = <RegistrationCertificate>{}

  constructor(private service: RegistrationCertificateService,
              private tokenService: TokenService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.service.getOneForUser(this.route.snapshot.params["id"]).subscribe((req)=> {
      this.request = req
      console.log(this.request)
    })
  }

}
