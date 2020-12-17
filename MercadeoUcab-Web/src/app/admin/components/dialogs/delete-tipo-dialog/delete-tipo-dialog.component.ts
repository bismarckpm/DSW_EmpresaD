import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
//import { TipoSolicitudService } from '@core/services/tipo/tipo.service';
import { Tipo } from '@models/tipo';
import { TipoService } from '@core/services/tipo/tipo.service';

@Component({
  selector: 'app-delete-tipo-dialog',
  templateUrl: './delete-tipo-dialog.component.html',
  styleUrls: ['./delete-tipo-dialog.component.css']
})
export class DeleteTipoDialogComponent implements OnInit {

  opStatus:string;//S,P,D

  @ViewChild('delTipo') private modalContent: TemplateRef<DeleteTipoDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal,
  private _service:TipoService
  	){}
  @Input() _userSelection : number;
  @Input() _tipo : Tipo;

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
    this._service.deleteTipo(this._tipo._id,null).subscribe(
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

}
