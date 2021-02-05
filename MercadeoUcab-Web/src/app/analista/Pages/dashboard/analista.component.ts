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
  { title:'Estudios asignados',dir:'tasks',icon:'text_snippet'},
  { title:'Home',dir:'overview',icon:'home'},
  ];
  openNote: boolean=false;
  showFiller = false;
  userSession = null;
  @ViewChild('uInfo') private infoComponent: BasicInfoDialogComponent;
  async openInfoModal() {
    return await this.infoComponent.open();
  }
  checkUser(){
    if(localStorage.getItem('user_data') === null){
      localStorage.setItem('user_data',JSON.stringify({
        _id: Math.floor(Math.random() * (1000 - 1) + 1),
        nombre: Math.random().toString(36).substr(2, 5),
        apellido: Math.random().toString(36).substr(2, 5),
        rol: 'Administrador',
        correo: Math.random().toString(36).substr(2, 5),
        estado: 'Activo',
      }));
      this.userSession = JSON.parse(localStorage.getItem('user_data'));
    }
    else {
      this.userSession = JSON.parse(localStorage.getItem('user_data'));
    }
  }
  ngOnInit(): void {
    this.onDir('overview');
    this.checkUser();
    setTimeout(()=>{
      this.openNote=true;
      setTimeout(() => {
        this.openNote=false;
      },10000)
    },2000);
  }
  onDir(_route: string): void {
    try {
     
      this.router.navigate(['analista/',_route]);
    } catch (e) {
      console.log(e.message);
    }
  }
}
