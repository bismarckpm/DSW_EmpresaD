import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
  Output,
  EventEmitter
} from '@angular/core';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { Opcion } from '@core/models/opcion';
import { toBackendAnswer } from '@core/models/toBackendAnswer';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';
import { RespuestaService } from '@core/services/respuesta/respuesta.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-encuesta-dialog',
  templateUrl: './encuesta-dialog.component.html',
  styleUrls: ['./encuesta-dialog.component.css'],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: { displayDefaultIndicatorType: false },
    },
  ],
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

 @Output() alterAnswers: EventEmitter<any> = new EventEmitter();
 @Input() _usuario: any;
 @Input() _encuesta: any;
 @Input() _answers:any;

 alteredEstudio: any = null;
 openAnswer:string = '';
 multiOption:any = [];
 singleOption:any = {};
 boolOption: boolean;
 opStatus:string = 'S';
 rangeOption:any = '';


 currentQuestion : any = null;

 ngOnInit(): void {}

clearFields(){
 this.openAnswer = '';
 this.multiOption = [];
 this.singleOption = {};
 this.boolOption= false;
 this.rangeOption = '';
}

prepAnswer(content,_type){
  console.log(content);
  switch(_type){
    case 'simple':
      break;
    case 'multiple':
      break;
    case 'boolean':
      this.boolOption = content;
      break;  
    case 'abierta':
      this.openAnswer = content;
      break;
    case 'rango':
      this.rangeOption = content;
      break;
   }
}
setSimpleOption(pregInd,opInd){
  let _op = parseInt(opInd,10);
  if(opInd - 1 !== -1){
    this.singleOption = this._encuesta.encuesta[pregInd].opciones[opInd];
    console.log(this.singleOption);
  }
  else {
    this.singleOption = null;
  }
} 
setMultOption(pregId, op) {
  let ILength: number = this.multiOption.length;
  this.multiOption = this.multiOption.filter(
    (regOp) => regOp._id !== op._id
  );
  if (ILength === this.multiOption.length) {
    this.multiOption.push(op);
  }
  console.log(this.multiOption);
}
checkMultiple(pregInd: number, opInd: number) {
  let res: boolean = false;
  this.multiOption.forEach((opc, ind) => {
    if (opc._id === opInd) {
      res = true;
    }
  });
  return res;
}
 postAnswer(user,_type,stepper,_pregId){
  let Answer : toBackendAnswer;
  Answer = new toBackendAnswer({ _id: _pregId }, { _id: this._usuario._id });
  Answer.dtoopcion = null;
  switch(_type){
    case 'simple':
      Answer.dtoopcion._id = this.singleOption._id;
      Answer.respuesta=null;
      this.sendAnswer(user,_type,[Answer],_pregId);
      break;
    case 'multiple':
      Answer.respuesta=null;
      let multiAnswers: toBackendAnswer[] = [];
      const userId = this._usuario._id;
      this.multiOption.forEach((op,ind) =>{
        let nAnswer: toBackendAnswer = new toBackendAnswer({ _id: _pregId }, { _id: userId });
        Answer.respuesta=null;
        Answer.dtoopcion._id;
        multiAnswers.push(nAnswer);
      });
      this.sendAnswer(user,_type,[...multiAnswers],_pregId);
      break;
    case 'boolean':
      //extraer
      Answer.respuesta = this.boolOption.toString();
      this.sendAnswer(user,_type,[Answer],_pregId);
      break;  
    case 'abierta':
      Answer.respuesta = this.openAnswer;
      this.sendAnswer(user,_type,[Answer],_pregId);
      break;
    case 'rango':
      Answer.respuesta = this.rangeOption.toString();
      this.sendAnswer(user,_type,[Answer],_pregId);
      break;
   }
   this.clearFields();
   stepper.next();
 }
 setBackendAnswer(simple, respuesta, encuestaEstudio, usuario, opcion) {
  let singleAnswer = new toBackendAnswer({ _id: this._encuesta._id }, { _id: this._usuario._id });
  /*singleAnswer.dtoEncuestaEstudio._id = encuestaEstudio;
  singleAnswer.dtousuario._id = usuario;*/
  if (simple === true) {
    singleAnswer.dtoopcion = opcion;
  } else {
    singleAnswer.respuesta = respuesta;
  }
  //this.toAdd.push(singleAnswer);
}
addRespuesta(user,data,_pregId){
  console.log('Sending answer...');
  this._respuestaService.createRespuesta(data).subscribe(
    (response: any) => {
      console.log(response);
      this.alterAnswers.emit({user,data,_pregId});
      this._answers[`${_pregId}`].push(data); 
      //this.opStatus = 'D';
    },
    (error) => {
      console.log(error);
      this.alterAnswers.emit({user,data,_pregId});
      this._answers[`${_pregId}`].push(data); 
      //this.opStatus = 'E';
    }
  );
}
saveSurvey(user,data,_pregId) {
  this.opStatus = 'P';
  this._respuestaService.saveSurvey(data).subscribe(
    (response: any) => {
      console.log(response);
      this.alterAnswers.emit({user,data,_pregId});
      this._answers[`${_pregId}`].push(data); 
     
    },
    (error) => {
      console.log(error);
      this.alterAnswers.emit({user,data,_pregId});
      this._answers[`${_pregId}`].push(data); 
      
    }
  );
}
 sendAnswer(user,preg,resp,_pregId){
   //ENVIO DE RESPUESTA SINGULAR
  /*
  case 'abierta':
          this.setBackendAnswer(
            false,
            respuesta.a_val,
            pregunta._id,
            this.userLogged,
            null
          );
          break;
        case 'simple':
          // Falta, obtener id de la opcion
          this.setBackendAnswer(
            true,
            null,
            pregunta._id,
            this.userLogged,
            respuesta.s_val._id
          );
          break;
        case 'multiple':
          // Falta, obtener id de la opcion y llamar la funcion segun tantas opciones posea
          respuesta.m_val.forEach((opcion, index) => {
            //verificar que funcione
            this.setBackendAnswer(
              true,
              null,
              pregunta._id,
              this.userLogged,
              opcion._id
            );
          });
          break;
        case 'boolean':
          this.setBackendAnswer(
            false,
            respuesta.b_val.toString(),
            pregunta._id,
            this.userLogged,
            null
          );
          break;
        case 'rango':
          // posible error por el valor
          this.setBackendAnswer(
            false,
            respuesta.r_val.toString(),
            pregunta._id,
            this.userLogged,
            null
          );
          break;
  */
   switch(preg){
    case 'simple':
      break;
    case 'multiple':
      break;
     default:
      this.saveSurvey(user,resp,_pregId);
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
