import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs/index';
import { Router } from '@angular/router';
import { ErrorService } from '../../components/common/error/errorService/error.service';
import { UserService } from '../security/user.service';
import { TranslateService } from '@ngx-translate/core';



const PROTOCOL = 'http';
const PORT = 8080;



@Injectable()
export class RestDataSourceService {

  private baseUrl;


  public path: Path;

  private rootPath = '/back-end-paths';


  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private errorService: ErrorService,
    private userService: UserService,
    private translate: TranslateService
  ) {
    this.baseUrl = `${PROTOCOL}://${location.hostname}:${PORT}`;
    this.get(this.rootPath).subscribe(v => {
      this.path = v;
    });
  }


  get(url): Observable<any> {
    url = this.getUrl(url);
    const subject = new Subject();
    const headers = this.getHeaders();
    this.httpClient.get(url, { headers }).subscribe(v => {
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
    const headers = this.getHeaders();
    this.httpClient.post(url, item, { headers }).subscribe(v => {
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
    const headers = this.getHeaders();
    this.httpClient.put(url, item, { headers }).subscribe(v => {
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
    const headers = this.getHeaders();
    this.httpClient.delete(url, { headers }).subscribe(v => {
        subject.next(v);
      },
      error => {
        this.handleError(error);
      });
    return subject.asObservable();
  }


  private getUrl(url) {
    const currentLang = this.translate.currentLang;
    return this.baseUrl + url + `?lang=${currentLang}`;
  }


  private getHeaders(): HttpHeaders {
    const headers2 = new HttpHeaders({
      'authorization': `Bearer ${this.userService.token}`,
      'id': `${this.userService.id}`,
    });

    return headers2;
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
