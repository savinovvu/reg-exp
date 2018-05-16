import { Component, OnInit } from '@angular/core';
import { ErrorService } from "./errorService/error.service";



@Component({
  selector: 'reg-error',
  templateUrl: './error.component.html',
  styleUrls: [ './error.component.css' ]
})
export class ErrorComponent implements OnInit {


  constructor(
    public errorService: ErrorService
  ) {
  }


  ngOnInit() {
  }

}
