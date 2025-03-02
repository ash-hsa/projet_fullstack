import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';

import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-super-admin-gestion-admin',
  standalone: true,
  imports: [
    HttpClientModule, 
    MatListModule, 
    MatButtonModule, 
    MatIconModule, 
    CommonModule, 
    RouterModule
  ],
  templateUrl: './super-admin-gestion-admin.component.html',
  styleUrls: ['./super-admin-gestion-admin.component.scss']
})

export class SuperAdminGestionAdminComponent implements OnInit {
  apiUrl = 'http://localhost:8080/';
  admins: any[] = []; 

  constructor(private http: HttpClient) {}

  ngOnInit() {
    if(localStorage.getItem("role")!="sadmin"){
      console.log("Accès refusé");
      window.location.href = '/login';
    }

    this.http.get<any>("/api/admin/users").subscribe(data=>{
      console.log(data)
      for(let i=0; i<data.length; i++){
        if(data[i].admin){
          this.admins.push(data[i]);
        }
        
      }
    }
    );

    
  }

  supprimerCentre(admin: any) {
    if (!confirm(`Voulez-vous vraiment supprimer ${admin.name} ?`)) {
      return; // Annuler si l'utilisateur ne confirme pas
    }

    const url = `http://localhost:8080/api/admin/user/${admin.id}`;
    const headers = {
      'Authorization': localStorage.getItem("authToken") || "", // 🔹 Ajoute le token
      'Content-Type': 'application/json'
    };

    this.http.delete(url, { headers }).subscribe({
      next: () => {
        this.admins = this.admins.filter(m => m.id !== admin.id);
        console.log(`✅ Centre ${admin.name} supprimé`);
      },
      error: (err) => {
        console.error('❌ Erreur lors de la suppression du centre:', err);
      }
    });
  }
}
