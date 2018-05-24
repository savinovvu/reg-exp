import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  token:string = "noneToken";

  constructor() { }
}
