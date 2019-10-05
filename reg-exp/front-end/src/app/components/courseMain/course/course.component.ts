import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from '../../../services/rest/rest-data-source.service';
import { BaseComponent } from '../../../utils/base-component';



@Component({
  selector: 'reg-course',
  templateUrl: './course.component.html',
  styleUrls: [ './course.component.css' ]
})
export class CourseComponent extends BaseComponent implements OnInit {

  levels;

  constructor(
    private  restService: RestDataSourceService
  ) {
    super();
    //
    this.subscribtion = restService.get('/v1/tasks/regexplevel').subscribe(v => {
      this.levels = v;
    });
  }


  ngOnInit() {
  }


  getSolveText(solve: any) {
    if (solve) {
      return 'решено';
    } else {
      return 'не решено';
    }
  }
}






