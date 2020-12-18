import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EstadoService } from '@core/services/estado/estado.service';
import { PaisService } from '@core/services/pais/pais.service';
import { MunicipioService } from '@core/services/municipio/municipio.service';
import { ParroquiaService } from '@core/services/parroquia/parroquia.service';
@Component({
  selector: 'app-del-lugar-dialog',
  templateUrl: './del-lugar-dialog.component.html',
  styleUrls: ['./del-lugar-dialog.component.css'],
})
export class DelLugarDialogComponent implements OnInit {
  opStatus: string; //S,P,D

  @ViewChild('delLugar')
  private modalContent: TemplateRef<DelLugarDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _paisService: PaisService,
    private _estadoService: EstadoService,
    private _municipioService: MunicipioService,
    private _parroquiaService: ParroquiaService
  ) {}

  @Input() _userSelection: number;
  @Input() _tipo: string;
  @Input() _lugar: any;
  ngOnInit(): void {
    this.opStatus = 'S';
  }
  deletePais(id) {
    this._paisService.deletePais(id, null).subscribe(
      (response) => {
        console.log(response);
        //alert('Se elimino el pais correctamente');
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    );
  }

  deleteEstado(id) {
    this._estadoService.deleteEstado(id, null).subscribe(
      (response) => {
        console.log(response);
        //alert('Se elimino el estado correctamente');
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    );
  }
  deleteParroquia(id) {
    this._parroquiaService.deleteParroquia(id, null).subscribe(
      (response) => {
        console.log(response);
        //alert('Se elimino la parroquia correctamente');
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    );
  }
  deleteMunicipio(id) {
    this._municipioService.deleteMunicipio(id, null).subscribe(
      (response) => {
        console.log(response);
        //alert('Se elimino el municipio correctamente');
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    );
  }

  open(id: number, tipo: string) {
    this._tipo = tipo;
    this._userSelection = id;
    this.modalRef = this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close() {
    this.opStatus = 'S';
    this.modalRef.close();
  }
  invokeService(id: number, role: string) {
    this.opStatus = 'P';
    switch (role) {
      case 'PA':
        this.deletePais(this._userSelection);
        break;
      case 'ES':
        this.deleteEstado(this._userSelection);
        break;
      case 'MU':
        this.deleteMunicipio(this._userSelection);
        break;
      case 'PR':
        this.deleteParroquia(this._userSelection);
        break;
      default:
        break;
    }
  }
}
