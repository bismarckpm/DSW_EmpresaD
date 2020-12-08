import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddPreguntaDialogComponent } from '../../components/dialogs/add-pregunta-dialog/add-pregunta-dialog.component';

@Component({
  selector: 'app-preguntas',
  templateUrl: './preguntas.component.html',
  styleUrls: ['./preguntas.component.css']
})
export class PreguntasComponent implements OnInit {
  op:string;
  searchState:string;
  setOperation(chOp:string){
    this.op=chOp;
    if(chOp !== ''){
      this.searchState="I";
    }
    else{
      this.searchState="U";
    }
  }
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder) { }
  
  @ViewChild('addPreg') private modalComponent:AddPreguntaDialogComponent
  async openModal() {
    return await this.modalComponent.open();
  }
  /*
  @ViewChild('updPreg') private modalComponent:AddPreguntaDialogComponent
  async openModal() {
    return await this.modalComponent.open();
  }
  @ViewChild('delPreg') private modalComponent:AddPreguntaDialogComponent
  async openModal() {
    return await this.modalComponent.open();
  }
  */

  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
  }
}
