import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";
import { ActivatedRoute } from "@angular/router";
import { BaseComponent } from "../../utils/base-component";

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
    private router: ActivatedRoute
  ) {
    super();
    this.taskId = router.snapshot.params[ 'task' ];
    this.level = router.snapshot.params[ 'level' ];
    this.nextLevel = 1 + Number(this.level);

    this.subscribtion = restService.get(`${PATH}${this.taskId}`).subscribe(v => {
      this.task = v;
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    this.restService.put(`${PATH}check/${this.taskId}`, this.regExp)
      .subscribe((v:CheckResult) => this.result = v);
  }

  getCommaString(strings: string[]) {
    return strings.join(', ');
  }
}

interface CheckResult {
  success: boolean;
  wrongMap: any;
}


