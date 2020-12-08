import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-presentacion',
  templateUrl: './presentacion.component.html',
  styleUrls: ['./presentacion.component.css']
})
export class PresentacionComponent implements OnInit {
  op:string;
  searchState:string;
  opStatus:string;//S,P,D
  setOperation(chOp:string){
    this.op=chOp;
    if(chOp !== ''){
      this.searchState="I";
      this.opStatus="S";
    }
    else{
      this.searchState="U";
    }
  }
  
  constructor(){}

  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
  }
  invokeSearch(){
    setTimeout(()=>{
      //DATA SOURCE EDIT
      //this.dataSource = new MatTableDataSource<UserModel>(this.users);
      this.searchState="D";
    },3000);
  }
  invokeUpdate(){

  }
  invokeAdd(){

  }
  invokeDelete(){

  }
}
