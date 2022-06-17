import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sluzbenik } from 'src/app/model/Sluzbenik';
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
export class SluzbenikService {
  
  private apiSluzbenikUrl = `${environment.apiURL}/api/sluzbenik`;

  constructor(private http: HttpClient) { }

  getSluzbenike(): Observable<Sluzbenik[]>{
    return this.http.get<Sluzbenik[]>(this.apiSluzbenikUrl);
  }

  getSluzbenikById(id:number): Observable<any>{
    return this.http.get(`${this.apiSluzbenikUrl}/${id}`);
    
  }

  updateSluzbenik(id:number,vrtici: Sluzbenik) : Observable<Sluzbenik>{
    return this.http.put<Sluzbenik>(`${this.apiSluzbenikUrl}/${id}`, JSON.stringify(vrtici),uploadHeader);

  }

  createSluzbenik(vrtici: Sluzbenik) : Observable<Sluzbenik>{
    return this.http.post<Sluzbenik>(this.apiSluzbenikUrl, vrtici, createHeader);
  }

  blockSluzbenik(id:any) : Observable<any>{
    return this.http.delete<Sluzbenik>(`${this.apiSluzbenikUrl}/${id}`);
  }
}

