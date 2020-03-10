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


  constructor(private jwtService: JwtHelperService, private http: HttpClient) {
    localStorage.setItem('jwt_token', jwtLoader());
    localStorage.setItem('id', getDataByKey('id'));
  }


  getUserName(): string {
    return this.login;
  }


  changeUser(jwt_token: string, id: string): void {
    localStorage.setItem('jwt_token', jwtLoader());
    localStorage.setItem('id', getDataByKey('id'));
  }


  getJwtToken(): string {
    return localStorage.getItem('jwt_token');
  }

  getId(): string {
    return localStorage.getItem('id');
  }

}



export function jwtLoader(): string {
  return localStorage.getItem('jwt_token');
}


export function getDataByKey(key): string {
  return localStorage.getItem(key);
}
