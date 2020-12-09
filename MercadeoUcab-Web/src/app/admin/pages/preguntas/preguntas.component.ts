import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Pregunta } from '@core/models/pregunta';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddPreguntaDialogComponent } from '../../components/dialogs/add-pregunta-dialog/add-pregunta-dialog.component';
import { DelPreguntaDialogComponent } from '../../components/dialogs/del-pregunta-dialog/del-pregunta-dialog.component';
import { UpdatePreguntaDialogComponent } from '../../components/dialogs/update-pregunta-dialog/update-pregunta-dialog.component';

@Component({
  selector: 'app-preguntas',
  templateUrl: './preguntas.component.html',
  styleUrls: ['./preguntas.component.css']
})
export class PreguntasComponent implements OnInit {
  
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _preguntaService: PreguntaService,){}
    
  op:string;
  searchState:string;
   //INDICE DE USUARIO SELECCIONADO
   userSelection:number = 0;
   searchForm: FormGroup;
   addForm:FormGroup;
   //LISTA DE USUARIOS DEVUELTOS EN BÚSQUEDA
   preguntas: Pregunta [] = [];
   dataSource : MatTableDataSource<Pregunta>;

   displayedColumns: string[] = ['id','desc','selector','ops'];
   columnsToDisplay: string[] = this.displayedColumns.slice();

  setOperation(chOp:string){
    this.op=chOp;
    if(chOp !== ''){
      this.searchState='I';
      console.log('I',chOp);
    }
    else{
      this.searchState='U';
    }
  }
  @ViewChild('delPregunta') private delComponent:DelPreguntaDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }
  @ViewChild('updPregunta') private updComponent:UpdatePreguntaDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }

  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
    this.searchForm = this.formBuilder.group({
      tipo:null,
      creado_el:null,
    });
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
    if(this.searchForm.value['creado_el'] !== null){
      this.searchForm.get('creado_el').setValue(new Date(this.searchForm.value['creado_el']));
    }
    this.searchState="P";
    setTimeout(()=>{
      this.dataSource = new MatTableDataSource<Pregunta>(this.dataFilter(this.preguntas));
      this.searchState="D";
    },3000);
  }
  dataFilter(dataArray:Pregunta[]): Pregunta[]{
    console.log(this.searchForm.value);
    let filtered: Pregunta[] = [];
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
  doSearch(){
    this.searchState="I";
  }
}
