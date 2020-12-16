import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder,FormGroup, } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EstadoService } from '@core/services/estado/estado.service';
import { PaisService } from '@core/services/pais/pais.service';
import { MunicipioService } from '@core/services/municipio/municipio.service';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';
import { Estado } from '@models/estado';
import { Pais } from '@models/pais';
import { Municipio } from '@models/municipio';

@Component({
  selector: 'app-upd-lugar-dialog',
  templateUrl: './upd-lugar-dialog.component.html',
  styleUrls: ['./upd-lugar-dialog.component.css']
})
export class UpdLugarDialogComponent implements OnInit {

  opStatus:string;//S,P,D,E

  @ViewChild('updLugar') private modalContent: TemplateRef<UpdLugarDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder,
    private _paisService:PaisService,
    private _estadoService:EstadoService,
    private _municipioService:MunicipioService,
    private _parroquiaService:ParroquiaService,){}
  
  updForm:FormGroup;
  @Input() _tipo : string;
  @Input() _updData : any;
  toService = {
    nombre:null,
     _id:null,
  };
  ngOnInit(): void {
    this.opStatus="S";
    this.updForm= this.formBuilder.group({
      nombre:null,
      _id:null,
    });
  }
  open(tipoLugar,data){
    this._updData=data;
    this._tipo=tipoLugar;
    this.updForm= this.formBuilder.group({
      nombre:null,
      _id:this._updData._id
    });
    this.toService = {
    nombre:null,
     _id:null,
    };
    this.modalRef =this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close(){
    this.opStatus="S";
    this.modalRef.close();
  }
  invokeService(role:string){
    Object.entries(this.updForm.value).forEach(([key,field],ind)=>{
      if(field !== null){
        this.toService[key]=field;
      }
      else{
        this.toService[key] = this._updData[key];
      }
    })
    //MEDIO PARA DETERMINAR SERVICIO A INVOCAR SEGUN FORMULARIO DE CREACION DE LOCACION
    this.opStatus="P"; 
    console.log(this.toService);
    switch(role){
      case 'PA':
      this._paisService.updatePais(this.toService._id,this.toService).subscribe(
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
      this._estadoService.updateEstado(this.toService._id,this.toService).subscribe(
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
      this._municipioService.updateMunicipio(this.toService._id,this.toService).subscribe(
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
      this._parroquiaService.updateParroquia(this.toService._id,this.toService).subscribe(
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
  }

}
