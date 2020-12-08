import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Categoria } from '@core/models/categoria';
import { DelCategoriaDialogComponent } from '../../components/dialogs/del-categoria-dialog/del-categoria-dialog.component';
import { UpdCategoriaDialogComponent } from '../../components/dialogs/upd-categoria-dialog/upd-categoria-dialog.component';
@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  styleUrls: ['./categorias.component.css']
})
export class CategoriasComponent implements OnInit {
  op:string;
  searchState:string;
  categorias: Categoria [] = [];
  dataSource : MatTableDataSource<Categoria>;
  userSelection:number = 0;
  displayedColumns: string[] = ['id','desc','selector','ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();

  @ViewChild('updCategoria') private updComponent:UpdCategoriaDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  @ViewChild('delCategoria') private delComponent:DelCategoriaDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }

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
      this.dataSource = new MatTableDataSource<Categoria>(this.categorias);
      this.searchState="D";
    },3000);
  }
  doSearch(){
    this.searchState="I";
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
}
