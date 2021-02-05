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

 ngOnInit(): void {
 }

 open() {
   this.modalRef = this.modalService.open(this.modalContent);
   this.modalRef.result.then();
 }
 close() {
   this.modalRef.close();
 }

}
