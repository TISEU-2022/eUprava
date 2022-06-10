import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../../environments/environment'
import { Vrtic } from 'src/app/model/Vrtic';
import { Konkurs } from 'src/app/model/Konkurs';


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
export class KonkursiService {

  private apiKonkursUrl = `${environment.apiURL}/api/konkurs`;

  constructor(private http: HttpClient) { }

  getKonkurs(): Observable<Konkurs[]>{
    return this.http.get<Konkurs[]>(this.apiKonkursUrl);
  }

  getKonkursById(id:number): Observable<any>{
    return this.http.get(`${this.apiKonkursUrl}/${id}`);
    
  }

  updateKonkurs(id:number,konkursi: Konkurs) : Observable<Konkurs>{
    return this.http.put<Konkurs>(`${this.apiKonkursUrl}/${id}`, JSON.stringify(konkursi),uploadHeader);

  }

  createKonkurs(konkursi: Konkurs) : Observable<Konkurs>{
    return this.http.post<Konkurs>(this.apiKonkursUrl, konkursi, createHeader);
  }

  blockKonkurs(id:any) : Observable<any>{
    return this.http.delete<Konkurs>(`${this.apiKonkursUrl}/${id}`);
  }
}
