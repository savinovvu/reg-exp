import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { BaseComponent } from "../../utils/base-component";
import { LevelService } from "../../services/level/level.service";

const PATH = "/tasks/regexptask/";

@Component({
  selector: 'reg-task',
  templateUrl: './task.component.html',
  styleUrls: [ './task.component.css' ]
})
export class TaskComponent extends BaseComponent implements OnInit {

  task;
  taskId;
  regExp: string;
  result: CheckResult;
  level: string;
  nextLevel: number;

  constructor(
    private  restService: RestDataSourceService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private levelService:LevelService
  ) {
    super();
    this.activatedRoute.params.subscribe(params => {
      this.taskId = params[ 'task' ];
      this.level = params[ 'level' ];
      this.nextLevel = 1 + Number(this.taskId);
      this.subscribtion = restService.get(`${PATH}${this.taskId}`).subscribe(v => {
        this.task = v;
      });
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    this.restService.put(`${PATH}check/${this.taskId}`, this.regExp)
      .subscribe((v: CheckResult) => this.result = v);
  }

  getCommaString(strings: string[]) {
    return strings.join(', ');
  }


  nextTask() {
    this.router.navigate([ 'course', this.level, this.nextLevel ]);
  }


}

interface CheckResult {
  success: boolean;
  wrongMap: any;
}


