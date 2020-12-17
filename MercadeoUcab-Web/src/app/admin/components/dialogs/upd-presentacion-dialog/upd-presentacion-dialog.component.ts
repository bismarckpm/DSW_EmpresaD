import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { PresentacionService } from '@core/services/presentacion/presentacion.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-upd-presentacion-dialog',
  templateUrl: './upd-presentacion-dialog.component.html',
  styleUrls: ['./upd-presentacion-dialog.component.css'],
})
export class UpdPresentacionDialogComponent implements OnInit {
  opStatus: string; //S,P,D

  @ViewChild('updPresentacion')
  private modalContent: TemplateRef<UpdPresentacionDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _presentacionService: PresentacionService
  ) {}
  @Input() _userSelection: number;

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
  updatePresentacion(id, data) {
    this._presentacionService.updatePresentacion(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se modifico la presentacion correctamente');
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
    // toUpdate.cantidad
    // toUpdate.tipo
    this.updatePresentacion(toUpdate.id, toUpdate);
    this.opStatus = 'P';
    setTimeout(() => {
      this.opStatus = 'D';
    }, 3000);
  }
}
