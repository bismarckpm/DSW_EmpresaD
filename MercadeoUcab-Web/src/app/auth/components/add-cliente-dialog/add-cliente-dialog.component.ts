import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UsuarioService } from '@core/services/usuario/usuario.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-add-cliente-dialog',
  templateUrl: './add-cliente-dialog.component.html',
  styleUrls: ['./add-cliente-dialog.component.css']
})
export class AddClienteDialogComponent implements OnInit {
  opStatus:string;
  addForm:FormGroup;
  @ViewChild('addUserCliente') private modalContent: TemplateRef<AddClienteDialogComponent>;
  private modalRef: NgbModalRef;

  constructor(
    private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _usuarioService: UsuarioService,){}


  ngOnInit(): void {
    this.opStatus="S";
    this.addForm = this.formBuilder.group({
      nombre: null,
      apellido: null,
      rol: 'cliente',
      estado: 'Activo',
      correo: null,
      password: null,
    });
  }
  
  open(){
    this.modalRef =this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close(){
    this.opStatus="S";
    this.modalRef.close();
    this.addForm = this.formBuilder.group({
      nombre: null,
      apellido: null,
      rol: 'cliente',
      estado: 'Activo',
      correo: null,
      password: null,
    });
  }
  addUser(data){
    console.log(data);
    this._usuarioService.createUser(data).subscribe(
      (response) => {
        console.log(response);
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    )
  }
  invokeService(){
    this.opStatus="P";
    this.addUser(this.addForm.value);
  }
}
