import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';

import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-super-admin-centres',
  standalone: true,
  imports: [
    HttpClientModule, 
    MatListModule, 
    MatButtonModule, 
    MatIconModule, 
    CommonModule, 
    RouterModule
  ],
  templateUrl: './super-admin-centre.component.html',
  styleUrls: ['./super-admin-centre.component.scss']
})

export class SuperAdminCentreComponent implements OnInit {
  apiUrl = 'http://localhost:8080/';
  adminConnecte: any = null; // ✅ Dynamique
  centers: any[] = []; 

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getAdminConnecte(); // 🔹 Récupère l’admin connecté avant de charger les centres
  }

  getAdminConnecte() {
    const url = 'http://localhost:8080/api/me'; // 🔹 Endpoint pour récupérer l'utilisateur connecté

    const headers = {
      'Authorization': localStorage.getItem("authToken") || "", // 🔐 Ajoute le token
      'Content-Type': 'application/json'
    };

    this.http.get<any>(url, { headers }).subscribe({
      next: (user) => {
        this.adminConnecte = user;
        console.log("✅ Admin connecté :", user);
        
        if (this.adminConnecte.addressId) {
          this.chargerCentres(); // ✅ Charge les centress dynamiquement après récupération de l'admin
        } else {
          console.warn("⚠️ L’admin connecté n’a pas d’adresse ID !");
        }
      },
      error: (err) => {
        console.error('❌ Erreur lors de la récupération de l’admin connecté:', err);
      }
    });
  }

  chargerCentres() {
    if (!this.adminConnecte || !this.adminConnecte.addressId) {
      console.warn("⚠️ Aucun centre ID disponible pour cet admin !");
      return;
    }

    const url = `${this.apiUrl}${this.adminConnecte.addressId}/centers`;
    
    const headers = {
      'Authorization': localStorage.getItem("authToken") || "", // 🔐 Ajoute le token
      'Content-Type': 'application/json'
    };

    this.http.get<any[]>(url, { headers }).subscribe({
      next: (data) => {
        this.centers = data;
        console.log("✅ Centress chargés :", data);
      },
      error: (err) => {
        console.error('❌ Erreur lors du chargement des centres:', err);
      }
    });
  }

  supprimerCentre(center: any) {
    if (!confirm(`Voulez-vous vraiment supprimer ${center.name} ?`)) {
      return; // Annuler si l'utilisateur ne confirme pas
    }

    const url = `http://localhost:8080/api/admin/center/${center.id}`;
    const headers = {
      'Authorization': localStorage.getItem("authToken") || "", // 🔹 Ajoute le token
      'Content-Type': 'application/json'
    };

    this.http.delete(url, { headers }).subscribe({
      next: () => {
        this.centers = this.centers.filter(m => m.id !== center.id);
        console.log(`✅ Centre ${center.name} supprimé`);
      },
      error: (err) => {
        console.error('❌ Erreur lors de la suppression du centre:', err);
      }
    });
  }
}
