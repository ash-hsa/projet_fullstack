import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';

import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-admin-medecins',
  standalone: true,
  imports: [
    HttpClientModule, 
    MatListModule, 
    MatButtonModule, 
    MatIconModule, 
    CommonModule, 
    RouterModule
  ],
  templateUrl: './admin-medecins.component.html',
  styleUrls: ['./admin-medecins.component.scss']
})


export class AdminMedecinsComponent implements OnInit {
  apiUrl = 'http://localhost:8080/api/public/center/';
  adminConnecte = { centerId: 1 }; // Simule l'admin connecté
  medecins: any[] = []; 

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.chargerMedecins();
  }

  chargerMedecins() {
    const url = `${this.apiUrl}${this.adminConnecte.centerId}/doctors`;

    this.http.get<any[]>(url).subscribe({
      next: (data) => {
        this.medecins = data;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des médecins:', err);
      }
    });
  }

  supprimerMedecin(medecin: any) {
    if (!confirm(`Voulez-vous vraiment supprimer ${medecin.name} ?`)) {
      return; // Annuler si l'utilisateur ne confirme pas
    }
  
    const url = `http://localhost:8080/api/admin/user/${medecin.id}`;
  
    this.http.delete(url).subscribe({
      next: () => {
        // Supprimer du tableau local après succès
        this.medecins = this.medecins.filter(m => m.id !== medecin.id);
      },
      error: (err) => {
        console.error('Erreur lors de la suppression du médecin:', err);
      }
    });
  }
  
}