import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient } from '@angular/common/http';
import { SHA256 } from 'crypto-js';
import { LoginDTO } from '../../model/login-dto';
import { map } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private login: string;

  private jwtToken: string;

  private id: string;


  constructor(private jwtService: JwtHelperService, private http: HttpClient) {
    this.jwtToken = jwtLoader();
    this.id = getDataByKey('id');
    if (this.jwtToken) {
      this.changeUser(this.jwtToken, false);
    }
  }


  getUserName(): string {
    return this.login;
  }


  changeUser(jwtToken: string, persist: boolean): void {
    const tokenJson: object = this.jwtService.decodeToken(jwtToken);
    this.login = tokenJson[ 'sub' ];
    if (persist) {
      localStorage.setItem('jwt_token', jwtToken);
    }
  }


  getJwtToken(): string {
    return this.jwtToken;
  }


  getLogin(): string {
    return this.login;
  }


  getId(): string {
    return this.id;
  }

}



export function jwtLoader(): string {
  return localStorage.getItem('jwt_token');
}


export function getDataByKey(key): string {
  return localStorage.getItem(key);
}
