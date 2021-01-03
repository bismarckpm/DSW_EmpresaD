import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EstudioService } from '@core/services/estudio/estudio.service';

@Component({
  selector: 'app-upd-estudio-dialog',
  templateUrl: './upd-estudio-dialog.component.html',
  styleUrls: ['./upd-estudio-dialog.component.css']
})
export class UpdEstudioDialogComponent implements OnInit {
  opStatus:string;//S,P,D
  toService: any;
  @ViewChild('updEstudio') private modalContent: TemplateRef<UpdEstudioDialogComponent>;
  private modalRef: NgbModalRef;
  updForm: FormGroup;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _estudioService: EstudioService,){}
  @Input() _userSelection : number;
  @Input() _estudio : any;


  ngOnInit(): void {
    this.opStatus="S";
    this.updForm = this.formBuilder.group({
      tipo:null,
      estado: null,
      encuestas_esperadas:null,
    });
  }
  open(){
    this.toService = {
      tipo:null,
      estado: null,
      encuestas_esperadas:null,
    };
    this.modalRef =this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close(){
    this.opStatus="S";
    this.modalRef.close();
  }
  invokeService(){
     Object.entries(this.updForm.value).forEach(([key, field], ind) => {
      if (field !== null) {
        this.toService[key] = field;
      } else {
        this.toService[key] = this._estudio[key];
      }
    });
    this.opStatus="P";
    const { encuestas_esperadas,tipo,estado } = this.toService;
    console.log(this._estudio,{tipo,estado,encuestasEsperadas:encuestas_esperadas});
    this._estudioService.updateEstudio(this._estudio._id,{tipo,estado,encuestasEsperadas:encuestas_esperadas}).subscribe(
      (response) => {
        console.log(response);
        alert('Se modifico el estudio correctamente');
         this.opStatus="D";
      },
      (error) => {
        console.log(error);
         this.opStatus="E";
      }
    );
  }

}
