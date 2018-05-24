import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";



@Component({
  selector: 'reg-login',
  templateUrl: './login.component.html',
  styleUrls: [ './login.component.css' ],
})
export class LoginComponent implements OnInit {

  login;

  password;


  constructor(
    private restService: RestDataSourceService
  ) {
  }


  ngOnInit() {
  }

  onSubmit(){
    this.restService.post('users/user/signin', { login: this.login, password: this.password });
    // this.restService.post('users/user/signin', 45);
  }
}
