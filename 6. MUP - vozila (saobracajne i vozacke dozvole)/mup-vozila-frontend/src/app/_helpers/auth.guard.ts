import { Injectable } from '@angular/core';
import { CanActivate, CanActivateChild, CanDeactivate, CanLoad, Route, UrlSegment, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router, ActivatedRoute, RoutesRecognized, Data } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../_services/auth-service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let url: string = state.url;
    return this.checkUserLogin(route, url);
  }


  checkUserLogin(route: ActivatedRouteSnapshot, url: any): boolean {
    if (this.authService.isLoggedIn()) {
      const userRole = this.authService.getRole();
      let roles : Array<any> = [];
      Object.entries(route.data).forEach(item => {
        roles.push(item[1]);
      });

      // returns list of roles
      let rolesFixed: string = roles[0] as string;

      console.log("Authorized roles for this route: " + rolesFixed);
      console.log("User role: " + userRole);

      // if user doesn't have any role but is trying to
      // access protected route he's being redirected to
      // login page
      if (rolesFixed[0] && userRole == null) {
        this.router.navigate(['/login']);
        return false;
      }

      // check if route is protected by roles and if it is
      // does user's role exist in list of authorized roles
      if (rolesFixed[0] && !rolesFixed.includes(userRole)) {
        this.router.navigate(['/']);
        return false;
      }

      // route is unprotected or user posses authorized role
      // for this route
      return true;
    }

    this.router.navigate(['/login']);
    return false;
  }
  
}
