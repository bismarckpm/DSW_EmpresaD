import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EstadoService } from '@core/services/estado/estado.service';
import { PaisService } from '@core/services/pais/pais.service';
import { MunicipioService } from '@core/services/municipio/municipio.service';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';
@Component({
  selector: 'app-del-lugar-dialog',
  templateUrl: './del-lugar-dialog.component.html',
  styleUrls: ['./del-lugar-dialog.component.css']
})
export class DelLugarDialogComponent implements OnInit {

  opStatus:string;//S,P,D

  @ViewChild('delLugar') private modalContent: TemplateRef<DelLugarDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder,
     private _paisService:PaisService,
    private _estadoService:EstadoService,
    private _municipioService:MunicipioService,
    private _parroquiaService:ParroquiaService,){}
  
  @Input() _userSelection : number;
  @Input() _tipo : string;

  ngOnInit(): void {
    this.opStatus="S";
  }
  open(id:number,tipo:string){
    this._tipo=tipo;
    this._userSelection=id;
    this.modalRef =this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close(){
    this.opStatus="S";
    this.modalRef.close();
  }
  invokeService(id:number,role:string){
    this.opStatus="P";
    switch(role){
      case 'PA':
      this._paisService.deletePais(this._userSelection,null).subscribe(
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
      this._estadoService.deleteEstado(this._userSelection,null).subscribe(
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
      this._municipioService.deleteMunicipio(this._userSelection,null).subscribe(
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
      this._parroquiaService.deleteParroquia(this._userSelection,null).subscribe(
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
