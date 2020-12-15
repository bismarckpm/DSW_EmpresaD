import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder,FormGroup, } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DelLugarDialogComponent } from '../../components/dialogs/del-lugar-dialog/del-lugar-dialog.component';
import { UpdLugarDialogComponent } from '../../components/dialogs/upd-lugar-dialog/upd-lugar-dialog.component';
import { EstadoService } from '@core/services/estado/estado.service';
import { PaisService } from '@core/services/pais/pais.service';
import { MunicipioService } from '@core/services/municipio/municipio.service';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';
import { Estado } from '@models/estado';
import { Pais } from '@models/pais';
import { Municipio } from '@models/municipio';

interface SearchLug {
  t:string,
  do:number,
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
  

  op:string = "";
  searchState:string="U";
  tipoLugar:string="";//PR,MU,ES,PA
  opStatus:string;//S,P,D
  targetData:any;
  searchForm:FormGroup;
  addForm:FormGroup;
  _paises:Pais[]=[];
  _estados:Estado[]=[];
  _municipios:Municipio[]=[];
  
  //CONTROL DE BUSQUEDA
  searchLugar: SearchLug[] = [];
  searchResults ={
    'PA':[],
    'ES':[],
    'MU':[],
    'PR':[]
  };
  async setData(inD){
    this.targetData=inD;
  }
  @ViewChild('updLugar') private updComponent:UpdLugarDialogComponent;
  async openUpdModal(id:number,tipo:string,data:any) {
    await this.setData(data);
    return await this.updComponent.open(tipo,this.targetData);
  }
  @ViewChild('delLugar') private delComponent:DelLugarDialogComponent;
  async openDelModal(id:number,tipo:string) {
    return await this.delComponent.open(id,tipo);
  }
  //dataSource : MatTableDataSource<Lugar>;
  //CHEQUEO DE OPERACION
  getAsociados(){
    let testPais = {
        _id:1,
        nombre:'Test pais'
    };
    let testEstado = {
        _id:1,
        nombre:'Test estado',
        pais:testPais
    };
    let testMunicipio = {
       _id:1,
      nombre:'Test  municipio',
      estado:testEstado
    }
    this._paisService.getPaises().subscribe(
      (response) => {
        console.log(response);
        this._paises=response.data;
      },
      (error) => {
        console.log(error);
        this._paises=[testPais];
      }
    );
    this._estadoService.getEstados().subscribe(
      (response) => {
        console.log(response);
        this._estados=response.data;
      },
      (error) => {
        console.log(error);
        this._estados=[testEstado];
      }
    )
    this._municipioService.getMunicipios().subscribe(
      (response) => {
        console.log(response);
        this._municipios=response.data;
      },
      (error) => {
        console.log(error);
        this._municipios=[testMunicipio];
      }
    )
  }
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
    });
    this.getAsociados();
    this.addForm= this.formBuilder.group({
      nombre:null,
      pais:null,
      estado:null,
      municipio:null,
      valor_socio_economico:null,
    });
  }
  filterData(byName:string){

  }
  invokeSearch(){
    let testPais = {
        _id:1,
        nombre:'Test pais'
    };
    let testEstado = {
        _id:1,
        nombre:'Test estado',
        pais:testPais,
    };
    let testMunicipio = {
       _id:1,
      nombre:'Test  municipio',
      estado:testEstado,
    }
    let testParroquia = { 
       _id:1,
      nombre:'Test  parrroquia',
      municipio:testMunicipio,
      valor_socio_economico:8000
    }


    console.log(this.searchLugar,this.searchForm.value);
    this.searchState="P";
    this.searchResults = {
      'PA':(this.searchLugar[0].do === 1)?[testPais]:[],
      'ES':(this.searchLugar[1].do === 1)?[testEstado]:[],
      'MU':(this.searchLugar[2].do === 1)?[testMunicipio]:[],
      'PR':(this.searchLugar[3].do === 1)?[testParroquia]:[],
    }
    setTimeout(()=>{
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
    console.log(this.addForm.value);
    //MEDIO PARA DETERMINAR SERVICIO A INVOCAR SEGUN FORMULARIO DE CREACION DE LOCACION
    this.opStatus="P"; 
    switch(role){
      case 'PA':
      this._paisService.createPais(this.addForm.value).subscribe(
      (response) => {
        console.log(response);
        this.opStatus="D"; 
      },
      (error) => {
        console.log(error);
        this.opStatus="E"; 
      }
      );
        break;
      case 'ES':
      this._estadoService.createEstado(this.addForm.value).subscribe(
      (response) => {
        console.log(response);
        this.opStatus="D";  
      },
      (error) => {
        console.log(error);
        this.opStatus="E"; 
      }
      );
        break;
      case 'MU':
      this._municipioService.createMunicipio(this.addForm.value).subscribe(
      (response) => {
        console.log(response);
        this.opStatus="D";  
      },
      (error) => {
        console.log(error);
        this.opStatus="E";  
      }
      );
        break;
      case 'PR':
      this._parroquiaService.createParroquia(this.addForm.value).subscribe(
      (response) => {
        console.log(response);
         this.opStatus="D";  
      },
      (error) => {
        console.log(error);
        this.opStatus="E"; 
      }
      );
        break;
      default:
        break;
    }
    this.getAsociados();
  }
}
