import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { MarcaService } from '@core/services/marca/marca.service';
import { Marca } from '@models/marca';

@Component({
  selector: 'app-del-marca-dialog',
  templateUrl: './del-marca-dialog.component.html',
  styleUrls: ['./del-marca-dialog.component.css'],
})
export class DelMarcaDialogComponent implements OnInit {
  opStatus: string; //S,P,D

  @ViewChild('delMarca')
  private modalContent: TemplateRef<DelMarcaDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _service: MarcaService
  ) {}
  @Input() _userSelection: number;
  @Input() _marca: Marca;

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

  deleteMarca() {
    this._service.deleteMarca(this._marca._id, null).subscribe(
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
    this.deleteMarca();
  }
}
