import { Component, OnInit } from '@angular/core';
import { ServerDataSource } from 'ng2-smart-table';
import 'rxjs/add/operator/debounceTime';
import { HttpClient } from "@angular/common/http";
import { RestDataSourceService } from "../../services/rest/rest-data-source.service";



@Component({
  selector: 'reg-user-rating',
  templateUrl: './user-rating.component.html',
  styleUrls: ['./user-rating.component.scss'],
})
export class UserRatingComponent implements OnInit {

  source: ServerDataSource;


  constructor(
    private httpClient: HttpClient,
    private restService: RestDataSourceService,
  ) {
    this.source = new ServerDataSource(httpClient,
      {
        dataKey: 'items',
        endPoint: '/v1/users/user/filter',
        pagerLimitKey: 'size',
        pagerPageKey: 'page',
        totalKey: 'total',
        sortFieldKey: 'sort',
        sortDirKey: 'direction',
        filterFieldKey: '#field#'
      })
  }


  ngOnInit() {
  }


  onDeleteConfirm(event): void {
    if (window.confirm(`Are you sure you want to delete ${event.data.firstName} ${event.data.firstName}?`)) {
      this.onDelete(event);
      event.confirm.resolve();
    } else {
      event.confirm.reject();
    }
  }


  onDelete(event: any) {
    this.restService.delete(`/v1/users/user/${event.data.id}`).subscribe(v => {
      this.source.refresh();
    });
  }


  onEdit(event: any) {
    this.restService.put(`/v1/users/user`, event.newData).subscribe(value => {
      this.source.refresh();
    });
  }


  onEditConfirm($event: any) {
    if (window.confirm(`Are you sure you want to edit ${$event.data.firstName} ${$event.data.firstName}?`)) {
      this.onEdit($event);
      $event.confirm.resolve();
    } else {
      $event.confirm.reject();
    }
  }


  settings = {
    mode: 'inline',
    selectMode: 'single',
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
      confirmSave: true,
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },

    actions: {
      add: false,
      edit:false,
      delete:false
    },


    columns: {
      score:{
        title: 'Score',
        type: 'number',
        editable: false,
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
        editor:{
          type:'list',
          config: {
            selectText: 'Select...',
            list: [
              { value: 'man', title: 'MAN' },
              { value: 'woman', title: 'WOMAN' },
            ],
          },
        },
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



