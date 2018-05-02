import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";

const PROTOCOL = 'http';
const PORT = 8080;

@Injectable()
export class RestDataSourceService {

  private auth_token: string = 'tmp';
  private baseUrl;

  constructor(
    private httpClient: HttpClient
  ) {
    this.baseUrl = `${PROTOCOL}://${location.hostname}:${PORT}/`;
  }

  get(url): Observable<any> {
    url = this.baseUrl + url;
    let subject = new Subject();
    const headers = this.getHeaders();
    this.httpClient.get(url, {headers}).subscribe(v => {
      subject.next(v);
    });
    return subject.asObservable();
  }

  post(url, item) {
    url = this.baseUrl + url;
    let subject = new Subject();
    const headers = this.getHeaders();
    this.httpClient.post(url, item, {headers}).subscribe(v => {
      subject.next(v);
    });
    return subject.asObservable();
  }

  put(url, item) {
    url = this.baseUrl + url;
    let subject = new Subject();
    const headers = this.getHeaders();
    this.httpClient.put(url, item, {headers}).subscribe(v => {
      subject.next(v);
    });
    return subject.asObservable();
  }

  delete(url, item) {
    url = this.baseUrl + url;
    let subject = new Subject();
    const headers = this.getHeaders();
    this.httpClient.delete(url, {headers}).subscribe(v => {
      subject.next(v);
    });
    return subject.asObservable();
  }


  private getHeaders(): HttpHeaders {
    const headers = new HttpHeaders()
    .set('Authorization', `Bearer<${this.auth_token}>`)
    return headers;
  }

}
