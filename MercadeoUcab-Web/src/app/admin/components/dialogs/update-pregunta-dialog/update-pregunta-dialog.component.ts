import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Pregunta } from '@models/pregunta';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';
@Component({
  selector: 'app-update-pregunta-dialog',
  templateUrl: './update-pregunta-dialog.component.html',
  styleUrls: ['./update-pregunta-dialog.component.css'],
})
export class UpdatePreguntaDialogComponent implements OnInit {
  opStatus: string; //S,P,D

  @ViewChild('updPregunta')
  private modalContent: TemplateRef<UpdatePreguntaDialogComponent>;
  private modalRef: NgbModalRef;
  updForm: FormGroup;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _preguntaService: PreguntaService
  ) {}
  @Input() _userSelection: number;
  @Input() _pregunta: Pregunta;
  minF:number=0;
  maxF:number=0;
  rangeConcat(limit,val){
    if(limit === 0){
      this.minF=val;
    }
    else if(limit === 1){
      this.maxF = val;
    } 
    if((this.minF !== 0) && (this.maxF !==0) && (this.minF < this.maxF)){
      this.updForm.get('rango').setValue(`${this.minF}&${this.maxF}`);
    }
  }
  /*
  setOption(opInd,opName){
    this._pregunta[opInd].nombre_opcion=opName;
  }*/
  ngOnInit(): void {
    this.opStatus = 'S';
    this.updForm = this.formBuilder.group({
      nombre_pregunta:null,
      rango:null,
      tipo:null,
    });
  }
  open() {
    this.modalRef = this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close() {
    this.opStatus = 'S';
    this.modalRef.close();
  }
  updatePregunta(id, data) {
    this._preguntaService.updatePregunta(id, data).subscribe(
      (response) => {
        console.log(response);
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
      }
    );
  }
  invokeService() {
    /*if((this.updForm.get('tipo').value === 'simple' || this.updForm.get('tipo').value === 'multiple' ) && this._pregunta.opciones.length > 0){
      this.updForm.get('opciones').setValue(this._pregunta.opciones);
      console.log('')
    }*/
    let toUpdate: any = {id:this._pregunta._id,...this.updForm.value};
    Object.entries(this.updForm.value).forEach(([key, field], ind) => {
      if (field !== null) {
        toUpdate[key] = field;
      } else {
        toUpdate[key] = this._pregunta[key];
      }
    });
    // Campos que se deben enviar al Back
    // toUpdate.id
    // toUpdate.nombre_pregunta
    // toUpdate.tipo
    // toUpdate.rango
    console.log(toUpdate);
    this.updatePregunta(toUpdate.id, toUpdate);
    this.opStatus = 'P';
    /*setTimeout(() => {
      this.opStatus = 'D';
    }, 3000);*/
    this.updForm = this.formBuilder.group({
      nombre_pregunta:null,
      rango:null,
      tipo:null,
    });
  }
}
