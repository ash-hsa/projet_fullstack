import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-accueil-admin',
  standalone: true,

  imports: [RouterModule,HttpClientModule, MatButtonModule ],

  templateUrl: './accueil-admin.component.html',
  styleUrl: './accueil-admin.component.scss'
})
export class AccueilAdminComponent {
  constructor(private http: HttpClient) {}

  ngOnInit() {
    if(localStorage.getItem("role")=="user"){
      console.log("Accès refusé");
      window.location.href = '/accueil-patient';
    }
  }
}
