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
      this.getinfos();
    }))
    return res;
  }
  


  getBasicAuthHeaderValue(): string {
    return this.createToken(this.username, this.password)
  }

  authHasBasic(): boolean {
    return !!this.password && !!this.username;
  }

  getinfos(): Observable<any> {
    let options = {
      headers: {
        'Authorization': this.getBasicAuthHeaderValue()
      }
    };
  
    return this.httpClient.get<any>('/api/me', options).pipe(map(me => {
      let res = {"role":"", "name":"", "tel":"", "mail":""};
      if(me.sadmin){
        res["role"]="sadmin";
      } else if(me.admin){
        res["role"]="admin";
      } else {
        res["role"]="user";
      }
      res["name"]=me.name;
      res["tel"]=me.tel;
      res["mail"]=me.mail;
      localStorage.setItem('role', res["role"]);
      localStorage.setItem('name', res["name"]);
      localStorage.setItem('tel', res["tel"]);
      localStorage.setItem('mail', res["mail"]);
      return me;
    }));
  }

  private createToken(username?: string, password?: string) {
    let token = `Basic ` + btoa(`${username}:${password}`);
    return token;
  }


  logout() {
    console.log("Logout")
    
    this.password = undefined;
    this.username = undefined;
    localStorage.setItem('authToken', "");
    localStorage.setItem('role', "");
      localStorage.setItem('name', "");
      localStorage.setItem('tel', "");
      localStorage.setItem('mail', "");
    
    this.isLoggedSubject.next(false);
    this.router.navigateByUrl("/login").then(console.log).catch(console.error)
  }
}
