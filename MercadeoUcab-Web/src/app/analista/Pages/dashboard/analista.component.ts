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
    { title: 'Estudios asignados', dir: 'tasks', icon: 'text_snippet' },
    { title: 'Home', dir: 'overview', icon: 'home' },
  ];
  openNote: boolean = false;
  showFiller = false;
  userSession = null;
  @ViewChild('uInfo') private infoComponent: BasicInfoDialogComponent;
  async openInfoModal() {
    return await this.infoComponent.open();
  }
  checkUser() {
    if (localStorage.getItem('user_data') === null) {
      localStorage.setItem(
        'user_data',
        JSON.stringify({
          _id: 40,
          nombre: 'Hammett',
          apellido: 'Schneider',
          rol: 'analista',
          estado: 'activo',
          correo: 'HMLETSCH456@gmail.com',
        })
      );
      this.userSession = JSON.parse(localStorage.getItem('user_data'));
    } else {
      this.userSession = JSON.parse(localStorage.getItem('user_data'));
    }
  }
  ngOnInit(): void {
    this.onDir('overview');
    this.checkUser();
    setTimeout(() => {
      this.openNote = true;
      setTimeout(() => {
        this.openNote = false;
      }, 10000);
    }, 2000);
  }
  /*ngOnDestroy(){
    localStorage.clear();
  }*/
  logOut() {
    localStorage.clear();
    this.infoComponent.close();
    this.router.navigate(['login']);
  }
  onDir(_route: string): void {
    try {
      this.router.navigate(['analista/', _route]);
    } catch (e) {
      console.log(e.message);
    }
  }
}
