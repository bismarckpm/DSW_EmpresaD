import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BasicInfoDialogComponent } from '../../components/dialogs/basic-info-dialog/basic-info-dialog.component';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  constructor(private route: ActivatedRoute, private router: Router) {}
  //CONTROL DE MUESTRA DE NOTIFICACIONES
  sections: any[] = [
  { title:'Usuarios',dir:'usuarios',icon:'supervisor_account'},
  { title:'Estudios',dir:'estudios',icon:'work'},
  { title:'Home',dir:'home',icon:'home'},
  { title:'Locaciones',dir:'lugares',icon:'location_on'},
  { title:'Preguntas',dir:'preguntas',icon:'help_center'},
  { title:'Categorias',dir:'categorias',icon:'turned_in'},
  { title:'SubCategorias',dir:'subcategorias',icon:'class'},
  { title:'Tipos',dir:'tipos',icon:'list'},
  { title:'Presentaciones',dir:'presentaciones',icon:'category'},
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
    this.router.navigate(['administrador/','home']);
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
      this.router.navigate(['administrador/',_route]);
    } catch (e) {
      console.log(e.message);
    }
  }
}
