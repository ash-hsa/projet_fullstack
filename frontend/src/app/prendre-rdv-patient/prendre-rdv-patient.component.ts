import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common'; 
import { RouterModule } from '@angular/router';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { VaccinationCenter } from '../vaccination-center';
import { VaccinationService } from '../service/vaccination.service';

import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-prendre-rdv-patient',
  standalone: true,
  imports: [
    FormsModule, MatListModule, MatButtonModule, MatIconModule, MatFormFieldModule, 
    MatInputModule, CommonModule, RouterModule, MatSelectModule, MatDatepickerModule, 
    MatNativeDateModule, HttpClientModule
  ], 
  templateUrl: './prendre-rdv-patient.component.html',
  styleUrl: './prendre-rdv-patient.component.scss'
})
export class PrendreRdvPatientComponent implements OnInit {
  
  centres: VaccinationCenter[] = [];
  selectedCentre?: VaccinationCenter;
  selectedDate?: any;
  selectedHeure?: string;

  constructor(private vaccinationService: VaccinationService, private http: HttpClient,) {}
  patientId = -1; 
  doctorID = -1;

  ngOnInit(): void {
    // Récupérer la liste des centres via l'API
    this.vaccinationService.getAllVaccinationCenter().subscribe(data => {
      this.centres = data;
    });
  }

  // 🛠️ Fonction pour formater la date au format "yyyy-MM-dd HH:mm:ss"
  formatDateToBackend(date: Date, heure: string): string {
    const [hours, minutes] = heure.split(':').map(Number);
    date.setHours(hours, minutes, 0, 0);

    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hour = String(date.getHours()).padStart(2, '0');
    const minute = String(date.getMinutes()).padStart(2, '0');
    const second = "00"; // Fixe les secondes à 00

    return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
  }

  validerRendezVous() {
    

    this.http.get<any[]>(`http://localhost:8080/api/public/patients?name=${localStorage.getItem("name")}`).subscribe(data => {
      if (!this.selectedCentre || !this.selectedDate || !this.selectedHeure) {
        alert('Veuillez remplir tous les champs ❌');
        return;
      }
      if(data.length == 1) {
        this.patientId = data[0].id;
      }
      this.http.get<any[]>(`http://localhost:8080/api/public/center/${this.selectedCentre.id +1}/doctors`).subscribe(data => {
        if (!this.selectedCentre || !this.selectedDate || !this.selectedHeure) {
          alert('Veuillez remplir tous les champs ❌');
          return;
        }
        if(data.length == 1) {
          this.doctorID = data[0].id;
        }
        console.log("Doctor ID :", this.doctorID);// ✅ Vérifier les données reçues


        // 📅 Formatage correct de la date
        const formattedDate = this.formatDateToBackend(new Date(this.selectedDate), this.selectedHeure);

        // 📤 Objet rendez-vous à envoyer au backend
        const rendezVous = {
          date: formattedDate, // ✅ Date au bon format "yyyy-MM-dd HH:mm:ss"
          patient: { id: this.patientId  }, 
          docteur: { id: this.doctorID },
          center: { id: this.selectedCentre.id }
        };

        console.log("📤 Envoi du RDV :", rendezVous);

        // 🔄 Envoi de la requête au backend
        this.vaccinationService.prendreRendezVous(rendezVous).subscribe(response => {
          console.log("✅ Réponse API :", response);
          alert('Rendez-vous confirmé ✅');
        }, error => {
          console.error("❌ Erreur API :", error);
          alert('Erreur lors de la prise de rendez-vous ❌');
        });



      });

    });
    

    
  }
}
