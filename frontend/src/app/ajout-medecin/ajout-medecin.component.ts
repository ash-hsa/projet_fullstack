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
    is_doctor: true,
    is_s_admin: false,
    address_id: this.adminConnecte.centerId // 🔹 Fixe directement l'ID du centre
  };

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit() {
    console.log("Admin connecté - Centre ID:", this.adminConnecte.centerId);
  }

  ajouterMedecin() {
    console.log("Médecin envoyé :", this.medecin);

    this.http.post('http://localhost:8080/api/medecins', this.medecin)
      .subscribe(response => {
        console.log("Médecin ajouté :", response);
        this.router.navigate(['/admin-medecins']); 
      }, error => {
        console.error("Erreur lors de l'ajout", error);
      });
}
}
