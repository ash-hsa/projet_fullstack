import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-accueil-admin',
  standalone: true,
  imports: [RouterModule, MatButtonModule],
  templateUrl: './accueil-admin.component.html',
  styleUrl: './accueil-admin.component.scss'
})
export class AccueilAdminComponent {

}
