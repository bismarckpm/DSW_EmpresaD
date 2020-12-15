import { Component, Injectable, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SolicitudService } from '@core/services/solicitud/solicitud.service';
import { Solicitud } from '@models/solicitud';
import {Usuario} from "@models/usuario";
import {Marca} from "@models/marca";
@Component({
  selector: 'app-update-solicitud-dialog',
  templateUrl: './update-solicitud-dialog.component.html',
  styleUrls: ['./update-solicitud-dialog.component.css']
})
@Injectable()
export class UpdateSolicitudDialogComponent implements OnInit {
  opStatus:string;//S,P,D,E
  updForm : FormGroup;

  @ViewChild('updSolicitud') private modalContent: TemplateRef<UpdateSolicitudDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder,private  _service:SolicitudService){}
  @Input() _solicitudSelection : number;
  @Input() _solicitud : Solicitud;
  toService: any;
  ngOnInit(): void {
    this.opStatus="S";
    this.updForm = this.formBuilder.group({
      usuario:null,
      marca:null,
      presentacion:null,
      tipo:null,
      subcategoria:null,
      estado:null,
      activo:null,
    });
  }
  open(){
    this.modalRef =this.modalService.open(this.modalContent);
    this.modalRef.result.then();
    //console.log('Usuario: ',this._user);
    this.toService ={
      _id:this._solicitud._id,
      tipo:null,
      subcategoria:null,
      marca:null,
      estado:null,
      activo:this._solicitud.activo,
    };
  }
  close(){
    this.opStatus="S";
    this.updForm = this.formBuilder.group({
      usuario:null,
      marca:null,
      estado:null,
      activo:null,
    });
    this.modalRef.close();
  }
  updateUser(id,data){
    this._service.updateSolicitud(id,data).subscribe(
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
  invokeService(){
    Object.entries(this.updForm.value).forEach(([key,field],ind)=>{
      if(field !== null){
        this.toService[key]=field;
      }
      else{
        this.toService[key] = this._solicitud[key];
      }
    })
    //console.log(this.toService,this.updForm.value);
    this.opStatus="P";
    this.updateUser(this._solicitudSelection,this.toService);
    /*setTimeout(()=>{
      this.opStatus="D";
    },3000);*/
  }
}
