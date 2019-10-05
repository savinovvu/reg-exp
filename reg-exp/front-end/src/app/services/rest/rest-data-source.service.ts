import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs/index';
import { Router } from '@angular/router';
import { ErrorService } from '../../components/common/error/errorService/error.service';
import { UserService } from '../security/user.service';
import { TranslateService } from '@ngx-translate/core';



@Injectable()
export class RestDataSourceService {

  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private errorService: ErrorService,
    private userService: UserService,
    private translate: TranslateService
  ) {
  }


  get(url): Observable<any> {
    url = this.getUrl(url);
    const subject = new Subject();
    this.httpClient.get(url).subscribe(v => {
        subject.next(v);
      },
      error => {
        this.handleError(error);
      });
    return subject.asObservable();
  }


  post(url, item) {
    url = this.getUrl(url);
    const subject = new Subject();
    this.httpClient.post(url, item).subscribe(v => {
        subject.next(v);
      },
      error => {
        // this.handleError(error);
      });
    return subject.asObservable();
  }


  put(url, item) {
    url = this.getUrl(url);
    const subject = new Subject();
    this.httpClient.put(url, item).subscribe(v => {
        subject.next(v);
      },
      error => {
        this.handleError(error);
      });
    return subject.asObservable();
  }


  delete(url, item) {
    url = this.getUrl(url);
    const subject = new Subject();
    this.httpClient.delete(url).subscribe(v => {
        subject.next(v);
      },
      error => {
        this.handleError(error);
      });
    return subject.asObservable();
  }


  private getUrl(url) {
    const currentLang = this.translate.currentLang;
    return url + `?lang=${currentLang}`;
  }


  private handleError(error) {
    this.errorService.header = error.message;
    this.errorService.stack = error.error.text;
    this.router.navigate([ '/error' ]);
  }


}



export interface Path {
  comment: string;

  like: string;

  numbered: string;

  regexpLevel: string;

  regexpTask: string;

  signin: string;

  signup: string;

  user: string;

  parent: string;

}
