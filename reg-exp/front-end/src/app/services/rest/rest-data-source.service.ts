import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, Subject } from "rxjs/index";
import { Router } from "@angular/router";
import { ErrorService } from "../../components/common/error/errorService/error.service";
import { UserService } from "../security/user.service";



const PROTOCOL = 'http';
const PORT = 8080;



@Injectable()
export class RestDataSourceService {

  private baseUrl;


  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private errorService: ErrorService,
    private userService: UserService
  ) {
    this.baseUrl = `${PROTOCOL}://${location.hostname}:${PORT}/`;
  }


  get(url): Observable<any> {
    url = this.baseUrl + url;
    let subject = new Subject();
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
    url = this.baseUrl + url;
    let subject = new Subject();
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
    url = this.baseUrl + url;
    let subject = new Subject();
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
    url = this.baseUrl + url;
    let subject = new Subject();
    const headers = this.getHeaders();
    this.httpClient.delete(url, { headers }).subscribe(v => {
        subject.next(v);
      },
      error => {
        this.handleError(error);
      });
    return subject.asObservable();
  }


  private getHeaders(): HttpHeaders {
    let headers2 = new HttpHeaders({
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
