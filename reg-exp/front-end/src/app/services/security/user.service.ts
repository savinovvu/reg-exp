import { Injectable } from '@angular/core';
import { isNil } from 'lodash';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _token: string;

  private _id: string;

  private _name: string;

  private _roles: string[];


  constructor() {
    const token = localStorage.getItem('reg-token');
    this._token = isNil(token) ? null : token;

    const id = localStorage.getItem('reg-id');
    this._id = isNil(id) ? null : id;

    const name = localStorage.getItem('reg-name');
    this._name = isNil(name) ? null : name;

    const roles = JSON.parse(localStorage.getItem('reg-roles'));
    this._roles = isNil(roles) ? [] : roles;
  }


  get token(): string {
    return this._token;
  }


  set token(value: string) {
    localStorage.setItem('reg-token', JSON.stringify(value));
    this._token = value;
  }


  get id(): string {
    return this._id;
  }


  set id(value: string) {
    localStorage.setItem('reg-id', JSON.stringify(value));
    this._id = value;
  }


  get name(): string {
    return this._name;
  }


  set name(value: string) {
    localStorage.setItem('reg-name', JSON.stringify(value));
    this._name = value;
  }


  get roles(): string[] {
    return this._roles;
  }


  set roles(value: string[]) {
    localStorage.setItem('reg-roles', JSON.stringify(value));
    this._roles = value;
  }
}
