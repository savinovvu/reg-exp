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

  private BASE_API_URL = 'http://localhost:8040/';


  private userName: string;


  constructor(private jwtService: JwtHelperService, private http: HttpClient) {
    const jwtToken = jwtLoader();
    if (jwtToken) {
      this.changeUser(jwtToken, false);
    } else {
      this.login({ userName: 'test', hashedPassword: SHA256('test').toString() });
    }
  }


  login(loginDTO: LoginDTO): void {
    this.http.post<any>(`${this.BASE_API_URL}api/login`, loginDTO, { observe: 'response' })
      .pipe(map(response => response.headers.get('Authorization'))
        , map(header => header.replace('Bearer ', '')))
      .subscribe(token => this.changeUser(token, true));
  }


  getUserName(): string {
    return this.userName;
  }


  changeUser(jwtToken: string, persist: boolean): void {
    const tokenJson: object = this.jwtService.decodeToken(jwtToken);
    this.userName = tokenJson[ 'sub' ];
    if (persist) {
      localStorage.setItem('jwt_token', jwtToken);
    }
  }
}



export function jwtLoader(): string {
  return localStorage.getItem('jwt_token');
}
