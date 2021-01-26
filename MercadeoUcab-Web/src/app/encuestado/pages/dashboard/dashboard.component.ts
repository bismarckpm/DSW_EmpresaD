import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BasicInfoDialogComponent } from '../../components/basic-info-dialog/basic-info-dialog.component';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  constructor(private route: ActivatedRoute, private router: Router) {}

  sections: any[] = [
  { title:'Encuestas pendientes',dir:'pending',icon:'assignment_late'},
  { title:'Home',dir:'home',icon:'home'},
  ];
  openNote: boolean=false;
  showFiller = false;

  @ViewChild('uInfo') private infoComponent: BasicInfoDialogComponent;
  async openInfoModal() {
    return await this.infoComponent.open();
  }

  getOpenNote():boolean{
    return this.openNote;
  };
  
  ngOnInit(): void {
    this.onDir('home');
    //this.router.navigate(['encuestado/','home']);
    setTimeout(()=>{
      this.openNote=true;
      setTimeout(() => {
        this.openNote=false;
      },10000)
    },2000);
  }
  /*
  onDir(_route: string): void {
    try {
      //console.log(_route);
      this.router.navigate([_route]);
    } catch (e) {
      console.log(e.message);
    }
  }*/
  onDir(_route: string): void {
    try {
      //console.log(_route);
      this.router.navigate(['encuestado/',_route]);
    } catch (e) {
      console.log(e.message);
    }
  }
}
