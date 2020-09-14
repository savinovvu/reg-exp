import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";



@Component({
  selector: 'reg-guest-login',
  templateUrl: './guest-login.component.html',
  styleUrls: ['./guest-login.component.scss']
})
export class GuestLoginComponent implements OnInit {

  constructor(
    private restService: RestDataSourceService
  ) {
  }


  ngOnInit() {
  }


  loginAsGuest() {
    this.restService.post("/v1/sign-in/guest", null).subscribe((v: any) => {
      console.log(v);
      localStorage.setItem('jwt_token', v.token);
      localStorage.setItem('id', v.id);
    })
  }
}
