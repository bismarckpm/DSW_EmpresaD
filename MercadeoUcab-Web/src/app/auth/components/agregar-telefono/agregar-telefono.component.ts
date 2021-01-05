import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {ParroquiaService} from "@core/services/parroquia/parroquia.service";
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {OcupacionService} from "@core/services/ocupacion/ocupacion.service";

@Component({
  selector: 'app-agregar-telefono',
  templateUrl: './agregar-telefono.component.html',
  styleUrls: ['./agregar-telefono.component.css']
})
export class AgregarTelefonoComponent implements OnInit {

  @ViewChild('AgregarTelefonoComponent')
  private modalContent: TemplateRef<AgregarTelefonoComponent>;
  private modalRef: NgbModalRef;

  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder
  ) { }
  Form: FormGroup;

  ngOnInit(): void {
  }

  open(){
    this.modalRef = this.modalService.open(this.modalContent);
    this.modalRef.result.then();
    this.Form = new FormGroup({
      telefonos: new FormArray([]),
    });
  }

  get Telefonos(): FormArray{
    return this.Form.get('telefonos') as FormArray;
  }

  agregarTelefono(){
    this.Telefonos.push(new FormControl());
  }

  removerTelefono(indice: number){
    this.Telefonos.removeAt(indice);
  }

}
