import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'reg-level',
  templateUrl: './level.component.html',
  styleUrls: ['./level.component.scss']
})
export class LevelComponent implements OnInit {

  tasks = [];

  level;

  constructor(
    private restService: RestDataSourceService,
    private activatedRoute: ActivatedRoute
  ) { }


  ngOnInit() {
    this.level = this.activatedRoute.snapshot.params.levelNumber;
    this.restService.get(`/v1/tasks/regexptask/byLevel/${this.level}`).subscribe(v => {
      this.tasks = v;
    });
  }

}
