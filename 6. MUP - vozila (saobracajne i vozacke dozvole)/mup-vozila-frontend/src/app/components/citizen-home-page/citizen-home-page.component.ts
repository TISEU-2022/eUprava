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
  drivingLicenceView: string = "Show";
  createDLrequestExist: boolean = true;
  changeDLrequestExist: boolean = true;
  page: number = 1;
  totalElements!: number;
  newDLrequests: RequestForDrivingLicence[] = [];
  changeDLrequests: DrivingLicenceChangeRequest[] = [];
  resultMsg: string = "";

  requestStatuses = ["PENDING", "ACCEPTED", "DECLINED"];
  requestStatus: string = "PENDING";

  drivingLicenceTypes = ["PERMANENT", "TRIAL", "DUPLICATE"]
  drivingLicenceType: string = "PERMANENT";

  requestTypes = ["CHANGE_OF_INFORMATION", "EXPIRED", "LOST"]
  requestType: string = "CHANGE_OF_INFORMATION";

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
      }
    )

    this.drivingLicenceService.getPendingEditRequestIfExist(userId).subscribe(
      data => {
        if (data == null) {
          this.changeDLrequestExist = false;
        }
      }
    )

    this.drivingLicenceService.getRequests(this.page-1, this.requestStatus).subscribe(
      (response:any) => {
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
      this.drivingLicenceService.getRequests(this.page-1, this.requestStatus).subscribe(
        (response: any) => {
          if (response != null) {
            this.newDLrequests = response.content;
            this.totalElements = response.totalElements;
          }
        }
      )
    }
    else if (this.viewType == "change of") {
      this.drivingLicenceService.getEditRequests(this.page-1, this.requestStatus).subscribe(
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
    const userId = this.tokenService.getUserId();
    const request = <RequestForDrivingLicence>({
      citizenId: userId,
      drivingLicenceType: this.drivingLicenceType,
      requestStatus: "PENDING"
    });

    this.drivingLicenceService.createDLrequest(request).subscribe(
      data => {
        this.createDLrequestExist = true;
        this.resultMsg = "*Request for driving licence was sent successfully"
        this.onViewTypeChange(this.viewType);
      },
      error => {
        this.resultMsg = "*There was an error while sending the request"
      } 
    )

    
  }

  onCreateEditRequestClick() {
    // only use selected drivingLicenceType if requestType is
    // for change of info
    if (this.requestType == "CHANGE_OF_INFORMATION") {
      this.drivingLicence.drivingLicenceType = this.drivingLicenceType;
    }

    const request = <DrivingLicenceChangeRequest>({
      requestType: this.requestType,
      drivingLicenceDTO: this.drivingLicence,
      requestStatus: "PENDING"
    });

    this.drivingLicenceService.createDLchangeRequest(request).subscribe(
      data => {
        this.resultMsg = "*Request for change of driving licence was sent successfully"
        this.onViewTypeChange(this.viewType);
      },
      error => {
        this.resultMsg = "*There was an error while sending the request"
      }
    );
  }

}
