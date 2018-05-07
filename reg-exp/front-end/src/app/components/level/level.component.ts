import {Component, OnInit} from '@angular/core';
import {RestDataSourceService} from "../../services/rest/rest-data-source.service";
import {BaseComponent} from "../../utils/base-component";
import {ActivatedRoute} from "@angular/router";
import { LevelService } from "../../services/level/level.service";

@Component({
  selector: 'reg-level',
  templateUrl: './level.component.html',
  styleUrls: ['./level.component.css']
})
export class LevelComponent extends BaseComponent implements OnInit {


  tasks;
  displayedColumns = ['id', 'name', 'description', 'author'];
  level;

  constructor(
    private  restService: RestDataSourceService,
    private router: ActivatedRoute,
    private levelService: LevelService
  ) {
    super();
   this.level = router.snapshot.params['level'];
    this.subscribtion = restService.get(`/tasks/regexptask/parent/${this.level}`).subscribe(v => {
      this.tasks = v;
      this.levelService.tasksCount = this.tasks.length;
    });
  }


  ngOnInit() {
  }

}
