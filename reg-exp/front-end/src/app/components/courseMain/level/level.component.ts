import { Component, OnInit } from '@angular/core';

import { ActivatedRoute } from "@angular/router";
import { BaseComponent } from "../../../utils/base-component";
import { RestDataSourceService } from "../../../services/rest/rest-data-source.service";



@Component({
  selector: 'reg-level',
  templateUrl: './level.component.html',
  styleUrls: [ './level.component.css' ]
})
export class LevelComponent extends BaseComponent implements OnInit {


  tasks;

  displayedColumns = [ 'id', 'name', 'description', 'author' ];

  level;


  constructor(
    private  restService: RestDataSourceService,
    private router: ActivatedRoute,
  ) {
    super();
    this.level = router.snapshot.params[ 'levelNumber' ];
    this.subscribtion = restService.get(`tasks/regexptask/parent/${this.level}`).subscribe(v => {
      this.tasks = v;
    });
  }


  ngOnInit() {
  }

}