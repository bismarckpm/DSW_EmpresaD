import { Component, OnInit } from '@angular/core';

class UserModel {
  id:number;
}

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {
  users: UserModel[] = [{id:1},{id:2},{id:3}];
  displayedColumns: string[] = ['id'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  constructor() { }

  ngOnInit(): void {}

}
