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

  private baseURL = "http://localhost:6001/api/driving-licence";
  constructor(private httpClient: HttpClient) { }

  // medical certificate
  createMedicalCertificate(): Observable<string> {
    return this.httpClient.get<string>(this.baseURL + "/create/medical-certificate");
  }

  // FETCHING REQUESTS
  getDrivingLicenceById(id: number): Observable<DrivingLicence> {
    return this.httpClient.get<DrivingLicence>(`${this.baseURL}/${id}`);
  }

  getDrivingLicenceForUser(userId: string): Observable<DrivingLicence> {
    return this.httpClient.get <DrivingLicence>(`${this.baseURL}/user/${userId}`);
  }

  getRequests(page: number, requestStatus: string): Observable<RequestForDrivingLicence[]> {
    return this.httpClient.get<RequestForDrivingLicence[]>(`${this.baseURL}/get/requests?page=` + page + "&requestStatus=" + requestStatus);
  }

  getEditRequests(page: number, requestStatus: string): Observable<DrivingLicenceChangeRequest[]> {
    return this.httpClient.get<DrivingLicenceChangeRequest[]>(`${this.baseURL}/get/edit-requests?page=` + page + "&requestStatus=" + requestStatus);
  }

  getCreateRequestById(id: number): Observable<RequestForDrivingLicence> {
    return this.httpClient.get<RequestForDrivingLicence>(`${this.baseURL}/get/requests/${id}`);
  }

  // VALIDATION REQUESTS
  getPendingRequestIfExist(userId: string): Observable<RequestForDrivingLicence> {
    return this.httpClient.get<RequestForDrivingLicence>(`${this.baseURL}/get/pending-request/citizen/${userId}`);
  }

  getPendingEditRequestIfExist(userId: string): Observable<DrivingLicenceChangeRequest> {
    return this.httpClient.get<DrivingLicenceChangeRequest>(`${this.baseURL}/get/pending-edit-request/citizen/${userId}`);
  }

  // CREATE REQUESTS
  createDL(requestForDL: RequestForDrivingLicence): Observable<DrivingLicence> {
    return this.httpClient.post<DrivingLicence>(`${this.baseURL}/create`, requestForDL);
  }

  createDLrequest(requestForDrivingLicence: RequestForDrivingLicence): Observable<RequestForDrivingLicence> {
    return this.httpClient.post<RequestForDrivingLicence>(`${this.baseURL}/create/request`, requestForDrivingLicence);
  }

  createDLchangeRequest(drivingLicenceChangeRequest: DrivingLicenceChangeRequest): Observable<DrivingLicenceChangeRequest> {
    return this.httpClient.post<DrivingLicenceChangeRequest>(`${this.baseURL}/create/edit-request`, drivingLicenceChangeRequest);
  }

  // EDIT REQUESTS
  editDLrequest(requestForDrivingLicence: RequestForDrivingLicence): Observable<RequestForDrivingLicence> {
    return this.httpClient.put<RequestForDrivingLicence>(`${this.baseURL}/edit/request`, requestForDrivingLicence);
  }

  editDLchangeRequest(drivingLicenceChangeRequest: DrivingLicenceChangeRequest): Observable<DrivingLicenceChangeRequest> {
    return this.httpClient.put<DrivingLicenceChangeRequest>(`${this.baseURL}/edit`, drivingLicenceChangeRequest);
  }



}
