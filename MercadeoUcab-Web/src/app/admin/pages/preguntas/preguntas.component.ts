import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
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
      this.dataSource = new MatTableDataSource<Pregunta>(this.preguntas);
      this.searchState="D";
    },3000);
  }
  doSearch(){
    this.searchState="I";
  }
}
