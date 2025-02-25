import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms'; 
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-patient-accueil',
  standalone: true,
  imports: [
    CommonModule, 
    HttpClientModule, 
    FormsModule, 
    MatListModule, 
    MatButtonModule, 
    MatIconModule, 
    MatFormFieldModule, 
    MatInputModule, 
    RouterModule
  ], 
  templateUrl: './accueil-patient.component.html',
  styleUrl: './accueil-patient.component.scss'
})
export class AccueilPatientComponent implements OnInit { 
  patientId = 1; // ID du patient en dur pour l’instant
  rendezVous: any[] = [];
  patient: any = {}; // ✅ Ajout de patient

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadPatient();
    this.loadRendezVous();
  }

  // ✅ Récupération du patient
  loadPatient() {
    this.http.get<any>(`http://localhost:8080/api/public/patient/${this.patientId}`)
      .subscribe(data => {
        this.patient = data;
      });
  }

  loadRendezVous() {
    this.http.get<any[]>(`http://localhost:8080/api/public/timeslots/patient/${this.patientId}`)
      .subscribe(data => {
        console.log("RDV récupérés :", data); // ✅ Vérifier les données reçues
        this.rendezVous = data;
      });
  }
  

  annulerRendezVous(rdv: any) {
    this.rendezVous = this.rendezVous.filter(item => item !== rdv);
  }
}
