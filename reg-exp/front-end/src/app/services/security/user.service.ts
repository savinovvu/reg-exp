import { Injectable } from '@angular/core';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  token: string;

  id: string;

  name: string;

  roles: string[];


  constructor() {
  }
}
