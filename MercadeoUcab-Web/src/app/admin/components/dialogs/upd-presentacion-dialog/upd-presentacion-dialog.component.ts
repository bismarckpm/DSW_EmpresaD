import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-upd-presentacion-dialog',
  templateUrl: './upd-presentacion-dialog.component.html',
  styleUrls: ['./upd-presentacion-dialog.component.css']
})
export class UpdPresentacionDialogComponent implements OnInit {

  opStatus:string;//S,P,D

  @ViewChild('updPresentacion') private modalContent: TemplateRef<UpdPresentacionDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder){}
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
