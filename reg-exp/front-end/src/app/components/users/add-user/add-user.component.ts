import { Component, OnInit } from '@angular/core';
import { BaseComponent } from "../../../utils/base-component";
import { RestDataSourceService } from "../../../services/rest/rest-data-source.service";



@Component({
  selector: 'reg-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: [ './add-user.component.css' ]
})
export class AddUserComponent extends BaseComponent implements OnInit {

  login;

  password;

  repeatPassword;

  name;

  email;


  constructor(
    private restService: RestDataSourceService
  ) {

    super()
  }


  ngOnInit() {
  }


  onSubmit() {
    if (this.password === this.repeatPassword) {
      this.restService.post('users/user/signup', {
        login: this.login,
        password: this.password,
        name: this.name,
        email: this.email,
      }).subscribe(
        (value: any) => {
          this.restService.token = value.token;
        }
      );
    } else {
      alert("Пароль и повторно введенный пароль не совпадают");
    }


  }

}
