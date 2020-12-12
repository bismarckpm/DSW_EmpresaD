import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder,FormGroup, } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { MarcaService } from '@core/services/marca/marca.service';
import { Marca } from '@models/marca';

@Component({
  selector: 'app-upd-marca-dialog',
  templateUrl: './upd-marca-dialog.component.html',
  styleUrls: ['./upd-marca-dialog.component.css']
})
export class UpdMarcaDialogComponent implements OnInit {
opStatus:string;//S,P,D,E
  updForm: FormGroup;
  toService: any;
  @ViewChild('updMarca') private modalContent: TemplateRef<UpdMarcaDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder,private _service:MarcaService){}
  @Input() _userSelection : number;
  @Input() _marca : Marca;

  ngOnInit(): void {
    this.opStatus="S";
    this.updForm= this.formBuilder.group({
      nombre:null,
    });
    this.toService= {
      nombre:null,
      _id:null
    }
  }
  open(){
    this.modalRef =this.modalService.open(this.modalContent);
    this.toService= {
      _id:this._marca._id,
      nombre:null,
    }
    this.modalRef.result.then();
  }
  close(){
    this.opStatus="S";
    this.modalRef.close();
  }
  updateMarca(data){
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
  }
  invokeService(){
    Object.entries(this.updForm.value).forEach(([key,field],ind)=>{
      if(field !== null){
        this.toService[key]=field;
      }
      else{
        this.toService[key] = this._marca[key];
      }
    })
    this.opStatus="P";
    this.updateMarca(this.toService);
  }
}
