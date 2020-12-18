import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Presentacion } from '@core/models/presentacion';
import { PresentacionService } from '@core/services/presentacion/presentacion.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-del-presentacion-dialog',
  templateUrl: './del-presentacion-dialog.component.html',
  styleUrls: ['./del-presentacion-dialog.component.css'],
})
export class DelPresentacionDialogComponent implements OnInit {
  opStatus: string; //S,P,D

  @ViewChild('delPresentacion')
  private modalContent: TemplateRef<DelPresentacionDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _presentacionService: PresentacionService
  ) {}
  @Input() _userSelection: number;
  @Input() _presentacion: Presentacion;

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
  deletePresentacion(id, data) {
    this._presentacionService.deletePresentacion(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se elimino la presentacion correctamente');
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
    this.deletePresentacion(this._presentacion._id,this._presentacion);
    /*
    setTimeout(() => {
      this.opStatus = 'D';
    }, 3000);*/
  }
}
