import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EstadoService } from '@core/services/estado/estado.service';
import { PaisService } from '@core/services/pais/pais.service';
import { MunicipioService } from '@core/services/municipio/municipio.service';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';

@Component({
  selector: 'app-upd-lugar-dialog',
  templateUrl: './upd-lugar-dialog.component.html',
  styleUrls: ['./upd-lugar-dialog.component.css']
})
export class UpdLugarDialogComponent implements OnInit {

  opStatus:string;//S,P,D

  @ViewChild('updLugar') private modalContent: TemplateRef<UpdLugarDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder,
     private _paisService:PaisService,
    private _estadoService:EstadoService,
    private _municipioService:MunicipioService,
    private _parroquiaService:ParroquiaService,){}
  @Input() _userSelection : number;

  ngOnInit(): void {
    this.opStatus="S";
  }
  open(){
    this.modalRef =this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close(){
    this.opStatus="S";
    this.modalRef.close();
  }
  invokeService(){
    this.opStatus="P";
    setTimeout(()=>{
      this.opStatus="D";
    },3000);
  }

}
