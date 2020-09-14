import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MENU_ITEMS } from '../pages/pages-menu';



@Component({
  selector: 'reg-root',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.scss' ],
})
export class AppComponent  implements OnInit {

  constructor() {
  }

  menu = MENU_ITEMS;

  ngOnInit(): void {
  }

}
