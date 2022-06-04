import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DrivingLicence } from '../../_models/driving-licence';
import { DrivingLicenceChangeRequest } from '../../_models/driving-licence-change-request';
import { RequestForDrivingLicence } from '../../_models/request-for-driving-licence';
import { DrivingLicenceService } from '../../_services/driving-licence.service';
import { TokenService } from '../../_services/token.service';

@Component({
  selector: 'app-driving-licence-citizen-page',
  templateUrl: './driving-licence-citizen-page.component.html',
  styleUrls: ['./driving-licence-citizen-page.component.css']
})
export class DrivingLicenceCitizenPageComponent implements OnInit {

  drivingLicence!: DrivingLicence;
  drivingLicenceView: string = "Show";
  createDLrequestExist: boolean = true;
  changeDLrequestExist: boolean = true;
  page: number = 1;
  totalElements!: number;
  newDLrequests: RequestForDrivingLicence[] = [];
  changeDLrequests: DrivingLicenceChangeRequest[] = [];
  createRequestResultMsg: string = "";
  changeRequestResultMsg: string = "";

  requestStatuses = ["PENDING", "ACCEPTED", "DECLINED"];
  requestStatus: string = "PENDING";

  drivingLicenceTypes = ["PERMANENT", "TRIAL", "DUPLICATE"]
  drivingLicenceType: string = "PERMANENT";

  requestTypes = ["CHANGE_OF_INFORMATION", "EXPIRED", "LOST"]
  requestType: string = "CHANGE_OF_INFORMATION";

  viewTypes = ["new", "update of"];
  viewType: string = "new";

  constructor(private router: Router,
    private tokenService: TokenService,
    private drivingLicenceService: DrivingLicenceService) { }

  ngOnInit(): void {
    const userId = this.tokenService.getUserId();
    this.drivingLicenceService.getDrivingLicenceForUser(userId).subscribe(
      data => {
        if (data != null) {
          console.log(data);
          this.drivingLicence = data;
          this.drivingLicenceType = data.drivingLicenceType;
        }
      }
    )

    this.drivingLicenceService.getPendingRequestIfExist(userId).subscribe(
      data => {
        if (data == null) {
          this.createDLrequestExist = false;
        }
        else {
          this.createRequestResultMsg = "*You have already sent a request for a new driving licence that is still pending"
        }
      }
    )

    this.drivingLicenceService.getPendingEditRequestIfExist(userId).subscribe(
      data => {
        if (data == null) {
          this.changeDLrequestExist = false;
        }
        else {
          this.changeRequestResultMsg = "*You have already sent a request for a new updated driving licence that is still pending"
        }
      }
    )

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

  onRequestTypeChange(requestType: string) {
    this.requestType = requestType;
    this.drivingLicenceType = this.drivingLicence.drivingLicenceType;
  }

  onRequestStatusChange(requestStatus: string) {
    this.requestStatus = requestStatus;
    this.onViewTypeChange(this.viewType);
  }

  onDLtypeChange(drivingLicenceType: string) {
    this.drivingLicenceType = drivingLicenceType;
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

  onViewDLclick() {
    this.requestType = "CHANGE_OF_INFORMATION";
    this.drivingLicenceType = this.drivingLicence.drivingLicenceType;
    if (this.drivingLicenceView == "Show") {
      this.drivingLicenceView = "Hide";
    }
    else if (this.drivingLicenceView == "Hide") {
      this.drivingLicenceView = "Show";
    }
  }

  onCreateRequestClick() {
    if (this.createDLrequestExist) {
      this.createRequestResultMsg = "*You have already sent a request that is still pending"
      return;
    }

    const userId = this.tokenService.getUserId();
    const request = <RequestForDrivingLicence>({
      citizenId: userId,
      drivingLicenceType: this.drivingLicenceType,
      requestStatus: "PENDING"
    });


    this.drivingLicenceService.createMedicalCertificate().subscribe(
      data => {

        this.drivingLicenceService.createDLrequest(request).subscribe(
          data => {
            this.createDLrequestExist = true;
            this.createRequestResultMsg = "*Request for a new driving licence was sent successfully"
            this.onViewTypeChange(this.viewType);
          },
          error => {
            this.createRequestResultMsg = "*There was an error while sending the request"
          }
        )

      },
      error => {
        console.log(error)
      }
    )

  }

  onCreateEditRequestClick() {
    if (this.changeDLrequestExist) {
      this.changeRequestResultMsg = "*You have already sent a request that is still pending"
      return;
    }

    const request = <DrivingLicenceChangeRequest>({
      requestType: this.requestType,
      drivingLicenceDTO: this.drivingLicence,
      requestStatus: "PENDING"
    });

    this.drivingLicenceService.createDLchangeRequest(request).subscribe(
      data => {
        this.changeDLrequestExist = true;
        this.changeRequestResultMsg = "*Request for a new updated driving licence was sent successfully"
        this.onViewTypeChange(this.viewType);
      },
      error => {
        this.changeRequestResultMsg = "*There was an error while sending the request"
      }
    );
  }
}
