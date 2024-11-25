import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../user';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  user : User = {pseudo : "", password:"",role:""};



}
