import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { map, Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private isLoggedSubject: Subject<boolean> = new Subject();
  
  private password?: string;
  private username?: string;

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  connect(username: string, password: string): Observable<any> {
    let token = this.createToken(username, password);
    let options = {
      headers: {
        'Authorization': token
      }
    };
    return this.httpClient.get<string>('/api/login', options).pipe(map(value => {
      this.password = password;
      this.username = username;
      console.log("Connected")
    }))
  }


  private createToken(username?: string, password?: string) {
    let token = `Basic ` + btoa(`${username}:${password}`);
    return token;
  }


  logout() {
    console.log("Logout")
    
    this.password = undefined;
    this.username = undefined;
    
    this.isLoggedSubject.next(false);
    this.router.navigateByUrl("/login").then(console.log).catch(console.error)
  }
}
