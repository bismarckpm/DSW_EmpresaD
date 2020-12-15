import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SubcategoriaService } from '@core/services/subcategoria/subcategoria.service';
import { SubCategoria } from '@models/subcategoria';

@Component({
  selector: 'app-upd-sub-catategoria-dialog',
  templateUrl: './upd-sub-catategoria-dialog.component.html',
  styleUrls: ['./upd-sub-catategoria-dialog.component.css'],
})
export class UpdSubCategoriaDialogComponent implements OnInit {
  opStatus: string; //S,P,D,E
  updForm: FormGroup;
  toService: any;
  @ViewChild('updSubCat')
  private modalContent: TemplateRef<UpdSubCategoriaDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _service: SubcategoriaService
  ) {}
  @Input() _userSelection: number;
  @Input() _subcategoria: SubCategoria;

  ngOnInit(): void {
    this.opStatus = 'S';
    this.updForm = this.formBuilder.group({
      nombre: null,
    });
    this.toService = {
      nombre: null,
      _id: null,
    };
  }
  open() {
    this.modalRef = this.modalService.open(this.modalContent);
    this.toService = {
      _id: this._subcategoria._id,
      nombre: null,
    };
    this.modalRef.result.then();
  }
  close() {
    this.opStatus = 'S';
    this.modalRef.close();
  }
  updateSubCategoria(id, data) {
    this._service.updateSubCategoria(id, data).subscribe(
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
        this.toService[key] = this._subcategoria[key];
      }
    });
    this.opStatus = 'P';
    //this.updateSubCategoria(this.toService);
  }
}
