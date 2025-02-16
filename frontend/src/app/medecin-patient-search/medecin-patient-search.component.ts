import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatListModule } from '@angular/material/list';

@Component({
  selector: 'app-medecin-patient-search',
  standalone: true,
  imports: [
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatListModule
  ],
  templateUrl: './medecin-patient-search.component.html',
  styleUrls: ['./medecin-patient-search.component.scss']
})
export class MedecinPatientSearchComponent {}
