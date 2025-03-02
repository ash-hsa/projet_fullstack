import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ajouter-medecin',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './ajout-medecin.component.html',
  styleUrl: './ajout-medecin.component.scss'
})
export class AjouterMedecinComponent implements OnInit {
  adminConnecte: any = {}; // 🟡 Stocke l'admin connecté dynamiquement

  medecin = {
    name: '',
    password: '',
    isDoctor: true,
    isSAdmin: false,
    addressId: null  // 🟡 À remplir dynamiquement
  };

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit() {
    if(localStorage.getItem('role') == "user"){
      alert('Vous devez être connecté en tant qu\'administrateur pour accéder à cette page !');
      window.location.href = '/login';
    }
    this.chargerAdminConnecte()
  }

  // 🟢 Récupère les infos de l'admin connecté
  chargerAdminConnecte() {
    const token = localStorage.getItem("authToken");

    if (!token) {
      console.error("❌ Erreur : Aucun token trouvé. Connecte-toi d'abord !");
      return;
    }

    const headers = { 'Authorization': token };
    
    this.http.get<any>("http://localhost:8080/api/me", { headers }).subscribe({
      next: (admin) => {
        this.adminConnecte = admin;
        this.medecin.addressId = admin.addressId; // 🟢 Dynamiser l'ID du centre
        console.log("✅ Admin connecté :", admin);
      },
      error: (err) => {
        console.error("❌ Erreur lors de la récupération de l'admin connecté :", err);
      }
    });
  }

  ajouterMedecin() {
    const url = "http://localhost:8080/api/admin/users"; 
    const token = localStorage.getItem("authToken");

    if (!token) {
        console.error("❌ Erreur : Pas de token trouvé. Connecte-toi d'abord !");
        return;
    }

    // 🟢 Construire dynamiquement l'objet médecin
    const nouveauMedecin = {
        name: this.medecin.name,
        password: this.medecin.password || "defaultpass",
        isDoctor: true,
        isSAdmin: false,
        addressId: this.medecin.addressId // 🟢 Dynamique selon l'admin connecté
    };

    console.log("📤 Données envoyées :", JSON.stringify(nouveauMedecin, null, 2));

    const headers = {
        'Authorization': token,
        'Content-Type': 'application/json'
    };

    this.http.post(url, nouveauMedecin, { headers }).subscribe({
        next: (response) => {
            console.log("✅ Médecin ajouté avec succès :", response);
            alert("Médecin ajouté !");
            this.router.navigate(['/admin-medecins']);
        },
        error: (err) => {
            console.error("❌ Erreur lors de l’ajout du médecin:", err);
        }
    });
  }
}
