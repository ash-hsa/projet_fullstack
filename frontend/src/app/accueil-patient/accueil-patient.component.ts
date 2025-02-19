import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common'; 
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-patient-accueil',
  standalone: true,
  imports: [FormsModule,MatListModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule,CommonModule,RouterModule], 
  templateUrl: './accueil-patient.component.html',
  styleUrl: './accueil-patient.component.scss'
})
export class AccueilPatientComponent {
  patient = {
    nom: 'Jean Dupont',
    email: 'jean.dupont@mail.com',
    telephone: '06 12 34 56 78'
  };

  rendezVous = [
    { centre: 'Centre X', date: '15/02/2024' },
    { centre: 'Centre Y', date: '20/03/2024' }
  ];

  annulerRendezVous(rdv: any) {
    this.rendezVous = this.rendezVous.filter(item => item !== rdv);
  }

  prendreRendezVous() {
    console.log("Redirection vers la prise de RDV");
    
  }

  modifierProfil() {
    console.log("Redirection vers la modification du profil");
    
  }
}
