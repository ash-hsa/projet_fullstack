import { Component } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common'; 


@Component({
  selector: 'app-admin-reservations',
  standalone: true,
  imports: [MatListModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule,CommonModule],
  templateUrl: './admin-reservations.component.html',
  styleUrls: ['./admin-reservations.component.scss']
})
export class AdminReservationsComponent {
  rendezVous = [
    { patient: 'Jean Dupont', date: '15/02/2024' },
    { patient: 'Claire Martin', date: '16/02/2024' }
  ];

  annulerRendezVous(rdv: any) {
    this.rendezVous = this.rendezVous.filter(r => r !== rdv);
  }
}
