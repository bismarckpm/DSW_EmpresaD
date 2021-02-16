import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BasicInfoDialogComponent } from '../../components/basic-info-dialog/basic-info-dialog.component';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboardCliente.component.html',
  styleUrls: ['./dashboardCliente.component.css'],
})
export class dashboardClienteComponent implements OnInit {
  constructor(private route: ActivatedRoute, private router: Router) {}
  sections: any[] = [
    { title: 'Solicitudes de estudios', dir: 'solicitudes', icon: 'work' },
    { title: 'Estudios culminados', dir: 'estudios', icon: 'class' },
    { title: 'Home', dir: 'home', icon: 'home' },
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
          _id: 31,
          nombre: 'Caesar',
          apellido: 'Mosley',
          rol: 'cliente',
          estado: 'activo',
          correo: 'CM10@gmail.com',
        })
      );
      this.userSession = JSON.parse(localStorage.getItem('user_data'));
    } else {
      this.userSession = JSON.parse(localStorage.getItem('user_data'));
    }
  }
  ngOnInit(): void {
    this.onDir('home');
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
      this.router.navigate(['cliente/', _route]);
    } catch (e) {
      console.log(e.message);
    }
  }
}
