import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { RestDataSourceService } from "../rest/rest-data-source.service";
import { isNil } from "lodash";



@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private router:Router,
    private restService: RestDataSourceService
  ) {
  }


  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (isNil(this.restService.token)) {
      this.router.navigateByUrl("/login");
      return false;
    }
    return true;
  }
}
