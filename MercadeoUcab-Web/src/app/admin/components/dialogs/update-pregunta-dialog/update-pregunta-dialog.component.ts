import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder } from '@angular/forms';
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

  @ViewChild('updLugar')
  private modalContent: TemplateRef<UpdatePreguntaDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _preguntaService: PreguntaService
  ) {}
  @Input() _userSelection: number;
  @Input() _pregunta: Pregunta;

  ngOnInit(): void {
    this.opStatus = 'S';
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
      },
      (error) => {
        console.log(error);
      }
    );
  }
  invokeService() {
    let toUpdate: any = {};
    // Campos que se deben enviar al Back
    // toUpdate.id
    // toUpdate.nombre_pregunta
    // toUpdate.tipo
    // toUpdate.rango
    this.updatePregunta(toUpdate.id, toUpdate);
    this.opStatus = 'P';
    setTimeout(() => {
      this.opStatus = 'D';
    }, 3000);
  }
}
