import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../../environments/environment'
import { Vrtic } from 'src/app/model/Vrtic';


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
export class VrticiService {
 

  private apiVrticiUrl = `${environment.apiURL}/api/vrtic`;

  constructor(private http: HttpClient) { }

  getVrtici(): Observable<Vrtic[]>{
    return this.http.get<Vrtic[]>(this.apiVrticiUrl);
  }

  getVerticById(id:number): Observable<any>{
    return this.http.get(`${this.apiVrticiUrl}/${id}`);
    
  }

  getVerticByKonkursId(id: number): Observable<any> {
    return this.http.get(`${this.apiVrticiUrl}/konkurs/${id}`);
  }

  updateVrtic(id:number,vrtici: Vrtic) : Observable<Vrtic>{
    return this.http.put<Vrtic>(`${this.apiVrticiUrl}/${id}`, JSON.stringify(vrtici),uploadHeader);

  }

  createVrtic(vrtici: Vrtic) : Observable<Vrtic>{
    return this.http.post<Vrtic>(this.apiVrticiUrl, vrtici, createHeader);
  }

  blockVrtic(id:any) : Observable<any>{
    return this.http.delete<Vrtic>(`${this.apiVrticiUrl}/${id}`);
  }
}
