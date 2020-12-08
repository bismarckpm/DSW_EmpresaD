import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  styleUrls: ['./categorias.component.css']
})
export class CategoriasComponent implements OnInit {
  op:string;
  searchState:string;
  setOperation(chOp:string){
    this.op=chOp;
    if(chOp !== ''){
      this.searchState="I";
    }
    else{
      this.searchState="U";
    }
  }
  constructor() { }

  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
  }
  invokeSearch(){
    this.searchState="P";
    setTimeout(()=>{
      //DATA SOURCE EDIT
      //this.dataSource = new MatTableDataSource<UserModel>(this.users);
      this.searchState="D";
    },3000);
  }
}
