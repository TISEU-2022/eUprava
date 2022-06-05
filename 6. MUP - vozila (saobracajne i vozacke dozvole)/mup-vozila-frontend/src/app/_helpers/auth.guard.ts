import { Injectable } from '@angular/core';
import { CanActivate, CanActivateChild, CanDeactivate, CanLoad, Route, UrlSegment, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router, ActivatedRoute, RoutesRecognized, Data } from '@angular/router';
import { catchError, EMPTY, map, Observable } from 'rxjs';
import { AuthService } from '../_services/auth-service';
import { TokenService } from '../_services/token.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService,
    private tokenService: TokenService,
    private router: Router) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let url: string = state.url;
    if (this.authService.isLoggedIn()) {
      return this.tokenService.checkTokenValidity().pipe(
        map(tokenValid => {
          if (tokenValid) {
            console.log("success " + tokenValid);
            return this.checkUserRolesForPath(route, url);
          } else {
            // this prob won't ever happen
            console.log("shouldn't happen" + tokenValid);
            return false;
          }
        }),
        // if token is invalid logout user and
        // redirect him to ePortal login page
        catchError((err, caught) => {
          this.authService.logout();
          this.router.navigate(['/login']);
          return EMPTY;
        })
      )
    }
    else {
      console.log("Didn't have token, wasn't logged in.")
      this.router.navigate(['/login']);
      return false;
    }
  }


  checkUserRolesForPath(route: ActivatedRouteSnapshot, url: any): boolean {
    
    const userRole = this.authService.getRole();
    let roles: Array<any> = [];
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

}
