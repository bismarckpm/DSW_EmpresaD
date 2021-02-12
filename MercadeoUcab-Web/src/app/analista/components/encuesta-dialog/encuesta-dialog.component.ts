import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
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
 
 currentQuestion : any = null;

 ngOnInit(): void {
 }

 setQuestion(){
  
 }
 sendAnswer(){
   //ENVIO DE RESPUESTA SINGULAR
 }

 open() {
   this.modalRef = this.modalService.open(this.modalContent);
   this.modalRef.result.then();
   console.log(this._encuesta,this._usuario);
 }
 close() {
   this.modalRef.close();
 }

}
