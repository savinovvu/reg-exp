import { Component, OnInit } from '@angular/core';
import { LocalDataSource, ServerDataSource } from 'ng2-smart-table';
import { RestDataSourceService } from '../../../services/rest/rest-data-source.service';
import { ActivatedRoute, Router, UrlSerializer, UrlTree } from "@angular/router";
import 'rxjs/add/operator/debounceTime';
import { debounce } from "rxjs/operators";
import { interval, timer } from "rxjs";
import { HttpClient } from "@angular/common/http";



const BASE_URL = '/v1/users/user/filter';



@Component({
  selector: 'ngx-smart-table',
  templateUrl: './smart-table.component.html',
  styleUrls: ['./smart-table.component.scss'],
})
export class SmartTableComponent implements OnInit {

  private source: ServerDataSource;


  constructor(
    private httpClient: HttpClient,
  ) {
    this.source = new ServerDataSource(httpClient,
      {
        dataKey:'items',
        endPoint:'/v1/users/user/filter',
        pagerLimitKey: 'size',
        pagerPageKey: 'page',
        totalKey: 'total',
        sortFieldKey: 'sort',
        sortDirKey:'direction',
        filterFieldKey:'#field#'
      })
  }




  ngOnInit() {
  }


  onDeleteConfirm(event): void {
    if (window.confirm('Are you sure you want to delete?')) {
      event.confirm.resolve();
    } else {
      event.confirm.reject();
    }
  }


  settings = {

    actions: {
      add: false,
      edit:false,
      delete:false,
    },


    columns: {
      id: {
        title: 'ID',
        type: 'number',
        filter: {
          query: 'slfdkj',
        },
      },
      firstName: {
        title: 'First Name',
        type: 'string',
      },
      lastName: {
        title: 'Last Name',
        type: 'string',
      },
      login: {
        title: 'Login',
        type: 'string',
      },
      email: {
        title: 'E-mail',
        type: 'string',
      },
      birthDate: {
        title: 'Birth Date',
        type: 'string',
      },
      sex: {
        title: 'Sex',
        type: 'string',
        filter: {
          type: 'list',
          config: {
            selectText: 'Select...',
            list: [
              { value: 'man', title: 'MAN' },
              { value: 'woman', title: 'WOMAN' },
            ],
          },
        },
      },
    },
  };

}



