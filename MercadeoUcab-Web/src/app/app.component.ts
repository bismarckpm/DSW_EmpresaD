import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HostListener } from '@angular/core';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  constructor(private route: ActivatedRoute, private router: Router) {}
  title = 'MercadeoUcab-Web';

  @HostListener('window:beforeunload', ['$event'])
  public beforeunloadHandler($event) {
    localStorage.removeItem('_id');
    localStorage.removeItem('rol');
    $event.preventDefault();
    $event.returnValue = false;
  }
}
