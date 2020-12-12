import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder,FormGroup, } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import { Tipo } from '@models/tipo';


@Component({
  selector: 'app-update-tipo-dialog',
  templateUrl: './update-tipo-dialog.component.html',
  styleUrls: ['./update-tipo-dialog.component.css']
})
export class UpdateTipoDialogComponent implements OnInit {
opStatus:string;//S,P,D,E
  updForm: FormGroup;
  toService: any;
  @ViewChild('updTipo') private modalContent: TemplateRef<UpdateTipoDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
  	private modalService: NgbModal,
  	private formBuilder: FormBuilder,
  	//private _service:TipoService
  	){}

  @Input() _userSelection : number;
  @Input() _tipo : Tipo;

  ngOnInit(): void {
    this.opStatus="S";
    this.updForm= this.formBuilder.group({
      nombre:null,
    });
    this.toService= {
     
    }
  }
  open(){
    this.modalRef =this.modalService.open(this.modalContent);
    this.toService= {
      _id:this._tipo._id,
      nombre:null,
    }
    this.modalRef.result.then();
  }
  close(){
    this.opStatus="S";
    this.modalRef.close();
  }
  /*updateTipo(data){
    this._service.updateMarca(data['_id'],data).subscribe(
      (response) => {
        console.log(response);
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    )
  }*/
  invokeService(){
    Object.entries(this.updForm.value).forEach(([key,field],ind)=>{
      if(field !== null){
        this.toService[key]=field;
      }
      else{
        this.toService[key] = this._tipo[key];
      }
    })
    this.opStatus="P";
    //this.updateTipo(this.toService);
  }
}
