import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DrivingLicence } from '../../_models/driving-licence';
import { RequestForDrivingLicence } from '../../_models/request-for-driving-licence';
import { DrivingLicenceService } from '../../_services/driving-licence.service';

@Component({
  selector: 'app-create-driver-license',
  templateUrl: './create-driver-license.component.html',
  styleUrls: ['./create-driver-license.component.css']
})
export class CreateDriverLicenseComponent implements OnInit {
  drivingLicence: DrivingLicence = new DrivingLicence();
  requestForDL: RequestForDrivingLicence = new RequestForDrivingLicence();
  resultMsg: string = "";

  constructor(private route: ActivatedRoute,
    private router: Router,
    private drivingLicenceService: DrivingLicenceService) { }

  ngOnInit(): void {
    const requestId = this.route.snapshot.params['requestId'];
    this.drivingLicenceService.getCreateRequestById(requestId).subscribe(
      data => {
        this.requestForDL = data;
        this.drivingLicence.drivingLicenceType = data.drivingLicenceType;
        this.drivingLicence.userId = data.citizenId;
      }
    )
  }

  onSubmit() {
    if (this.drivingLicence.licenceNumber == null || this.drivingLicence.placeOfIssue == null || this.drivingLicence.validUntil == null) {
      this.resultMsg = "You have to fill all the fields";
      return;
    }

    this.requestForDL.drivingLicenceDTO = this.drivingLicence;

    this.drivingLicenceService.createDL(this.requestForDL).subscribe(
      data => {
        this.router.navigate(['/employee/home-page']);
      }
    )
  }

}
