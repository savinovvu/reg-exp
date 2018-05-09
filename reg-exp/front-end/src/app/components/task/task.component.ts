import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";
import { ActivatedRoute, Router } from "@angular/router";
import { BaseComponent } from "../../utils/base-component";



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

  private taskCount: number;

  existNextTask: boolean = false;


  constructor(
    private  restService: RestDataSourceService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
  ) {
    super();
    this.activatedRoute.params.subscribe(params => {
      this.taskNumber = params[ 'taskNumber' ];
      this.levelNumber = params[ 'levelNumber' ];
      this.subscribtion = restService.get(`${PATH}/byNumber/${this.levelNumber}/${this.taskNumber}`).subscribe(taskData => {
        this.task = taskData;
        this.subscribtion = restService.get(`/tasks/regexptask/parent/${this.task.regExpLevel.id}`).subscribe(tasksAtLevel => {
          this.existNextTask = this.task.number >= tasksAtLevel.length;
        });
      });
    });
  }


  ngOnInit() {
  }


  onSubmit() {
    this.restService.put(`${PATH}check/${this.task.id}`, this.regExp)
      .subscribe((v: CheckResult) => this.result = v);
  }


  nextTask() {
    let nextTaskNumber = this.task.number + 1;
    this.router.navigate([ 'course', this.levelNumber, nextTaskNumber ]);
  }


}



interface CheckResult {
  success: boolean;
  wrongMap: any;
}


