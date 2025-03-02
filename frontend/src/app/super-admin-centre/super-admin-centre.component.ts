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
    if(localStorage.getItem("role")!="sadmin"){
      console.log("Accès refusé");
      window.location.href = '/login';
    }
    this.onconnect(); // 🔹 Récupère l’admin connecté avant de charger les centres
  }

  onconnect() {
    this.http.get<any>("/api/public/centers").subscribe(data=>{
      this.centers=data;
    });
  }

  

  supprimerCentre(center: any) {
    this.http.delete(`/api/admin/center/${center.id}`).subscribe({
      next: (data) => {
        console.log("✅ Centre supprimé :", data);
        this.onconnect();
      },
      error: (err) => {
        console.error("⚠️ Erreur lors de la suppression du centre :", err);
      }
    });
  }
}
