import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DrivingLicenceChangeRequest } from '../../_models/driving-licence-change-request';
import { RequestForDrivingLicence } from '../../_models/request-for-driving-licence';
import { DrivingLicenceService } from '../../_services/driving-licence.service';
import { TokenService } from '../../_services/token.service';

@Component({
  selector: 'app-driving-licence-employee-page',
  templateUrl: './driving-licence-employee-page.component.html',
  styleUrls: ['./driving-licence-employee-page.component.css']
})
export class DrivingLicenceEmployeePageComponent implements OnInit {

  drivingLicenceView: string = "Show";
  page: number = 1;
  totalElements!: number;
  newDLrequests: RequestForDrivingLicence[] = [];
  changeDLrequests: DrivingLicenceChangeRequest[] = [];

  requestStatuses = ["PENDING", "ACCEPTED", "DECLINED"];
  requestStatus: string = "PENDING";

  viewTypes = ["new", "update of"];
  viewType: string = "new";

  constructor(private router: Router,
    private tokenService: TokenService,
    private drivingLicenceService: DrivingLicenceService) { }

  ngOnInit(): void {
    this.drivingLicenceService.getRequests(this.page - 1, this.requestStatus).subscribe(
      (response: any) => {
        if (response != null) {
          this.newDLrequests = response.content;
          this.totalElements = response.totalElements;
        }
      }
    )
  }


  onChangePage(newPage: number) {
    // update current page of items
    this.page = newPage;
    this.onViewTypeChange(this.viewType);
  }

  onRequestStatusChange(requestStatus: string) {
    this.requestStatus = requestStatus;
    this.onViewTypeChange(this.viewType);
  }

  onViewTypeChange(viewType: string) {
    this.viewType = viewType;
    if (this.viewType == "new") {
      this.drivingLicenceService.getRequests(this.page - 1, this.requestStatus).subscribe(
        (response: any) => {
          if (response != null) {
            this.newDLrequests = response.content;
            this.totalElements = response.totalElements;
          }
        }
      )
    }
    else if (this.viewType == "update of") {
      this.drivingLicenceService.getEditRequests(this.page - 1, this.requestStatus).subscribe(
        (response: any) => {
          if (response != null) {
            this.changeDLrequests = response.content;
            this.totalElements = response.totalElements;
          }
        }
      )
    }
  }

  // CREATE DL REQUEST CLICK LISTENERS
  onAcceptCreateRequest(request: RequestForDrivingLicence) {
    const userId = this.tokenService.getUserId();
    request.employeeId = userId;
    request.requestStatus = "ACCEPTED";

    this.drivingLicenceService.editDLrequest(request).subscribe(
      data => {
        this.onViewTypeChange(this.viewType);
      }
    )

  }

  onDeclineCreateRequest(request: RequestForDrivingLicence) {
    const userId = this.tokenService.getUserId();
    request.employeeId = userId;
    request.requestStatus = "DECLINED";

    this.drivingLicenceService.editDLrequest(request).subscribe(
      data => {
        this.onViewTypeChange(this.viewType);
      }
    )

  }


  // EDIT DL REQUEST CLICK LISTENERS
  onAcceptEditRequest(request: DrivingLicenceChangeRequest) {
    const userId = this.tokenService.getUserId();
    request.employeeId = userId;
    request.requestStatus = "ACCEPTED";

    this.drivingLicenceService.editDLchangeRequest(request).subscribe(
      data => {
        this.onViewTypeChange(this.viewType);
      }
    )
  }

  onDeclineEditRequest(request: DrivingLicenceChangeRequest) {
    const userId = this.tokenService.getUserId();
    request.employeeId = userId;
    request.requestStatus = "DECLINED";

    this.drivingLicenceService.editDLchangeRequest(request).subscribe(
      data => {
        this.onViewTypeChange(this.viewType);
      }
    )
  }

}
