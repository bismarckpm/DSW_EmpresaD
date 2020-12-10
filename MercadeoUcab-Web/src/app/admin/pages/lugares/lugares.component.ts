import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder,FormGroup, } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DelLugarDialogComponent } from '../../components/dialogs/del-lugar-dialog/del-lugar-dialog.component';
import { UpdLugarDialogComponent } from '../../components/dialogs/upd-lugar-dialog/upd-lugar-dialog.component';
import { Pregunta } from '@models/pregunta';
import { EstadoService } from '@core/services/estado/estado.service';
import { PaisService } from '@core/services/pais/pais.service';
import { MunicipioService } from '@core/services/municipio/municipio.service';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';


interface SearchLug {
  t:string;
  do:number;
}

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
  async openUpdModal(id:number,type:string) {
    return await this.updComponent.open();
  }
  @ViewChild('delLugar') private delComponent:DelLugarDialogComponent;
  async openDelModal(id:number,type:string) {
    return await this.delComponent.open();
  }
  op:string = "";
  searchState:string="U";
  tipoLugar:string="";//PR,MU,ES,PA
  opStatus:string;//S,P,D
  searchForm:FormGroup;
  //CONTROL DE BUSQUEDA
  searchLugar: SearchLug[] = [];
  searchResults ={
    'PA':[],
    'ES':[],
    'MU':[],
    'PR':[]
  };
  //dataSource : MatTableDataSource<Lugar>;
  //CHEQUEO DE OPERACION
  checkForSearch(i:number){
    if(this.searchLugar[i].do === 1){
      this.searchLugar[i].do = 0;
    }
    else {
      this.searchLugar[i].do = 1;
    }
  }
  searchCheck(pos:number){
    if(this.searchLugar[pos].do === 1){
      return true;
    }
    return false;
  }
  setOperation(chOp:string){
    this.op=chOp;
    if(chOp !== ''){
      this.searchState="I";
      this.opStatus="S"; 
      this.setTipoLugar('');
    }
    else{
      this.searchState="U";
    }
  }
  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
    this.searchLugar=[{t:'PA',do:0},{t:'ES',do:0},{t:'MU',do:0},{t:'PR',do:0}];
    this.searchForm = this.formBuilder.group({
      nombre:'',
    })
  }
  filterData(byName:string){

  }
  invokeSearch(){
    console.log(this.searchLugar,this.searchForm.value);
    this.searchState="P";
    this.searchResults = {
      'PA':(this.searchLugar[0].do === 1)?[{n:'test'}]:[],
      'ES':(this.searchLugar[1].do === 1)?[{n:'test'}]:[],
      'MU':(this.searchLugar[2].do === 1)?[{n:'test'}]:[],
      'PR':(this.searchLugar[3].do === 1)?[{n:'test'}]:[],
    }
    setTimeout(()=>{
      //this.dataSource = new MatTableDataSource<Pregunta>(this.preguntas);
      this.searchState="D";
      console.log(this.searchResults);
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
  doSearch(){
    this.searchState="I";
    this.searchLugar=[{t:'PA',do:0},{t:'ES',do:0},{t:'MU',do:0},{t:'PR',do:0}];
  }
  setTipoLugar(tipo:string){
    this.tipoLugar=tipo;
  }
  serviceInvoke(role:string){
    //MEDIO PARA DETERMINAR SERVICIO A INVOCAR SEGUN FORMULARIO DE CREACION DE USUARIO
    switch(role){
      case 'PA':
        break;
      case 'ES':
        break;
      case 'MU':
        break;
      case 'PR':
        break;
      default:
        break;
    }
    this.opStatus="P";
    setTimeout(()=>{
      this.opStatus="D";
    },3000);
  }
}
