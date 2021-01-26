//import { EstudioService } from '../../../shared/Services/estudio/estudio.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BasicInfoDialogComponent } from '../../components/dialogs/basic-info-dialog/basic-info-dialog.component';

@Component({
  selector: 'app-analista',
  templateUrl: './analista.component.html',
  styleUrls: ['./analista.component.css'],
})
export class AnalistaComponent implements OnInit {
  constructor(private route: ActivatedRoute, private router: Router) {}
  
  sections: any[] = [
  { title:'Encuestas pendientes',dir:'tasks',icon:'text_snippet'},
  { title:'Home',dir:'overview',icon:'home'},
  ];
  openNote: boolean=false;
  showFiller = false;

  @ViewChild('uInfo') private infoComponent: BasicInfoDialogComponent;
  async openInfoModal() {
    return await this.infoComponent.open();
  }

  ngOnInit(): void {
    this.onDir('overview');
    //this.router.navigate(['analista/','home']);
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
      this.router.navigate(['analista/',_route]);
    } catch (e) {
      console.log(e.message);
    }
  }
  /*
  onDir(_route: string): void {
    try {
      //console.log(_route);
      this.router.navigate([_route]);
    } catch (e) {
      console.log(e.message);
    }
  }
  */
}
