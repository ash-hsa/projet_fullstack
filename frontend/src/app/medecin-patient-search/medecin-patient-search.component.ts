import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatListModule } from '@angular/material/list';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-medecin-patient-search',
  standalone: true,
  imports: [
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatListModule,
    FormsModule,
  ],
  templateUrl: './medecin-patient-search.component.html',
  styleUrls: ['./medecin-patient-search.component.scss']
})
export class MedecinPatientSearchComponent {

  constructor(private router: Router,private httpClient: HttpClient,private loginservice : LoginService) { }
  username="";
  post() {
    console.log('get '+this.username);
    this.httpClient.get<any>(`/api/public/patients?name=${this.username}`).subscribe({
      next: response => {
        console.log('Patient trouvé :', response);
        if(response.length==0){
          alert('Patient non trouvé');
        }else{
            this.httpClient.post(`/api/public/patients/${response[0].id}/vaccinate`, {}).subscribe({
            next: vaccinateResponse => {
              console.log('Patient vacciné :', vaccinateResponse);
              alert('Patient vacciné avec succès');
            },
            error: vaccinateError => {
              console.error('Erreur lors de la vaccination du patient :', vaccinateError);
              alert('Erreur lors de la vaccination du patient');
            }
            });
        }
      },
      error: error => {
        console.error('Erreur lors de la recherche du patient :', error);
        alert('Patient non trouvé');
      }
    });

  }
}
