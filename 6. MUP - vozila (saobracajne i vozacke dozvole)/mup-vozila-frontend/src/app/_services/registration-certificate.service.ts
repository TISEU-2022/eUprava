import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import {RegistrationCertificate} from "../_models/registration-certificate.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RegistrationCertificateService {

  private api = "http://localhost:6001/api/registration-certificates"

  constructor(private http: HttpClient) { }

  createRequest(request: RegistrationCertificate): Observable<RegistrationCertificate>{
    return this.http.post<RegistrationCertificate>(`${this.api}/requests`, request)
  }

  createCertificate(id: number, request: RegistrationCertificate){
    const url = `${this.api}/${id}`
    return this.http.put(url, request)
  }

  getRequests(): Observable<RegistrationCertificate[]>{
    return this.http.get<RegistrationCertificate[]>(this.api + "/requests")
  }

  getRequest(id: number): Observable<RegistrationCertificate>{
    const url = `${this.api}/requests/${id}`
    return this.http.get<RegistrationCertificate>(url)
  }

  getRequestForUser(userId: string): Observable<RegistrationCertificate>{
    const url = `${this.api}/requests/user/${userId}`
    return this.http.get<RegistrationCertificate>(url)
  }

  declineRequest(id: number){
    const url = `${this.api}/requests/${id}`
    return this.http.delete(url)
  }
}
