import { Component, OnInit } from '@angular/core';
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";
import { BaseComponent } from "../../utils/base-component";

@Component({
  selector: 'reg-course',
  templateUrl: './course.component.html',
  styleUrls: [ './course.component.css' ]
})
export class CourseComponent extends BaseComponent implements OnInit {

  levels;
  displayedColumns = [ 'id', 'description' ];

  constructor(
    private  restService: RestDataSourceService
  ) {
    super();
    this.subscribtion = restService.get("tasks/regexplevel").subscribe(v => {
      this.levels = v;
    })
  }


  ngOnInit() {
  }

}





