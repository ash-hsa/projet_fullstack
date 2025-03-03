import { Component } from '@angular/core';
import { MedecinPatientSearchComponent } from '../medecin-patient-search/medecin-patient-search.component';
import { HttpClient } from '@angular/common/http';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-accueil',
  standalone: true,
  imports: [MedecinPatientSearchComponent],
  templateUrl: './accueil.component.html',
  styleUrl: './accueil.component.scss'
})
export class AccueilComponent {

  constructor(private http: HttpClient, private loginservice : LoginService) { }

  ngOnInit() {
    let options = {
      headers: {
        'Authorization': this.loginservice.getBasicAuthHeaderValue()
      }
    };

    interface response {
      doctor: boolean;
    }

    this.http.get<response>(`http://localhost:8080/api/me`, options).subscribe(
      response => {
        console.log('Utilisateur connecté :', response);
        if(!response.doctor){
          window.location.href = '/login';
          alert("Vous devez etre un medecin pour acceder a cette page !");
        }
      },
      error => {
        window.location.href = '/login';
        alert("Vous devez etre un medecin pour acceder a cette page !");
      }
    );
  }
}
