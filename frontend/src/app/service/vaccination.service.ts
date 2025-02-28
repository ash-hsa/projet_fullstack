import { Injectable } from '@angular/core';
import { VaccinationCenter } from '../vaccination-center';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';




@Injectable({
  providedIn: 'root'
})



export class VaccinationService {

  
  

  constructor(private http: HttpClient) { }

  getAllVaccinationCenter() : Observable<VaccinationCenter[]>{
    return this.http.get<VaccinationCenter[]>("api/public/centers");
  }

  getCenterById(id: Number) : Observable<VaccinationCenter>{
    return this.http.get<VaccinationCenter>("api/public/center/"+id);
  }

  prendreRendezVous(rendezVous: any): Observable<any> {
    return this.http.post<any>("http://localhost:8080/api/public/timeslots", rendezVous);
  }
  
}