import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-ajouter-medecin',
  standalone: true,
  imports: [RouterModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatSelectModule],
  templateUrl: './ajout-medecin.component.html',
  styleUrl: './ajout-medecin.component.scss'
})
export class AjouterMedecinComponent {
  medecin = {
    nom: '',
    prenom: '',
    email: '',
    telephone: '',
    sexe: ''
  };

  constructor(private router: Router) {}

  ajouterMedecin() {
    console.log("Médecin ajouté :", this.medecin);
    this.router.navigate(['/admin-medecins']); // Retour à la liste après ajout
  }
}
