import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-accueil-super-admin',
  standalone: true,
  imports: [RouterModule, MatButtonModule],
  templateUrl: './accueil-super-admin.component.html',
  styleUrl: './accueil-super-admin.component.scss'
})
export class AccueilSuperAdminComponent {

}
