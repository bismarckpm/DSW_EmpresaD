import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lugares',
  templateUrl: './lugares.component.html',
  styleUrls: ['./lugares.component.css']
})
export class LugaresComponent implements OnInit {

  constructor() { }
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
  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
  }

}
