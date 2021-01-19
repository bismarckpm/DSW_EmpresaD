import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  constructor(private route: ActivatedRoute, private router: Router) {}
  //CONTROL DE MUESTRA DE NOTIFICACIONES
  sections: any[] = [
  { title:'Usuarios',dir:'usuarios'},
  { title:'Estudios',dir:'estudios'},
  { title:'Home',dir:'home'},
  { title:'Locaciones',dir:'lugares'},
  { title:'Preguntas',dir:'preguntas'},
  { title:'Categorias',dir:'categorias'},
  { title:'SubCategorias',dir:'subcategorias'},
  { title:'Tipos',dir:'tipos'},
  { title:'Presentaciones',dir:'presentaciones'},
  ];
  openNote: boolean=false;
  showFiller = false;
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
      this.router.navigate(['administrador/',_route]);
    } catch (e) {
      console.log(e.message);
    }
  }
}
