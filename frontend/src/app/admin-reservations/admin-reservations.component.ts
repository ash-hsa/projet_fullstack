import { Component } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common'; 
import { RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { LoginService } from '../service/login.service';


@Component({
  selector: 'app-admin-reservations',
  standalone: true,
  imports: [MatListModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule,CommonModule,RouterModule],
  templateUrl: './admin-reservations.component.html',
  styleUrls: ['./admin-reservations.component.scss']
})
export class AdminReservationsComponent {
  rendezVous = [
    { id: -1,patient: '', date: '2021-06-01' },
  ];


  constructor(private http: HttpClient, private loginservice : LoginService) {}
  
  ngOnInit() {
    if(localStorage.getItem("role")=="user"){
      console.log("Accès refusé");
      window.location.href = '/accueil-patient';
      return;
    }
    let options = {
      headers: {
        'Authorization': this.loginservice.getBasicAuthHeaderValue()
      }
    };
    let addressId = -1;
    this.http.get('/api/me',options).subscribe((data: any) => { 
      addressId = data.addressId;
      if(addressId == -1){
        console.error('Erreur lors de la récupération de l’admin connecté :');
        alert('Vous devez être connecté pour accéder à cette page !');
        window.location.href = '/login';
        return;
      }
      this.http.get(`http://localhost:8080/api/public/timeslots/center/${addressId}`).subscribe((data: any) => {
        for (let i = 0; i < data.length; i++) {
          this.rendezVous.push({ id: data[i].id, patient: data[i].patient.name, date: data[i].date });
        }
        this.rendezVous.shift();
      });
    });

    


  }

  annulerRendezVous(rdv: any) {
    const options = {
      headers: {
        'Authorization': this.loginservice.getBasicAuthHeaderValue()
      }
    };
    this.http.delete(`http://localhost:8080/api/public/timeslots/${rdv.id}`, options).subscribe(
      response => {
        console.log('Rendez-vous annulé avec succès');
        this.rendezVous = this.rendezVous.filter(r => r !== rdv);
      },
      error => {
        console.error('Erreur lors de l\'annulation du rendez-vous', error);
      }
    );
  }
  
}
