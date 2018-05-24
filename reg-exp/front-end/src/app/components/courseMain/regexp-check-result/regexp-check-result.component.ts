import { Component, Input, OnInit } from '@angular/core';
import { CheckedResult } from "../../../model/interfaces";



@Component({
  selector: 'reg-regexp-check-result',
  templateUrl: './regexp-check-result.component.html',
  styleUrls: [ './regexp-check-result.component.css' ]
})
export class RegexpCheckResultComponent implements OnInit {

  @Input()
  resultAnswer: CheckedResult;


  constructor() {
  }


  ngOnInit() {
  }

}
