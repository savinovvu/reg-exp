import { Component, OnInit } from '@angular/core';
import { BaseComponent } from "../../../utils/base-component";

@Component({
  selector: 'reg-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent extends BaseComponent implements OnInit {

  constructor() {
    super()
  }

  ngOnInit() {
  }

}
