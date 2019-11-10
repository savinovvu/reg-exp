import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { RestDataSourceService } from '../services/rest/rest-data-source.service';
import { AnalyticsService } from '../@core/utils';
import { MENU_ITEMS } from '../pages/pages-menu';



@Component({
  selector: 'reg-root',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.scss' ],
})
export class AppComponent  implements OnInit {

  constructor(private analytics: AnalyticsService) {
  }

  menu = MENU_ITEMS;

  ngOnInit(): void {
    this.analytics.trackPageViews();
  }

}
