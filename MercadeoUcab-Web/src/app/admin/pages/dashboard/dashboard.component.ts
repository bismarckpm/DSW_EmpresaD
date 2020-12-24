import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {NgbPopover, NgbPopoverConfig} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  constructor(private route: ActivatedRoute, private router: Router) {}
  //CONTROL DE MUESTRA DE NOTIFICACIONES
  openNote: boolean=false;
  
  getOpenNote():boolean{
    return this.openNote;
  };

  ngOnInit(): void {
    setTimeout(()=>{
      this.openNote=true;
      setTimeout(() => {
        this.openNote=false;
      },10000)
    },2000);
  }
  onDir(_route: string): void {
    try {
      //console.log(_route);
      this.router.navigate([_route]);
    } catch (e) {
      console.log(e.message);
    }
  }
}
