import { Component, ViewEncapsulation } from '@angular/core';
import { RestDataSourceService } from '../services/rest/rest-data-source.service';



@Component({
  selector: 'reg-root',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.css' ],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent {

  constructor(private restService: RestDataSourceService) {
  }

}
