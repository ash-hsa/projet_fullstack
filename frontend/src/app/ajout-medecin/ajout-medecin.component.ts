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
  adminConnecte = { centerId: 1 }; // 🔹 Simule l'admin connecté (centre 1)

  medecin = {
    name: '',
    password: '',
    isDoctor: true,
    isSAdmin: false,
    addressId: this.adminConnecte.centerId

  };

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit() {
    console.log("Admin connecté - Centre ID:", this.adminConnecte.centerId);
  }

  ajouterMedecin() {
    const url = "http://localhost:8080/api/admin/users"; 
    const token = localStorage.getItem("authToken");

    if (!token) {
        console.error("❌ Erreur : Pas de token trouvé. Connecte-toi d'abord !");
        return;
    }

    // 🔹 Construire l'objet médecin
    const nouveauMedecin = {
        name: this.medecin.name,
        password: this.medecin.password || "defaultpass",
        isDoctor: true,  // 🩺 Forcer ici
        isSAdmin: false,
        addressId: 1
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
