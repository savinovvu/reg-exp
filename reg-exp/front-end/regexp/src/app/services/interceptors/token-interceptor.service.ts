import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";
import { tap } from "rxjs/operators";
import { AuthenticationService } from "../jwt/authentication.service";

@Injectable({
  providedIn: 'root'
})
@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private injector: Injector,
              private authenticationService:AuthenticationService) {}



  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.authenticationService.getJwtToken()}`,
          'id': this.authenticationService.getId()
      }
    });
    return next.handle(request).pipe(
      tap(
        event => {},
        error => {}
      )
    );
  }
}
