import {Component, OnInit} from '@angular/core';
import {RestDataSourceService} from "../../services/rest/rest-data-source.service";
import {ActivatedRoute} from "@angular/router";
import {BaseComponent} from "../../utils/base-component";

const PATH = "/tasks/regexptask/";

@Component({
  selector: 'reg-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent extends BaseComponent implements OnInit {

  task;
  taskId;
  regExp: string;

  constructor(
    private  restService: RestDataSourceService,
    private router: ActivatedRoute
  ) {
    super();
    this.taskId = router.snapshot.params['task'];
    this.subscribtion = restService.get(`${PATH}${this.taskId}`).subscribe(v => {
      console.log("777777777777");
      this.task = v;
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    this.restService.put(`${PATH}check/${this.taskId}`, this.regExp)
      .subscribe(v => console.log(JSON.stringify(v)));
  }
}
