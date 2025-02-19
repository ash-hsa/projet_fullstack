import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common'; 
import { RouterModule } from '@angular/router';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';


@Component({
  selector: 'app-prendre-rdv-patient',
  standalone: true,
  imports: [FormsModule,MatListModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule,CommonModule,RouterModule, MatSelectModule, MatDatepickerModule,MatNativeDateModule], 
  templateUrl: './prendre-rdv-patient.component.html',
  styleUrl: './prendre-rdv-patient.component.scss'
})
export class PrendreRdvPatientComponent {
  centres = ['Centre Paris', 'Centre Lyon', 'Centre Marseille'];
  selectedCentre = '';
  selectedDate: any;
  selectedHeure = '';

  validerRendezVous() {
    if (this.selectedCentre && this.selectedDate && this.selectedHeure) {
      console.log(`Rendez-vous pris à ${this.selectedCentre} le ${this.selectedDate} à ${this.selectedHeure}`);
      alert('Rendez-vous confirmé ✅');
    } else {
      alert('Veuillez remplir tous les champs ❌');
    }
  }

}
