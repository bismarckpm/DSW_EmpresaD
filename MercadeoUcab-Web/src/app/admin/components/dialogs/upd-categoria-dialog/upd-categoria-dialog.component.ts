import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CategoriaService } from '@core/services/categoria/categoria.service';
@Component({
  selector: 'app-upd-categoria-dialog',
  templateUrl: './upd-categoria-dialog.component.html',
  styleUrls: ['./upd-categoria-dialog.component.css']
})
export class UpdCategoriaDialogComponent implements OnInit {

  opStatus:string;//S,P,D

  @ViewChild('delCategoria') private modalContent: TemplateRef<UpdCategoriaDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder,_service:CategoriaService){}
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
