import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs/index';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { AuthenticationService } from '../jwt/authentication.service';



@Injectable()
export class RestDataSourceService {

  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private translate: TranslateService,
    private authenticationService: AuthenticationService
  ) {
  }


  get(url): Observable<any> {
    url = this.getUrl(url);
    const headers = this.getHeaders();
    const subject = new Subject();
    this.httpClient.get(url, {headers}).subscribe(v => {
        subject.next(v);
      },
      error => {
        this.handleError(error);
      });
    return subject.asObservable();
  }


  post(url, item) {
    url = this.getUrl(url);
    const headers = this.getHeaders();
    const subject = new Subject();
    this.httpClient.post(url, item, {headers}).subscribe(v => {
        subject.next(v);
      },
      error => {
        // this.handleError(error);
      });
    return subject.asObservable();
  }


  put(url, item) {
    url = this.getUrl(url);
    const headers = this.getHeaders();
    const subject = new Subject();
    this.httpClient.put(url, item, {headers}).subscribe(v => {
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
    this.httpClient.delete(url, {headers}).subscribe(v => {
        subject.next(v);
      },
      error => {
        this.handleError(error);
      });
    return subject.asObservable();
  }


  private getUrl(url) {
    return url;
    // const currentLang = this.translate.currentLang;
    // return url + `?lang=${currentLang}`;
  }


  private handleError(error) {
    this.router.navigate([ '/error' ]);
  }


  private getHeaders(): HttpHeaders {
    const headers = new HttpHeaders()
    ;
    return headers;
  }


}




