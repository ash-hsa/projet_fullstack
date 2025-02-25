import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../model/user';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatButtonModule, MatInputModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  user : User = {pseudo : "", password:"",role:""};
  constructor(private loginService: LoginService, private router: Router) {
  }

  connect(): void { 
    this.loginService.connect(this.user.pseudo, this.user.password).subscribe(value => {
      let role=this.loginService.getrole();
      role.subscribe(value => {
        if(value=="sadmin"){
          this.router.navigate(['/accueil-admin']); //route peut etre à changer une fois la page super admin faite
        } else if(value=="admin"){
          this.router.navigate(['/admin-medecins']);
        } else {
          this.router.navigate(['/accueil-patient']);
        }
      })
      
    });
  }

}
