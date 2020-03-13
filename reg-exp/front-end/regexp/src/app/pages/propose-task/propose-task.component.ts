import { Component, OnInit } from '@angular/core';
import { Task } from '../../model/interfaces';

@Component({
  selector: 'reg-propose-task',
  templateUrl: './propose-task.component.html',
  styleUrls: ['./propose-task.component.scss']
})
export class ProposeTaskComponent implements OnInit {

  task:Task;
  conditions: any;

  constructor() { }

  ngOnInit() {
  }

}
