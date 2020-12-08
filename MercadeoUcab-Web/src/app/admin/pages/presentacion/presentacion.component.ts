import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { PresentacionService } from '@core/services/presentacion/presentacion.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-presentacion',
  templateUrl: './presentacion.component.html',
  styleUrls: ['./presentacion.component.css']
})
export class PresentacionComponent implements OnInit {
  op:string;
  searchState:string;
  opStatus:string;//S,P,D
  userSelection:number = 0;

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
  
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _presentacionService: PresentacionService,){}

  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
  }
  selectUser(id: number){
    if(id === this.userSelection){
      this.userSelection = 0;
    }
    else{
      this.userSelection=id;
    }
  }
  isSelected(id: number):boolean{
    if(id === this.userSelection){
      return true;
    }
    return false;
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
