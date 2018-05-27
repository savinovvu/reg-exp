import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from "../../../services/rest/rest-data-source.service";
import { Router } from "@angular/router";
import { UserService } from "../../../services/security/user.service";



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
    private router: Router,
    private userService: UserService
  ) {
  }


  ngOnInit() {
  }


  onSubmit() {
    this.restService.post('users/user/signin', { login: this.login, password: this.password }).subscribe(
      (value: any) => {
        this.userService.token = value.token;
        this.userService.name = value.name;
        this.userService.id = value.id;
        this.userService.roles = value.roles;
        this.router.navigate([ '/course' ]);
      }
    );
  }
}
