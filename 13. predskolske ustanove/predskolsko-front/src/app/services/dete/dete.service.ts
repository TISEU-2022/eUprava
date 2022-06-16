import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Dete } from 'src/app/model/Dete';
import { environment } from 'src/environments/environment';

const createHeader = {
  headers: new HttpHeaders({
    'method':'POST',
    'Content-Type': 'application/json',
  }),
};

const uploadHeader = {
  headers: new HttpHeaders({
    'method':'PUT',
    'Content-Type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root'
})
export class DeteService {

  private apiDeteUrl = `${environment.apiURL}/api/dete`;

  constructor(private http: HttpClient) { }

  getDeca(): Observable<Dete[]>{
    return this.http.get<Dete[]>(this.apiDeteUrl);
  }

  getDeteById(id:number): Observable<any>{
    return this.http.get(`${this.apiDeteUrl}/${id}`);
    
  }

  createDete(deca: Dete) : Observable<Dete>{
    return this.http.post<Dete>(this.apiDeteUrl, deca, createHeader);
  }

  blockDete(id:any) : Observable<any>{
    return this.http.delete<Dete>(`${this.apiDeteUrl}/${id}`);
  }
}
