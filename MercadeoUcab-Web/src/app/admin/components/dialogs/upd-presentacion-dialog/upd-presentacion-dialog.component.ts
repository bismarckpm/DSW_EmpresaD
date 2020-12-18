import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Presentacion } from '@core/models/presentacion';
import { PresentacionService } from '@core/services/presentacion/presentacion.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-upd-presentacion-dialog',
  templateUrl: './upd-presentacion-dialog.component.html',
  styleUrls: ['./upd-presentacion-dialog.component.css'],
})
export class UpdPresentacionDialogComponent implements OnInit {
  opStatus: string; //S,P,D
  updForm:FormGroup;
  @ViewChild('updPresentacion')
  private modalContent: TemplateRef<UpdPresentacionDialogComponent>;
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
    this.updForm= this.formBuilder.group({
      cantidad:null,
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
  updatePresentacion(id, data) {
    this._presentacionService.updatePresentacion(id, data).subscribe(
      (response) => {
        console.log(response);
        alert('Se modifico la presentacion correctamente');
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
      }
    );
  }
  invokeService() {
    let toUpdate: any = {
      id:this._presentacion._id,
      cantidad:null,
      tipo:null,
    };
    // Campos que se deben enviar al Back
    // toUpdate.id
    // toUpdate.cantidad
    // toUpdate.tipo
    Object.entries(this.updForm.value).forEach(([key,field],ind)=>{
      if(field !== null){
        toUpdate[key]=field;
      }
      else{
        toUpdate[key] = this._presentacion[key];
      }
    })
    this.opStatus = 'P';
    this.updatePresentacion(toUpdate.id, toUpdate);
  }
}
