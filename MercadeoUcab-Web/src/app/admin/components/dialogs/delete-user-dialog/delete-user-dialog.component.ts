import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { UsuarioService } from '@core/services/usuario/usuario.service';
@Component({
  selector: 'app-delete-user-dialog',
  templateUrl: './delete-user-dialog.component.html',
  styleUrls: ['./delete-user-dialog.component.css'],
})
export class DeleteUserDialogComponent implements OnInit {
  opStatus: string; //S,P,D
  updForm: FormGroup;

  @ViewChild('delUser')
  private modalContent: TemplateRef<DeleteUserDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _service: UsuarioService
  ) {}
  //ID DE REGISTRO A ELIMINAR
  @Input() _userSelection: number;
  @Input() _user: any;

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

  deleteUser(id) {
    this._service.deleteUser(id, null).subscribe(
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
  /*

  EN ESTE CASO SOLO SE RECIBE UN ID Y SE ELIMINA LA ENTIDAD,
  LA UNICA VARIANTE ES NO RECIBIR UN ID SINO EL REGISTRO COMPLETO 
  A ELIMINAR Y DE ALLI SE EXTRAE EL ID REQUERIDO PARA EL PROCESO DE 
  ELIMINACION
  
  */
  invokeService() {
    this.opStatus = 'P';
    console.log(this._userSelection);
    this.deleteUser(this._userSelection);
  }
}
