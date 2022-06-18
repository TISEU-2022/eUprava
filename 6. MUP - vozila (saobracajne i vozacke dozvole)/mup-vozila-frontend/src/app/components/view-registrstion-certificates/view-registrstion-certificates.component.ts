import { Component, OnInit } from '@angular/core';
import {RegistrationCertificate} from "../../_models/registration-certificate.model";
import {RegistrationCertificateService} from "../../_services/registration-certificate.service";
import {Router} from "@angular/router";
import {TokenService} from "../../_services/token.service";

@Component({
  selector: 'app-view-registrstion-certificates',
  templateUrl: './view-registrstion-certificates.component.html',
  styleUrls: ['./view-registrstion-certificates.component.css']
})
export class ViewRegistrstionCertificatesComponent implements OnInit {

  regs: RegistrationCertificate[] = []

  constructor(private service: RegistrationCertificateService,
              private tokenService: TokenService,
              private router: Router) { }

  ngOnInit(): void {
    this.service.getAllForUser(this.tokenService.getUserId()).subscribe((response:RegistrationCertificate[])=>{
      this.regs = response
    })
  }

  viewMoreInfo(reg: RegistrationCertificate){
    this.router.navigate([`registration-certificates/${reg.id}`])
  }

}
