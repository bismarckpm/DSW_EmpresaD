import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CategoriaService } from '@core/services/categoria/categoria.service';
import { Categoria } from '@models/categoria';
@Component({
  selector: 'app-upd-categoria-dialog',
  templateUrl: './upd-categoria-dialog.component.html',
  styleUrls: ['./upd-categoria-dialog.component.css'],
})
export class UpdCategoriaDialogComponent implements OnInit {
  opStatus: string; //S,P,D,E
  updForm: FormGroup;
  toService: any;
  @ViewChild('updCategoria')
  private modalContent: TemplateRef<UpdCategoriaDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _service: CategoriaService
  ) {}
  @Input() _userSelection: number;
  @Input() _categoria: Categoria;

  ngOnInit(): void {
    this.opStatus = 'S';
    this.updForm = this.formBuilder.group({
      nombre: null,
      activo: null,
    });
    this.toService = {
      nombre: null,
      activo: null,
    };
  }
  open() {
    this.modalRef = this.modalService.open(this.modalContent);
    this.toService = {
      id: this._categoria._id,
      nombre: null,
    };
    this.modalRef.result.then();
  }
  close() {
    this.opStatus = 'S';
    this.modalRef.close();
  }
  updateCategoria(id, data) {
    this._service.updateCategoria(id, data).subscribe(
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
    Object.entries(this.updForm.value).forEach(([key, field], ind) => {
      if (field !== null) {
        this.toService[key] = field;
      } else {
        this.toService[key] = this._categoria[key];
      }
    });
    this.opStatus = 'P';
    let toUpdate: any = {};
    toUpdate.nombre = this.toService.nombre;
    this.updateCategoria(this._userSelection, toUpdate);
  }
}
