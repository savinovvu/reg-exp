import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { CheckedResult } from "../../../model/interfaces";
import { RestDataSourceService } from "../../../services/rest/rest-data-source.service";
import { BaseComponent } from "../../../utils/base-component";



const PATH = "tasks/regexptask/";



@Component({
  selector: 'reg-task',
  templateUrl: './task.component.html',
  styleUrls: [ './task.component.css' ]
})
export class TaskComponent extends BaseComponent implements OnInit {

  task;

  taskNumber;

  regExp: string;

  resultAnswer: CheckedResult;

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
      this.subscribtion = restService.get(`${PATH}byNumber/${this.levelNumber}/${this.taskNumber}`).subscribe(taskData => {
        this.task = taskData;
        this.subscribtion = restService.get(`${PATH}parent/${this.task.regExpLevel.id}`).subscribe(tasksAtLevel => {
          this.existNextTask = this.task.number >= tasksAtLevel.length;
        });
      });
    });
  }


  ngOnInit() {
  }


  onSubmit() {
    this.restService.put(`${PATH}check/${this.task.id}`, this.regExp)
      .subscribe((v: CheckedResult) => this.resultAnswer = v);
  }


  nextTask() {
    let nextTaskNumber = this.task.number + 1;
    this.router.navigate([ 'course', this.levelNumber, nextTaskNumber ]);
  }


}




