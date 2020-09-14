import { Component, OnInit } from '@angular/core';
import { Task } from '../../model/interfaces';
import { RestDataSourceService } from '../../services/rest/rest-data-source.service';
import { ActivatedRoute } from '@angular/router';
import { isNil } from 'lodash';



let timeout;



@Component({
  selector: 'reg-propose-task',
  templateUrl: './propose-task.component.html',
  styleUrls: ['./propose-task.component.scss']
})
export class ProposeTaskComponent implements OnInit {


  task: any;

  conditions: any = {};

  answer;

  success = false;

  findString: string;

  excludeString: string;

  requiredSubstring: string;

  excludedAnswer: string;

  isSubmitting = false;


  constructor(
    private restService: RestDataSourceService,
  ) {

  }


  ngOnInit() {
    this.task = {};
    this.conditions = {};
    this.isSubmitting = false;
    this.answer = '';
    this.success = false;
    this.conditions.matchedStrings = [];
    this.conditions.excludedStrings = [];
    this.conditions.requiredSubStrings = [];
    this.conditions.excludedAnswers = [];
    this.conditions.specialConditions = [];
    this.conditions.maxSymbols = {};
    this.conditions.minSymbols = {};
  }


  check() {
    clearTimeout(timeout);
    const trimmedAnswer = this.answer.trim();
    if (trimmedAnswer === '') {
      this.resetProgress();
      return;
    }
    timeout = setTimeout(() => {
      const conditionsAndAnswer: any = {};
      this.fillConditionAndAnswer(conditionsAndAnswer, trimmedAnswer);


      this.restService.post(`/v1/proposeTask/check`, conditionsAndAnswer)
        .subscribe((v: any) => {
          this.conditions = v;
          this.success = v.success;
        });
    }, 300);
  }


  private fillConditionAndAnswer(conditionsAndAnswer: any, trimmedAnswer: string) {
    conditionsAndAnswer.matchedStrings = this.conditions.matchedStrings.map(v => v.condition);
    conditionsAndAnswer.excludedStrings = this.conditions.excludedStrings.map(v => v.condition);
    conditionsAndAnswer.requiredSubStrings = this.conditions.requiredSubStrings.map(v => v.condition);
    conditionsAndAnswer.excludedAnswers = this.conditions.excludedAnswers.map(v => v.condition);
    conditionsAndAnswer.maxSymbols = this.conditions.maxSymbols.condition;
    conditionsAndAnswer.minSymbols = this.conditions.minSymbols.condition;
    conditionsAndAnswer.answer = trimmedAnswer;
  }


  changeMax() {
    this.conditions.maxSymbols.result = false;
    this.check();
  }


  changeMin() {
    this.conditions.minSymbols.result = false;
    this.check();
  }


  addConditions() {
    if (!isNil(this.findString)) {
      const items = { condition: this.findString, result: false };
      this.conditions.matchedStrings.push(items);
      this.findString = null;
    }

    if (!isNil(this.excludeString)) {
      const items = { condition: this.excludeString, result: false };
      this.conditions.excludedStrings.push(items);
      this.excludeString = null;
    }
    if (!isNil(this.requiredSubstring)) {
      const items = { condition: this.requiredSubstring, result: false };
      this.conditions.requiredSubStrings.push(items);
      this.requiredSubstring = null;
    }
    if (!isNil(this.excludedAnswer)) {
      const items = { condition: this.excludedAnswer, result: false };
      this.conditions.excludedAnswers.push(items);
      this.excludedAnswer = null;
    }
    this.check();
  }


  deleteCondition(index: number, array: any[]) {
    array.splice(index, 1);
  }


  registerTask() {
    clearTimeout(timeout);
    const trimmedAnswer = this.answer.trim();
    if (trimmedAnswer === '') {
      this.resetProgress();
      return;
    }
    this.isSubmitting = true;

    timeout = setTimeout(() => {
      this.fillConditionAndAnswer(this.task, trimmedAnswer);

      this.restService.post(`/v1/proposeTask/propose`, this.task)
        .subscribe((v: any) => {
          this.ngOnInit();
        }, error => {
          this.isSubmitting = false;
        });
    }, 300);
  }


  private resetProgress() {
    this.success = false;
    this.conditions.matchedStrings.forEach(v => v.result = false);
    this.conditions.excludedStrings.forEach(v => v.result = false);
    this.conditions.requiredSubStrings.forEach(v => v.result = false);
    this.conditions.excludedAnswers.forEach(v => v.result = false);
    this.conditions.maxSymbols.result = false;
    this.conditions.minSymbols.result = false;
  }
}
