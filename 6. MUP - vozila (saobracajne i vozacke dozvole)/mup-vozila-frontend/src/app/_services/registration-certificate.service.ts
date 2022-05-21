import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root'
})
export class RegistrationCertificateService {

  private api = "http://localhost:6001/registration-certificate/"

  constructor(private http: HttpClient) { }

  createRequest(request: any){
    return this.http.post(this.api, request, httpOptions)
  }

  getRequests(){
    return this.http.get(this.api)
  }

  getRequest(id: number){
    const url = `${this.api}/${id}`
    return this.http.get(url)
  }
}
