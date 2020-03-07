import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'reg-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent implements OnInit {

  tabs: any[] = [
    {
      title: 'User',
      route: '/settings/user',
    },
    {
      title: 'Rating',
      route: '/settings/rating',
    },
  ];

  constructor() { }

  ngOnInit() {
  }

}
