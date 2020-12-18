import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CategoriaService } from '@core/services/categoria/categoria.service';
@Component({
  selector: 'app-del-categoria-dialog',
  templateUrl: './del-categoria-dialog.component.html',
  styleUrls: ['./del-categoria-dialog.component.css'],
})
export class DelCategoriaDialogComponent implements OnInit {
  opStatus: string; //S,P,D

  @ViewChild('delCategoria')
  private modalContent: TemplateRef<DelCategoriaDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _service: CategoriaService
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

  deleteCategoria(id) {
    this._service.deleteCategoria(id, null).subscribe(
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
    /*this.opStatus="P";
    setTimeout(()=>{
      this.opStatus="D";
    },3000);*/
    console.log(this._userSelection);
    this.deleteCategoria(this._userSelection);
  }
}
