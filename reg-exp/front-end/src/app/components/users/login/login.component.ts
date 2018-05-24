import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from "../../../services/rest/rest-data-source.service";
import { Router } from "@angular/router";



@Component({
  selector: 'reg-login',
  templateUrl: './login.component.html',
  styleUrls: [ './login.component.css' ],
})
export class LoginComponent implements OnInit {

  login;

  password;


  constructor(
    private restService: RestDataSourceService,
    private router: Router
  ) {
  }


  ngOnInit() {
  }


  onSubmit() {
    this.restService.post('users/user/signin', { login: this.login, password: this.password }).subscribe(
      (value: any) => {
        this.restService.token = value.token;
        this.router.navigate(['/course']);
      }
    );
  }
}
