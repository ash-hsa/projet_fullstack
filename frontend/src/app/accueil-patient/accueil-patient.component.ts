import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common'; 
import { RouterModule } from '@angular/router';
import { LoginService } from '../service/login.service';


@Component({
  selector: 'app-patient-accueil',
  standalone: true,
  imports: [FormsModule,MatListModule, MatButtonModule, MatIconModule, MatFormFieldModule, MatInputModule,CommonModule,RouterModule], 
  templateUrl: './accueil-patient.component.html',
  styleUrl: './accueil-patient.component.scss'
})
export class AccueilPatientComponent {
  patient = {
    nom:"",
    email:"",
    telephone:""
  };

  rendezVous = [
    { centre: 'Centre X', date: '15/02/2024' },
    { centre: 'Centre Y', date: '20/03/2024' }
  ];


  constructor(private loginService: LoginService,) {
    }

  ngOnInit() {
    let res = this.loginService.getinfos().subscribe(value => {
      this.patient.nom = value.name;
      this.patient.email = value.mail;
      this.patient.telephone = value.tel;
    });
  }
  
  annulerRendezVous(rdv: any) {
    this.rendezVous = this.rendezVous.filter(item => item !== rdv);
  }

  prendreRendezVous() {
    console.log("Redirection vers la prise de RDV");
    
  }

  modifierProfil() {
    console.log("Redirection vers la modification du profil");
    
  }
}
