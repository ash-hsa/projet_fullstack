import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common'; 

@Component({
  selector: 'app-admin-medecins',
  standalone: true,
  imports: [FormsModule,MatListModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule,CommonModule], 
  templateUrl: './admin-medecins.component.html',
  styleUrls: ['./admin-medecins.component.scss']
})
export class AdminMedecinsComponent {
  newMedecin: string = '';
  medecins = [
    { nom: 'Dr. Dupont' },
    { nom: 'Dr. Martin' },
    { nom: 'Dr. Bernard' }
  ];

  ajouterMedecin() {
    if (this.newMedecin.trim()) {
      this.medecins.push({ nom: this.newMedecin });
      this.newMedecin = ''; 
    }
  }

  supprimerMedecin(medecin: any) {
    this.medecins = this.medecins.filter(m => m !== medecin);
  }
}
