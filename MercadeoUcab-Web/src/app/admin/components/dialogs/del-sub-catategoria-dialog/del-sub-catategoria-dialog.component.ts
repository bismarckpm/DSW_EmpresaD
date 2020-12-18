import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SubcategoriaService } from '@core/services/subcategoria/subcategoria.service';
import { SubCategoria } from '@models/subcategoria';

@Component({
  selector: 'app-del-sub-catategoria-dialog',
  templateUrl: './del-sub-catategoria-dialog.component.html',
  styleUrls: ['./del-sub-catategoria-dialog.component.css'],
})
export class DelSubCategoriaDialogComponent implements OnInit {
  opStatus: string; //S,P,D

  @ViewChild('delSubCat')
  private modalContent: TemplateRef<DelSubCategoriaDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _subcategoriaService: SubcategoriaService
  ) {}
  @Input() _userSelection: number;
  @Input() _subcategoria: SubCategoria;

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

  invokeService() {
    this.opStatus = 'P';
    console.log(this._subcategoria._id);
    let toDelete = this._subcategoria._id;
    this._subcategoriaService.deleteSubCategoria(toDelete, null).subscribe(
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
}
