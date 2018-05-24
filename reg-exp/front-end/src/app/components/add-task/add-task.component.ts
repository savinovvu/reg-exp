import { Component, OnInit } from '@angular/core';
import { Answer, CheckedResult, Task } from "../../model/interfaces";
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";



@Component({
  selector: 'reg-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: [ './add-task.component.css' ]
})
export class AddTaskComponent implements OnInit {

  name: string;

  description: string;

  answer: string;

  matchStrings: string[] = [];

  excludedStrings: string[] = [];

  requiredSubStrings: string[] = [];

  excludedAnswers: string[] = [];

  matchString: string = '';

  excludedString: string = '';

  requiredSubString: string = '';

  excludedAnswer: string = '';

  resultAnswer: CheckedResult;


  constructor(
    private  restService: RestDataSourceService
  ) {
  }


  ngOnInit() {
  }


  onSubmit() {
    const addedTask: Task = {};
    addedTask.name = this.name;
    addedTask.description = this.description;
    addedTask.matchStrings = this.matchStrings;
    addedTask.excludedString = this.excludedStrings;
    addedTask.requiredSubStrings = this.requiredSubStrings;
    addedTask.excludedAnswers = this.excludedAnswers;
    let addedAnswer: Answer = {
      answer: this.answer,
    };
    addedTask.answers = [ addedAnswer ];

    this.restService.post('tasks/regexptask', addedTask).subscribe((v: CheckedResult) =>
      this.resultAnswer = v
    )
  }


  addExcludedAnswer() {
    if (this.excludedAnswers.indexOf(this.excludedAnswer) !== -1
      || this.excludedAnswer === '') {
      return;
    }
    this.excludedAnswers.push(this.excludedAnswer);
    this.excludedAnswer = '';
  }


  addRequiredSubString() {
    if (this.requiredSubStrings.indexOf(this.requiredSubString) !== -1
      || this.requiredSubString === '') {
      return;
    }
    this.requiredSubStrings.push(this.requiredSubString);
    this.requiredSubString = '';
  }


  addExcludedString() {
    if (this.matchStrings.indexOf(this.excludedString) !== -1
      || this.excludedStrings.indexOf(this.excludedString) !== -1
      || this.excludedString === '') {
      return;
    }

    this.excludedStrings.push(this.excludedString);
    this.excludedString = '';
  }


  addMatchString() {
    if (this.excludedStrings.indexOf(this.matchString) !== -1
      || this.matchStrings.indexOf(this.matchString) !== -1
      || this.matchString === '') {
      return;
    }

    this.matchStrings.push(this.matchString);
    this.matchString = '';
  }


  deleteCondition(item: string, conditionArray: string[]) {
    const idx = conditionArray.indexOf(item);
    if (idx != -1) {
      conditionArray.splice(idx, 1);
    }
  }
}


