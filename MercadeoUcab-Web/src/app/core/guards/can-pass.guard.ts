import { Injectable } from '@angular/core';
import { CanLoad, Route, UrlSegment, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CanPassGuard implements CanLoad {
  constructor(private router: Router) {}

  canLoad(
    route: Route,
    segments: UrlSegment[]
  ): Observable<boolean> | Promise<boolean> | boolean {
    let url: string = route.path;
    console.log('Url:' + url);
    let rol = localStorage.getItem('rol');
    if (rol === url) {
      return true;
    } else {
      this.router.navigate(['login']);
      return false;
    }
  }
}
