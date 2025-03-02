import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common'; 

import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';




@Component({
  selector: 'app-modifier-profil-patient',
  standalone: true,
  imports: [FormsModule,MatListModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule,CommonModule, MatSelectModule, MatDatepickerModule,MatNativeDateModule], 
  templateUrl: './modifier-profil-patient.component.html',
  styleUrl: './modifier-profil-patient.component.scss'
})
export class ModifierProfilPatientComponent {

  user = { id: "", name: "", password : "", addressId : -1, tel : "", mail: "", doctor : false, sadmin : false, admin : false};
  options={};

  ngOnInit(): void {
    if(localStorage.getItem('authToken')==""){
      console.error('Erreur lors de la mise à jour du profil :');
      alert('Vous devez être connecté pour modifier votre profil !');
      this.router.navigate(['/login']);
      return;
    }else{
      this.options = {
        headers: {
          'Authorization': localStorage.getItem('authToken')
        }
      };
    }

    this.httpClient.get<{ id: string, name: string, password : string, addressId : number, tel : string, mail : string, doctor : boolean, sadmin : boolean, admin : boolean   }>('/api/me', this.options).subscribe({
      next: response => {
        console.log('Données utilisateur récupérées :', response);
        this.user.id = response.id;
        this.user.name= response.name;
        this.user.mail= response.mail;
        this.user.tel= response.tel;
        this.user.password= response.password;
        this.user.addressId= response.addressId;
        this.user.doctor= response.doctor;
        this.user.sadmin= response.sadmin;
        this.user.admin= response.admin
      },
      error: error => {
        console.error('Erreur lors de la récupération des données utilisateur :', error);
        alert('Vous devez être connecté pour modifier votre profil !');
        this.router.navigate(['/login']);
      }
    });
  }

  constructor(private httpClient: HttpClient, private router: Router) {}

  modifierProfil() {
    console.log('Profil mis à jour :', this.user);
   
    this.httpClient.post('/api/admin/users', this.user, this.options).subscribe(response => {
      console.log('Réponse du serveur :', response);
    }, error => {
      console.error('Erreur lors de la mise à jour du profil :', error);
    });
    alert('Votre profil a bien été mis à jour !');
    this.router.navigate(['/accueil-patient']);
  }

}
