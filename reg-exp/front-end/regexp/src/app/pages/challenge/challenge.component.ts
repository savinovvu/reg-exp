import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";
import { ActivatedRoute } from "@angular/router";
import { Task } from 'src/app/model/interfaces';



@Component({
  selector: 'reg-challenge',
  templateUrl: './challenge.component.html',
  styleUrls: ['./challenge.component.scss']
})
export class ChallengeComponent implements OnInit {

  task: Task;

  taskNumber;

  levelNumber;

  conditions = {};

  answer = '';


  constructor(
    private restService: RestDataSourceService,
    private activatedRoute: ActivatedRoute
  ) {
  }


  ngOnInit() {
    this.levelNumber = this.activatedRoute.snapshot.params.levelNumber;
    this.taskNumber = this.activatedRoute.snapshot.params.taskNumber;
    this.restService.get(`/v1/tasks/regexptask/byLevel/${this.levelNumber}/byNumber/${this.taskNumber}`).subscribe(v => {
      this.task = v;

      this.restService.put(`/v1/tasks/regexptask/check/${this.task.id}`, ' ').subscribe(v => {
        this.conditions = v;
      });
    });
  }


  answerChange() {
    if (this.answer.trim() === '') {
      return;
    }
    this.restService.put(`/v1/tasks/regexptask/check/${this.task.id}`, this.answer).subscribe(v => {
      this.conditions = v;
    });
  }

}
