import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";
import { ActivatedRoute } from "@angular/router";
import { Task } from 'src/app/model/interfaces';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';



;

let timeout;



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

  success: boolean = false;

  registerAnswer:boolean = false

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
    clearTimeout(timeout);
    if (this.answer.trim() === '') {
      this.success = false;
      return;
    }
    timeout = setTimeout(() => {
      this.restService.put(`/v1/tasks/regexptask/check/${this.task.id}`, this.answer)
        .subscribe(v => {
          this.conditions = v;
          this.success = v.success;
        });
    }, 300);
  }


  regsiterAnswer() {
    clearTimeout(timeout);
    if (this.answer.trim() === '') {
      this.success = false;
      return;
    }
    timeout = setTimeout(() => {
      this.restService.put(`/v1/tasks/regexptask/registerAnswer/${this.task.id}`, this.answer)
        .subscribe(v => {
          this.conditions = v;
          this.registerAnswer = v.success;
        });
    }, 300);
  }
}
