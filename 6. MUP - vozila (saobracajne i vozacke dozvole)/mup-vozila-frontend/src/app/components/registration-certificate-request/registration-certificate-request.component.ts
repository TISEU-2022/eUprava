import { Component, OnInit } from '@angular/core';
import {TokenService} from '../../_services/token.service';
import {RegistrationCertificateService} from "../../_services/registration-certificate.service";
import {RegistrationCertificate} from "../../_models/registration-certificate.model";
import {Car} from "../../_models/car.model";
import {Router} from "@angular/router";

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
              private tokenService: TokenService,
              private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
    if(!this.make || !this.model || !this.chassisNumber || !this.engine || !this.horsePower || !this.weight || !this.fuelType){
      alert("Sva polja moraju biti popunjena")
      return
    }

    const carDTO: Car = <Car>{};
    carDTO.chassisNumber = this.chassisNumber
    carDTO.make= this.make
    carDTO.model= this.model
    carDTO.engine= this.engine
    carDTO.horsePower= this.horsePower
    carDTO.weight= this.weight
    carDTO.fuelType= this.fuelType

    const registrationCertificateDTO: RegistrationCertificate = <RegistrationCertificate>{}
    registrationCertificateDTO.userId = this.tokenService.getUserId()
    registrationCertificateDTO.carDTO = carDTO

    console.log("Ovo su podaci ", registrationCertificateDTO)

    this.service.createRequest(registrationCertificateDTO).subscribe( data => {
      console.log(data)
    },
      error => console.error(error))

    this.fuelType = ""
    this.weight = 0
    this.engine = 0
    this.model = ""
    this.make = ""
    this.chassisNumber = ""
    this.horsePower = 0

    this.router.navigate(["/"])

  }
}
