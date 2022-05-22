import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DrivingLicence } from '../_models/driving-licence';
import { DrivingLicenceChangeRequest } from '../_models/driving-licence-change-request';
import { RequestForDrivingLicence } from '../_models/request-for-driving-licence';

@Injectable({
  providedIn: 'root'
})
export class DrivingLicenceService {

  private baseURL = "http://localhost:8080/api/driving-licence";
  constructor(private httpClient: HttpClient) { }

  getDrivingLicenceForUser(userId: string): Observable<DrivingLicence> {
    return this.httpClient.get <DrivingLicence>(`${this.baseURL}/user/${userId}`);
  }

  getPendingRequestIfExist(userId: string): Observable<RequestForDrivingLicence> {
    return this.httpClient.get<RequestForDrivingLicence>(`${this.baseURL}/get/pending-request/citizen/${userId}`);
  }

  getPendingEditRequestIfExist(userId: string): Observable<DrivingLicenceChangeRequest> {
    return this.httpClient.get<DrivingLicenceChangeRequest>(`${this.baseURL}/get/pending-edit-request/citizen/${userId}`);
  }

  createDLrequest(requestForDrivingLicence: RequestForDrivingLicence): Observable<RequestForDrivingLicence> {
    return this.httpClient.post<RequestForDrivingLicence>(`${this.baseURL}/create/request`, requestForDrivingLicence);
  }

  createDLchangeRequest(drivingLicenceChangeRequest: DrivingLicenceChangeRequest): Observable<DrivingLicenceChangeRequest> {
    return this.httpClient.post<DrivingLicenceChangeRequest>(`${this.baseURL}/create/edit-request`, drivingLicenceChangeRequest);
  }

  getRequests(page: number, requestStatus: string): Observable<RequestForDrivingLicence[]> {
    return this.httpClient.get<RequestForDrivingLicence[]>(`${this.baseURL}/get/requests?page=` + page + "&requestStatus=" + requestStatus);
  }

  getEditRequests(page: number, requestStatus: string): Observable<DrivingLicenceChangeRequest[]> {
    return this.httpClient.get<DrivingLicenceChangeRequest[]>(`${this.baseURL}/get/edit-requests?page=` + page + "&requestStatus=" + requestStatus);
  }

}
