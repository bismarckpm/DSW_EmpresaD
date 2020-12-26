import { Component, Injectable, OnInit, TemplateRef, ViewChild } from '@angular/core';
import {NgbModal,NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';
@Component({
  selector: 'app-add-pregunta-dialog',
  templateUrl: './add-pregunta-dialog.component.html',
  styleUrls: ['./add-pregunta-dialog.component.css']
})
@Injectable()
export class AddPreguntaDialogComponent implements OnInit {
  opStatus:string;
  addForm:FormGroup;
  @ViewChild('addPreg') private modalContent: TemplateRef<AddPreguntaDialogComponent>;
  private modalRef: NgbModalRef;

  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _preguntaService: PreguntaService,){}

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      nombre_pregunta: null,
      tipo: null,
      rango: null,
      fk_usuario: null,
      opciones: null,
    });
  }
  
  open(){
    this.modalRef =this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close(){
    this.opStatus="S";
    this.modalRef.close();
    this.addForm = this.formBuilder.group({
      nombre_pregunta: null,
      tipo: null,
      rango: null,
      fk_usuario: null,
      opciones: null,
    });
  }
  addPregunta(data){
    /*this._preguntaService.createPregunta(data).subscribe(
      (response) => {
        console.log(response);
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    )*/
  }
  invokeService(){
    this.opStatus="P";
    this.addPregunta(this.addForm.value);
  }
}
