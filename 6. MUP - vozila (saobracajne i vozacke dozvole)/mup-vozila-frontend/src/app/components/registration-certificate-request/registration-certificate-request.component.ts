import { Component, OnInit } from '@angular/core';
import {TokenService} from '../../_services/token.service';
import {RegistrationCertificateService} from "../../services/registration-certificate.service";

@Component({
  selector: 'app-registration-certificate-request',
  templateUrl: './registration-certificate-request.component.html',
  styleUrls: ['./registration-certificate-request.component.css']
})
export class RegistrationCertificateRequestComponent implements OnInit {

  make: string = "";
  model: string = "";
  chassisNumber: string = "";
  engine: number = 0;
  horsePower: number = 0;
  weight: number = 0;
  fuelType: string = "";
  warning: string = "";

  constructor(private service: RegistrationCertificateService,
              private tokenService: TokenService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    if(!this.make || !this.model || !this.chassisNumber || !this.engine || !this.horsePower || !this.weight || !this.fuelType){
      alert("Sva polja moraju biti popunjena")
      return
    }

    const carDTO = {
      chassisNumber: this.chassisNumber,
      make: this.make,
      model: this.model,
      engine: this.engine,
      horsePower: this.horsePower,
      weight: this.weight,
      fuelType: this.fuelType
    }

    const registrationCertificateDTO = {
      userId: this.tokenService.getUserId(),
      carDTO: carDTO
    }

    this.service.createRequest(registrationCertificateDTO)

    this.fuelType = ""
    this.weight = 0
    this.engine = 0
    this.model = ""
    this.make = ""
    this.chassisNumber = ""
    this.horsePower = 0

  }

}
