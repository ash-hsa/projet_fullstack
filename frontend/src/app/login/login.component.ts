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
      let infos=this.loginService.getinfos().subscribe(value => {
        if(value.role=="Sadmin"){
          this.router.navigate(['/accueil-admin']);
        } else if(value.role=="admin"){
          this.router.navigate(['/accueil-admin']);
        }
        else{
          this.router.navigate(['/accueil-patient']);
        }
    });
  });
  }
}
