import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { toBackendAnswer } from '@core/models/toBackendAnswer';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';
import { RespuestaService } from '@core/services/respuesta/respuesta.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-encuesta-dialog',
  templateUrl: './encuesta-dialog.component.html',
  styleUrls: ['./encuesta-dialog.component.css']
})
export class EncuestaDialogComponent implements OnInit {

  constructor(
    private modalService: NgbModal,
    private _respuestaService: RespuestaService,
    private _preguntaService: PreguntaService,
    private _estudioService: EstudioService
 ) { }

 @ViewChild('encuesta')
 private modalContent: TemplateRef<EncuestaDialogComponent>;
 private modalRef: NgbModalRef;

 @Input() _usuario: any;
 @Input() _encuesta: any;
 @Input() _answers:any;
 alteredEstudio: any = null;
 multiOption:any = [];
 singleOption:any = [];
 opStatus:string = 'S';
 rangeOption:any = '';


 currentQuestion : any = null;

 ngOnInit(): void {
 }

 setQuestion(){
  
 }
 /*
 setMultOption(pregId, op) {
  let ILength: number = this._respuestas[pregId].m_val.length;
  this._respuestas[pregId].m_val = this._respuestas[pregId].m_val.filter(
    (regOp) => regOp._id !== op._id
  );
  if (ILength === this._respuestas[pregId].m_val.length) {
    this._respuestas[pregId].m_val.push(op);
  }
  //console.log(this._respuestas[pregId].m_val);
}
checkMultiple(pregInd: number, opInd: number) {
  let res: boolean = false;
  this._respuestas[pregInd].m_val.forEach((opc, ind) => {
    if (opc._id === opInd) {
      res = true;
    }
  });
  //console.log(res,` for ${opInd}`);
  return res;
}*/

 setBackendAnswer(simple, respuesta, encuestaEstudio, usuario, opcion) {
  let singleAnswer = new toBackendAnswer({ _id: this._encuesta._id }, { _id: this._usuario._id });
  singleAnswer.dtoEncuestaEstudio._id = encuestaEstudio;
  singleAnswer.dtousuario._id = usuario;
  if (simple === true) {
    singleAnswer.dtoopcion = opcion;
  } else {
    singleAnswer.respuesta = respuesta;
  }
  //this.toAdd.push(singleAnswer);
}
addRespuesta(data){
  this._respuestaService.createRespuesta(data).subscribe(
    (response: any) => {
      console.log(response);
      //this.opStatus = 'D';
    },
    (error) => {
      console.log(error);
      //this.opStatus = 'E';
    }
  );
}
/*
saveSurvey(data) {
  this.opStatus = 'P';
  this._respuestaService.saveSurvey(data).subscribe(
    (response: any) => {
      console.log(response);
      this.opStatus = 'D';
    },
    (error) => {
      console.log(error);
      this.opStatus = 'E';
    }
  );
}*/
 sendAnswer(user,preg,resp){
   //ENVIO DE RESPUESTA SINGULAR
   switch(preg){
    case 'simple':
      break;
    case 'multiple':
      break;
    case 'boolean':
      break;  
    case 'abierta':
      break;
    case 'rango':
      break;
   }
 }

 open() {
   const {_id,encuesta,...rest} = this._encuesta;
   this.alteredEstudio = {_id,encuesta};
   this.alteredEstudio.encuesta =[...this.alteredEstudio.encuesta.filter((preg,ind) => this._answers[`${preg._id}`].length === 0)];
   this.modalRef = this.modalService.open(this.modalContent);
   this.modalRef.result.then();
   console.log(this._answers,this.alteredEstudio.encuesta);
 }
 close() {
   this.modalRef.close();
 }

}
