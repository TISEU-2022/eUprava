import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DrivingLicence } from '../../_models/driving-licence';
import { DrivingLicenceChangeRequest } from '../../_models/driving-licence-change-request';
import { RequestForDrivingLicence } from '../../_models/request-for-driving-licence';
import { DrivingLicenceService } from '../../_services/driving-licence.service';
import { TokenService } from '../../_services/token.service';


@Component({
  selector: 'app-citizen-home-page',
  templateUrl: './citizen-home-page.component.html',
  styleUrls: ['./citizen-home-page.component.css']
})
export class CitizenHomePageComponent implements OnInit {
  drivingLicence!: DrivingLicence;
  createDLrequestExist: boolean = false;
  changeDLrequestExist: boolean = false;
  page: number = 1;
  newDLrequests: RequestForDrivingLicence[] = [];
  changeDLrequests: DrivingLicenceChangeRequest[] = [];

  requestStatuses = ["PENDING", "ACCEPTED", "DECLINED"];
  requestStatus: string = "PENDING";

  viewTypes = ["new","change of"];
  viewType: string = "new";

  constructor(private router: Router,
    private tokenService: TokenService,
    private drivingLicenceService: DrivingLicenceService) { }

  ngOnInit(): void {
    const userId = this.tokenService.getUserId();
    this.drivingLicenceService.getDrivingLicenceForUser(userId).subscribe(
      data => {
        if (data != null) {
          this.drivingLicence = data;
        }
      }
    )

    this.drivingLicenceService.getPendingRequestIfExist(userId).subscribe(
      data => {
        if (data != null) {
          this.createDLrequestExist = true;
        }
      }
    )

    this.drivingLicenceService.getPendingEditRequestIfExist(userId).subscribe(
      data => {
        if (data != null) {
          this.changeDLrequestExist = true;
        }
      }
    )

    this.drivingLicenceService.getRequests(this.page-1, this.requestStatus).subscribe(
      (response:any) => {
        if (response != null) {
          this.newDLrequests = response.content;
        }
      }
    )
  }

  onRequestStatusChange(requestStatus: string) {
    this.requestStatus = requestStatus;
    this.onViewTypeChange(this.viewType);
  }

  onViewTypeChange(viewType: string) {
    this.viewType = viewType;
    if (this.viewType == "new") {
      this.drivingLicenceService.getRequests(this.page-1, this.requestStatus).subscribe(
        (response: any) => {
          if (response != null) {
            this.newDLrequests = response.content;
          }
        }
      )
    }
    else if (this.viewType == "change of") {
      this.drivingLicenceService.getEditRequests(this.page-1, this.requestStatus).subscribe(
        (response: any) => {
          if (response != null) {
            this.changeDLrequests = response.content;
          }
        }
      )
    }
  }

  onViewDLclick() {
    this.router.navigate([`/driving-licence/${this.drivingLicence.id}`])
  }

  onCreateRequestClick() {
    this.router.navigate([`/driving-licence/${this.drivingLicence.id}`])
  }

  onCreateEditRequestClick() {
    this.router.navigate([`/driving-licence/${this.drivingLicence.id}`])
  }

}
