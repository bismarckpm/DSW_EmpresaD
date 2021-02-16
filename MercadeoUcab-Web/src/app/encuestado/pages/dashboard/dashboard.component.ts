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
    { title: 'Encuestas pendientes', dir: 'pending', icon: 'assignment_late' },
    { title: 'Home', dir: 'home', icon: 'home' },
  ];
  openNote: boolean = false;
  showFiller = false;
  userSession = null;
  @ViewChild('uInfo') private infoComponent: BasicInfoDialogComponent;
  async openInfoModal() {
    return await this.infoComponent.open();
  }

  getOpenNote(): boolean {
    return this.openNote;
  }
  checkUser() {
    if (localStorage.getItem('user_data') === null) {
      localStorage.setItem('token','WHATEVER');
      localStorage.setItem(
        'user_data',
        JSON.stringify({
          _id: 3,
          nombre: 'Zeus',
          apellido: 'Berg',
          rol: 'encuestado',
          estado: 'activo',
          correo: 'ZB@gmail.com',
        })
      );
      this.userSession = JSON.parse(localStorage.getItem('user_data'));
    } else {
      this.userSession = JSON.parse(localStorage.getItem('user_data'));
    }
  }
  ngOnInit(): void {
    this.onDir('home');
    setTimeout(() => {
      this.openNote = true;
      setTimeout(() => {
        this.openNote = false;
      }, 10000);
    }, 2000);
  }
  logOut() {
    localStorage.clear();
    this.infoComponent.close();
    this.router.navigate(['login']);
  }
  onDir(_route: string): void {
    try {
      this.router.navigate(['encuestado/', _route]);
      this.checkUser();
    } catch (e) {
      console.log(e.message);
    }
  }
}
