import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { RouterModule } from '@angular/router';
import { VaccinationCenterComponent } from './vaccination-center/vaccination-center.component';
import { VaccinationCenterListComponent } from "./vaccination-center-list/vaccination-center-list.component";
import { MatToolbarModule } from '@angular/material/toolbar';
import { LoginService } from './service/login.service';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MatToolbarModule, RouterModule ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Sting Me';

  constructor(private router: Router,private loginservice: LoginService) {}


  to_accueil() : void {
    if(localStorage.getItem('role') == "sadmin"){
      this.router.navigate(['/accueil-super-admin']);
    } else if(localStorage.getItem('role') == "admin"){
      this.router.navigate(['/accueil-admin']);
    }else{
      this.router.navigate(['/accueil-patient']);
    }
  }

  logout() : void {
    this.loginservice.logout();
    this.router.navigate(['/login']);
  }


}
