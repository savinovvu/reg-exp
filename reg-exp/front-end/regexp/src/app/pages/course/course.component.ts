import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";



@Component({
  selector: 'reg-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss']
})
export class CourseComponent implements OnInit {

  levels = [];

  constructor(
    private restService: RestDataSourceService
  ) {
  }


  ngOnInit() {
    this.restService.get('/v1/tasks/regexplevel').subscribe(v => {
      this.levels = v;
    });

  }

}
