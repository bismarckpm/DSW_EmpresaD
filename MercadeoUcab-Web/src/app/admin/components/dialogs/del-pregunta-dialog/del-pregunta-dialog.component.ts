import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-del-pregunta-dialog',
  templateUrl: './del-pregunta-dialog.component.html',
  styleUrls: ['./del-pregunta-dialog.component.css']
})
export class DelPreguntaDialogComponent implements OnInit {

  opStatus:string;//S,P,D

  @ViewChild('updLugar') private modalContent: TemplateRef<DelPreguntaDialogComponent>;
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
