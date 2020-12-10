import { Component, Injectable, OnInit, TemplateRef, ViewChild } from '@angular/core';
import {NgbModal,NgbModalRef} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-add-pregunta-dialog',
  templateUrl: './add-pregunta-dialog.component.html',
  styleUrls: ['./add-pregunta-dialog.component.css']
})
@Injectable()
export class AddPreguntaDialogComponent implements OnInit {
  opStatus:string;
  @ViewChild('addPreg') private modalContent: TemplateRef<AddPreguntaDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal){}

  ngOnInit(): void {}
  
  open(){
    this.modalRef =this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close(){
    this.opStatus="S";
    this.modalRef.close();
  }
  invokeService(){

  }
}
