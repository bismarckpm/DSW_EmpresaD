import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Categoria } from '@core/models/categoria';
import { CategoriaService } from '@core/services/categoria/categoria.service';
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
  opStatus:string;//S,P,D,E
  searchForm: FormGroup;
  targetCategoria: Categoria;
  addForm:FormGroup;
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
      this.opStatus="S";
    }
  }
  constructor( 
    private formBuilder: FormBuilder,
    private _categoriaService: CategoriaService,
  ){ }

  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
    this.opStatus="S";
    this.addForm =  this.formBuilder.group({
      nombre:null,
    });
    this.searchForm = this.formBuilder.group({
      nombre:null,
      activo:null,
    });
  }
  getCategorias(){
    this._categoriaService.getCategorias().subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    )
  }
  addCategoria(data){
    this._categoriaService.createCategoria(data).subscribe(
      (response) => {
        console.log(response);
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    )
  }
  serviceInvoke(){
    this.opStatus="P";
    this.addCategoria(this.addForm.value);
    this.addForm = this.formBuilder.group({
      nombre:null,
    });
  }
  invokeSearch(){
    this.searchState="P";
    this.categorias = [];
    this.userSelection=0;
    //INVOCAR BUSQUEDA
    setTimeout(()=>{
      //DATA SOURCE EDIT
      for (let i = 0; i < Math.floor(Math.random()*(100-1)+1); i++) {
        this.categorias.push({
         _id:Math.floor(Math.random()*(1000-1)+1),
         nombre:Math.random().toString(36).substr(2, 5),
         activo:true,
         creado_el:new Date(),
         modificado_el:new Date(),
        });
      }
      this.dataSource = new MatTableDataSource<Categoria>(this.dataFilter(this.categorias));
      this.searchState="D";
    },3000);
  }
  doSearch(){
    this.searchState="I";
  }
  dataFilter(dataArray:Categoria[]): Categoria[]{
    //console.log(this.searchForm.value);
    let filtered: Categoria[] = [];
    dataArray.forEach((res,ind) => {
      let inc = true;
      Object.entries(this.searchForm.value).forEach(([key,field],_ind)=>{
        if(inc === true && field !== null){
          if(field instanceof Date && (res[key] >= field && res[key] <= Date.now())){
            return;
          }
          else if(typeof(field)==='string' && res[key].startsWith(field)){
            return;
          }
          else if(typeof(field)==='boolean' && res[key]===field){
            return;
          }
          else{
            inc = false;
          }
        }
      })
      if(inc === true){
        filtered.push(res);
      }
    })
    console.log(dataArray,filtered);
    return filtered;
  }
  selectUser(id: number,data:Categoria){
    if(id === this.userSelection){
      this.userSelection = 0;
      this.targetCategoria=null;
    }
    else{
      this.userSelection=id;
      this.targetCategoria=data;
    }
  }
  isSelected(id: number):boolean{
    if(id === this.userSelection){
      return true;
    }
    return false;
  }
}
