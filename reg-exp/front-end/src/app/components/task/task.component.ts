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
  taskNumber;
  regExp: string;
  result: CheckResult;
  levelNumber: string;
  nextLevel: number;

  constructor(
    private  restService: RestDataSourceService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private levelService: LevelService
  ) {
    super();
    this.activatedRoute.params.subscribe(params => {
      this.taskNumber = params[ 'taskNumber' ];
      this.levelNumber = params[ 'levelNumber' ];
      console.log(this.taskNumber);
      console.log(this.levelNumber);
      this.nextLevel = 1 + Number(this.taskNumber);
      this.subscribtion = restService.get(`${PATH}/byNumber/${this.levelNumber}/${this.taskNumber}`).subscribe(v => {
        console.log(v);
        this.task = v;
      });
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    this.restService.put(`${PATH}check/${this.task.id}`, this.regExp)
      .subscribe((v: CheckResult) => this.result = v);
  }

  getCommaString(strings: string[]) {
    return strings.join(', ');
  }


  nextTask() {
    this.router.navigate([ 'course', this.levelNumber, this.nextLevel ]);
  }


}

interface CheckResult {
  success: boolean;
  wrongMap: any;
}


