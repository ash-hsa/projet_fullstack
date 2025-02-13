import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-medecin-patient-search',
  standalone: true,
  templateUrl: './medecin-patient-search.component.html',
  styleUrls: ['./medecin-patient-search.component.scss']
})
export class MedecinPatientSearchComponent {
  /* searchTerm: string = '';
  patients: any[] = [];

  // Liste de patients factices pour tester l'affichage
  fakePatients = [
    { id: 1, nom: 'Dupont', prenom: 'Jean' },
    { id: 2, nom: 'Martin', prenom: 'Claire' },
    { id: 3, nom: 'Bernard', prenom: 'Louis' }
  ];

  constructor(private router: Router) {}

  onSearch() {
    if (this.searchTerm.length > 1) {
      // Filtrer la liste factice au lieu d'appeler un backend
      this.patients = this.fakePatients.filter(patient =>
        patient.nom.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    } else {
      this.patients = [];
    }
  }

  goToValidation(patient: any) {
    this.router.navigate(['/medecin/validation'], { queryParams: { id: patient.id } });
  } */
}
