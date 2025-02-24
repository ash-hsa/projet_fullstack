import { Component } from '@angular/core';
import { MedecinPatientSearchComponent } from '../medecin-patient-search/medecin-patient-search.component';

@Component({
  selector: 'app-accueil',
  standalone: true,
  imports: [MedecinPatientSearchComponent],
  templateUrl: './accueil.component.html',
  styleUrl: './accueil.component.scss'
})
export class AccueilComponent {

}
