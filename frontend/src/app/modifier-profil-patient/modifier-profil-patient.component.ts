import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common'; 

import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { Router } from '@angular/router';




@Component({
  selector: 'app-modifier-profil-patient',
  standalone: true,
  imports: [FormsModule,MatListModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule,CommonModule, MatSelectModule, MatDatepickerModule,MatNativeDateModule], 
  templateUrl: './modifier-profil-patient.component.html',
  styleUrl: './modifier-profil-patient.component.scss'
})
export class ModifierProfilPatientComponent {

  patient = { nom: '', prenom: '', email: '', telephone: '' };

  constructor(private router: Router) {}

  modifierProfil() {
    console.log('Profil mis à jour :', this.patient);
    alert('Votre profil a bien été mis à jour !');
    this.router.navigate(['/patient-accueil']);
  }

}
