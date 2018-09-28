import { Component, OnInit } from '@angular/core';
import { BaseComponent } from '../../../utils/base-component';
import { RestDataSourceService } from '../../../services/rest/rest-data-source.service';
import { Router } from '@angular/router';
import { UserService } from '../../../services/security/user.service';



@Component({
  selector: 'reg-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: [ './add-user.component.css' ],
})
export class AddUserComponent extends BaseComponent implements OnInit {

  login;

  password;

  repeatPassword;

  name;

  email;


  constructor(
    private restService: RestDataSourceService,
    private router: Router,
    private userService: UserService
  ) {

    super();
  }


  ngOnInit() {
  }


  onSubmit() {
    if (this.password === this.repeatPassword) {
      this.restService.post(this.restService.path.user + this.restService.path.signup, {
        login: this.login,
        password: this.password,
        name: this.name,
        email: this.email,
      }).subscribe(
        (value: any) => {
          this.userService.token = value.token;
          this.userService.name = value.name;
          this.userService.id = value.id;
          this.userService.roles = value.roles;
          this.router.navigate([ '/course' ]);
        }
      );
    } else {
      alert('Пароль и повторно введенный пароль не совпадают');
    }


  }

}
