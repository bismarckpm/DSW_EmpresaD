import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DelLugarDialogComponent } from '../../components/dialogs/del-lugar-dialog/del-lugar-dialog.component';
import { UpdLugarDialogComponent } from '../../components/dialogs/upd-lugar-dialog/upd-lugar-dialog.component';
import { Pregunta } from '@models/pregunta';
import { EstadoService } from '@core/services/estado/estado.service';
import { PaisService } from '@core/services/pais/pais.service';
import { MunicipioService } from '@core/services/municipio/municipio.service';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';

@Component({
  selector: 'app-lugares',
  templateUrl: './lugares.component.html',
  styleUrls: ['./lugares.component.css']
})
export class LugaresComponent implements OnInit {
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _paisService:PaisService,
    private _estadoService:EstadoService,
    private _municipioService:MunicipioService,
    private _parroquiaService:ParroquiaService,
    ) { }
  userSelection:number = 0;
  
  @ViewChild('updLugar') private updComponent:UpdLugarDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  @ViewChild('delLugar') private delComponent:DelLugarDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }
  op:string = "";
  searchState:string="U";
  //dataSource : MatTableDataSource<Lugar>;
  //preguntas: Pregunta [];
  //CHEQUEO DE OPERACION
  opCheck(comp:string){
    if(comp === this.op){
      return true;
    }
    return false;
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
  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
  }
  invokeSearch(){
    this.searchState="P";
    setTimeout(()=>{
      //this.dataSource = new MatTableDataSource<Pregunta>(this.preguntas);
      this.searchState="D";
    },3000);
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
