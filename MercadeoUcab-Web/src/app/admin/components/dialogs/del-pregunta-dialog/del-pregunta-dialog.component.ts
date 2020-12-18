import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Pregunta } from '@core/models/pregunta';
import { PreguntaService } from '@core/services/pregunta/pregunta.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-del-pregunta-dialog',
  templateUrl: './del-pregunta-dialog.component.html',
  styleUrls: ['./del-pregunta-dialog.component.css'],
})
export class DelPreguntaDialogComponent implements OnInit {
  opStatus: string; //S,P,D

  @ViewChild('delPregunta')
  private modalContent: TemplateRef<DelPreguntaDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
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

  deletePregunta(id, data) {
    this._preguntaService.deletePregunta(id, data).subscribe(
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
    this.opStatus = 'P';
    this.deletePregunta(this._pregunta._id,this._pregunta);
  }
}
