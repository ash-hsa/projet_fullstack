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
    localStorage.setItem('authToken', token);
    
    let res = this.httpClient.get<string>('/api/public/login', options).pipe(map(value => {
      this.password = password;
      this.username = username;
      this.getrole().subscribe(value => {localStorage.setItem('role', value);});
    }))
    
    return res;
  }
  


  getBasicAuthHeaderValue(): string {
    return this.createToken(this.username, this.password)
  }

  authHasBasic(): boolean {
    return !!this.password && !!this.username;
  }

  getrole(): Observable<string> {
    let role="";
    let options = {
      headers: {
      'Authorization': this.getBasicAuthHeaderValue()
      }
    };

    return new Observable<string>(observer => {
      this.httpClient.get<string>('/api/me', options).subscribe(value => {
      let me: any = value;
      if(me.sadmin){
        observer.next("sadmin");
      } else if(me.admin){
        observer.next("admin");
      } else {
        observer.next("user");
      }
      observer.complete();
      }, error => {
      observer.error(error);
      });
    });
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
